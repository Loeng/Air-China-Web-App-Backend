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
package com.stevejrong.airchina.oauth.common.constant;

/**
 * 常量
 *
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午1:06:44
 */
public class Constants {
	/** 系统 */
	public static final String SECURET = "airchina";
	public static final String REQUEST_ROOT_SUFFIX = "/oauth";
	public static final String ERROR_SUFFIX = "/error";
	public static final String USER_ID = "userid";
	public static final String FAILED_REASON = "failed reason";
	public static final String APPLICATION_JSON = "application/json";
	public static final String UTF8 = "UTF-8";
	public static final String APPLICATION_JSON_UTF8 = APPLICATION_JSON + ";charset=" + UTF8;

	/** Shiro */
	public static final String SHIRO_LOGOUT_SUFFIX = REQUEST_ROOT_SUFFIX + "/logout";
	public static final String SHIRO_UNAUTHORIZED_SUFFIX = "/403";
	public static final String SHIRO_AUTHENTICATE_SUFFIX = REQUEST_ROOT_SUFFIX + "/authenticate";
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String AUTHORIZATION_PARAM = "auth";
	public static final String AUTHORIZATION_SCHEME = "Bearer";
	public static final String AUTHORIZATION_SCHEME_ALT = "Basic";
	public static final String TOKEN = "token";

	/** 消息 */
	public static final String MESSAGE_CODE = "code";
	public static final String MESSAGE = "message";
	public static final String MESSAGE_TIMESTAMP = "timestamp";
}
