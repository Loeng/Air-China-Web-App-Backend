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
package com.stevejrong.airchina.common.util;

/**
 * Enumeration - HTTP状态码
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午1:29:48
 */
public enum HttpStatus {
	OK(200, "成功"),
	BAD_REQUEST(400, "请求参数错误"),
	UNAUTHORIZED(401, "无权限访问资源"),
	NOT_FOUND(404, "资源未找到"),
	FORBIDDEN(403, "资源访问受限"),
	INTERNAL_ERROR(500, "服务器内部错误");

	private Integer code;

	private String message;

	HttpStatus(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer code() {
		return code;
	}

	public String message() {
		return message;
	}

	@Override
	public String toString() {
		return Integer.toString(code());
	}
}
