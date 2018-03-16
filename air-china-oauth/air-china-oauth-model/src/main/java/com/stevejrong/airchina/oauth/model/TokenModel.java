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
package com.stevejrong.airchina.oauth.model;

import com.stevejrong.airchina.oauth.common.model.BaseModel;

/**
 * Model - t_base_token
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月25日 下午11:30:32
 */
public class TokenModel extends BaseModel {
	private String userId; // 用户识别码（具有唯一性）
	private String email; // 电子邮件地址
	private String token; // Token令牌
	private Integer state; // 状态 (使用-1,禁用-0)

	public TokenModel() {
	}

	public TokenModel(String userId, String email, String token, Integer state) {
		this.userId = userId;
		this.email = email;
		this.token = token;
		this.state = state;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
