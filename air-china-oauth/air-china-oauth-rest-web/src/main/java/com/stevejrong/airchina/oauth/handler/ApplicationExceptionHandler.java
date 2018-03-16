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
package com.stevejrong.airchina.oauth.handler;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller Advice - 异常增强
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午12:21:08
 */
@ControllerAdvice
public class ApplicationExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

	/**
	 * 将异常类型AuthenticationException、UnknownAccountException、UnauthenticatedException、
	 * IncorrectCredentialsException和UnauthorizedException都指定为HTTP 401异常代码
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({ AuthenticationException.class, UnknownAccountException.class, UnauthenticatedException.class,
			IncorrectCredentialsException.class, UnauthorizedException.class })
	public void processUnauthorizedException() {
		LOGGER.info("无权限或未登录...");
	}
}
