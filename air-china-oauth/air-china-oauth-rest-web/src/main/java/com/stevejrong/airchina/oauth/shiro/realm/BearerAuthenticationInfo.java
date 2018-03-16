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

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import com.stevejrong.airchina.oauth.model.TokenModel;

/**
 * 权限认证用户信息
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午4:46:36
 */
public class BearerAuthenticationInfo implements AuthenticationInfo {
	
	private static final long serialVersionUID = 3470761774414912759L;
	
	private BearerTokenAuthenticatingRealm bearerTokenAuthenticatingRealm;
	private final TokenModel token;

	BearerAuthenticationInfo(BearerTokenAuthenticatingRealm bearerTokenAuthenticatingRealm, TokenModel token) {
		this.bearerTokenAuthenticatingRealm = bearerTokenAuthenticatingRealm;
		this.token = token;
	}

	@Override
	public Object getCredentials() {
		return token.getToken();
	}

	/**
	 * 获取用户主体信息
	 */
	@Override
	public PrincipalCollection getPrincipals() {
		SimplePrincipalCollection ret = new SimplePrincipalCollection();
		ret.add(token.getEmail(), bearerTokenAuthenticatingRealm.getName());
		return ret;
	}
}