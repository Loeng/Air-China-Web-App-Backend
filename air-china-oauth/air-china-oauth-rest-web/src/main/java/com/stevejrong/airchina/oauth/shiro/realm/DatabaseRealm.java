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
import com.stevejrong.airchina.oauth.model.MenuModel;
import com.stevejrong.airchina.oauth.model.RoleModel;
import com.stevejrong.airchina.oauth.model.UserModel;
import com.stevejrong.airchina.oauth.service.MenuService;
import com.stevejrong.airchina.oauth.service.RoleService;
import com.stevejrong.airchina.oauth.service.UserService;
import com.stevejrong.airchina.oauth.shiro.util.ShiroHashUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 数据库认证数据源
 * 在调用用户授权接口给用户授权生成Token时会用到
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午11:24:45
 */
public class DatabaseRealm extends AuthorizingRealm {
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseRealm.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;

	public DatabaseRealm() {
		super(ShiroHashUtil.getCredentialsMatcher());
		setAuthenticationTokenClass(UsernamePasswordToken.class);
	}

	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}
	
	/**
	 * 授权方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Preconditions.checkNotNull(principals, "授权时的principals不能都为空！");
		
		String email = (String) getAvailablePrincipal(principals);
		if (email == null) {
			throw new NullPointerException("授权时的principal不能为空！");
		}
		
		UserModel user = userService.getByEmail(email);

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
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if (token instanceof UsernamePasswordToken) {
			String email = ((UsernamePasswordToken) token).getUsername();
			return doGetAuthenticationInfo(email);
		}
		throw new UnsupportedOperationException("Implement another method of getting user email.");
	}

	private AuthenticationInfo doGetAuthenticationInfo(String email) throws AuthenticationException {
		Preconditions.checkNotNull(email, "电子邮件地址不能为空！");

		UserModel user = userService.getByEmail(email);
		SimpleAccount account = new SimpleAccount(user.getEmail(), user.getPassword().toCharArray(),
				new SimpleByteSource(email), getName());
		
		// 根据用电子邮件地址查找此用户的角色和权限
		List<RoleModel> totalRoles = roleService.listAllRolesByUserId(user.getUserId()); // 查找所有角色（目前仅支持单角色，获取到了1个以上的角色就取其中的一个）
		List<MenuModel> totalMenuPermissions = menuService.listAllMenusByUserId(user.getUserId()); // 查找所有菜单权限

		final Set<String> roleNames = new LinkedHashSet<String>(totalRoles.size());
		final Set<String> permissionNames = new LinkedHashSet<String>(totalMenuPermissions.size());

		roleNames.add(totalRoles.stream().findFirst().get().getRoleCode());
		totalMenuPermissions.forEach(menu -> {
			permissionNames.add(menu.getRequestUrl());
		});

		account.addRole(roleNames);
		account.addStringPermissions(permissionNames);
		return account;
	}
}