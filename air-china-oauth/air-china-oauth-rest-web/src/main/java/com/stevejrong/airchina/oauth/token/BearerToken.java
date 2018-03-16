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
package com.stevejrong.airchina.oauth.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 认证时的Token令牌
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午4:58:00
 */
public class BearerToken implements AuthenticationToken {
	private static final long serialVersionUID = 6970024298027020920L;

	private final String email;
	private final String token;

	public BearerToken(String email, String token) {
		super();
		this.email = email;
		this.token = token;
	}

	@Override
	public Object getCredentials() {
		return token;
	}

	@Override
	public Object getPrincipal() {
		return email;
	}
}
