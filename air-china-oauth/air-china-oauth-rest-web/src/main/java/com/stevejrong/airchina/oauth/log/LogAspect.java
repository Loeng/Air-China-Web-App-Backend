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
package com.stevejrong.airchina.oauth.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * AspectJ - 日志
 * 
 * @author Steve Jrong
 * @since 1.0 - 2018年1月29日 下午8:47:33
 */
@Aspect
@Configuration
public class LogAspect {
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	ThreadLocal<Long> startTime = new ThreadLocal<Long>();

	@Pointcut(value = "execution(public * com.stevejrong.airchina.oauth.rest.web.controller.*.*(..))")
	public void webRestLog() {
	}

	@Before(value = "webRestLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		startTime.set(System.currentTimeMillis());
		
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		LOGGER.info("URL:{}", request.getRequestURL());
		LOGGER.info("HTTP_Method:{}", request.getMethod());
		LOGGER.info("IP:{}", request.getRemoteAddr());
		LOGGER.info("Class_Method:{}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
		LOGGER.info("Args:{}", Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(returning = "returnRequestResult", pointcut = "webRestLog()")
	public void doAfterReturning(Object returnRequestResult) {
		LOGGER.info("Response:{}", returnRequestResult);
		LOGGER.info("Spend Time:{}", (System.currentTimeMillis() - startTime.get()));
	}
}