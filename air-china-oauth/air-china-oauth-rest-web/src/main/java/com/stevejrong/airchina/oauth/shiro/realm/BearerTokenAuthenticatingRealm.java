/**
 * Copyright 2018 Steve Jrong - https://www.stevejrong.top

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 *     http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stevejrong.airchina.oauth.shiro.realm;

import com.google.common.base.Preconditions;
import com.stevejrong.airchina.oauth.common.bo.UserBo;
import com.stevejrong.airchina.oauth.model.MenuModel;
import com.stevejrong.airchina.oauth.model.RoleModel;
import com.stevejrong.airchina.oauth.model.TokenModel;
import com.stevejrong.airchina.oauth.rest.spi.feign.UserFeign;
import com.stevejrong.airchina.oauth.service.MenuService;
import com.stevejrong.airchina.oauth.service.RoleService;
import com.stevejrong.airchina.oauth.service.TokenService;
import com.stevejrong.airchina.oauth.token.BearerToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Token认证数据源
 * 在访问受限资源时会用到
 *
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午4:46:24
 */
public class BearerTokenAuthenticatingRealm extends AuthorizingRealm {
	private static final Logger LOGGER = LoggerFactory.getLogger(BearerTokenAuthenticatingRealm.class);

	@Autowired
	private TokenService tokenService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private UserFeign userFeign;

	public BearerTokenAuthenticatingRealm() {
		super(new AllowAllCredentialsMatcher());
		setAuthenticationTokenClass(BearerToken.class);
	}

	/**
	 * 授权方法
	 * 当访问到受限资源时Shiro将会执行此方法以为用户授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		LOGGER.debug("访问到了需要权限的资源，开始进行授权...");
		Preconditions.checkNotNull(principalCollection, "授权时的principals不能都为空！");

		String email = (String) getAvailablePrincipal(principalCollection);
		if (email == null) {
			throw new NullPointerException("授权时的principal不能为空！");
		}

		UserBo user = userFeign.getByEmail(email);
		// 根据用电子邮件地址查找此用户的角色和权限
		List<RoleModel> totalRoles = roleService.listAllRolesByUserId(user.getUserId()); // 查找所有角色（目前仅支持单角色，获取到了1个以上的角色就取其中的一个）
		List<MenuModel> totalMenuPermissions = menuService.listAllMenusByUserId(user.getUserId()); // 查找所有菜单权限

		final Set<String> roleNames = new LinkedHashSet<String>(totalRoles.size());
		final Set<String> permissionNames = new LinkedHashSet<String>(totalMenuPermissions.size());

		roleNames.add(totalRoles.stream().findFirst().get().getRoleCode());
		totalMenuPermissions.forEach(menu -> {
			permissionNames.add(menu.getRequestUrl());
		});

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRoles(roleNames);
		info.addStringPermissions(permissionNames);
		return info;
	}

	/**
	 * 认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		BearerToken token = (BearerToken) arg0;
		String email = (String) token.getPrincipal();
		String credentials = (String) token.getCredentials();

		Preconditions.checkNotNull(email, "电子邮件地址不能为空");
		Preconditions.checkNotNull(token, "Token令牌不能为空");

		TokenModel dbToken = tokenService.getByEmail(email);
		if (tokenIsInvalid(token, dbToken)) {
			LOGGER.info("用户{}的令牌{}被拒绝！", email, credentials);
			return null;
		}

		return new BearerAuthenticationInfo(this, dbToken);
	}

	/**
	 * 验证Token是否有效
	 *
	 * @param token 传入的Token信息实体
	 * @param dbToken 数据库中的Token信息实体
	 * @return
	 */
	private static boolean tokenIsInvalid(BearerToken token, TokenModel dbToken) {
		return token == null || dbToken == null || !dbToken.getEmail().equals(token.getPrincipal());
	}

	@Override
	public void onLogout(PrincipalCollection principals) {
		super.onLogout(principals);
//		deleteTokens(principals);
	}

//	@SuppressWarnings("unchecked")
//	private void deleteTokens(PrincipalCollection principals) {
//		Collection<String> tokens = principals.fromRealm(getName());
//		if (tokens != null) {
//			tokenService.deleteAuthenticationTokens(tokens);
//		}
//	}
}
