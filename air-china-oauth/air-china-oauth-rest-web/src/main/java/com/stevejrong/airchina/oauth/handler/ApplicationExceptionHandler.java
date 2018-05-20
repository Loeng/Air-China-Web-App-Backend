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

import com.stevejrong.airchina.common.wrapper.ResponseWrapper;
import com.stevejrong.airchina.oauth.common.constant.ExceptionConstantsEnum;
import com.stevejrong.airchina.oauth.rest.common.web.exception.AirChinaTokenExpiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller Advice - 异常增强
 * 这里仅适用于Controller层的异常捕获和处理
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午12:21:08
 */
@ControllerAdvice
public class ApplicationExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = AirChinaTokenExpiredException.class)
	@ResponseBody
	public ResponseWrapper processExpiredJwtException(AirChinaTokenExpiredException ex) {
		LOGGER.error("Token过期……");
		return ResponseWrapper.response(ExceptionConstantsEnum.TOKEN_EXPIRED.exceptionCode(),
				ExceptionConstantsEnum.TOKEN_EXPIRED.exceptionMessage(), ex.getLocalizedMessage());
	}
}
