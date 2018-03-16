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
package com.stevejrong.airchina.oauth.shiro.filter;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.stevejrong.airchina.oauth.common.constant.Constants;

/**
 * Filter - Form表单的认证过滤器
 * 
 * @author Steve Jrong
 * @since 1.0
 * create date: 2018年2月26日 下午4:23:44
 */
public class WebAuthenticationFilter extends FormAuthenticationFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WebAuthenticationFilter.class);

	/**
	 * 设置登录失败的属性
	 */
	@Override
	protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
		request.setAttribute(Constants.FAILED_REASON, ae.getLocalizedMessage());
	}

	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		AuthenticationToken token = createToken(request, response); // 创建Token
		Subject subject = SecurityUtils.getSubject(); // 获取Subject
		Session originalSession = subject.getSession(); // 从Subject中获取Session

		Map<Object, Object> attributes = Maps.newLinkedHashMap();
		Collection<Object> keys = originalSession.getAttributeKeys(); // 从Session中获取到已登录的信息以进行对话销毁
		for (Object key : keys) {
			Object value = originalSession.getAttribute(key);
			if (value != null) {
				attributes.put(key, value);
			}
		}
		originalSession.stop(); // 销毁对话以进行登录

		try {
			subject.login(token);

			// 登录成功后使用新的Session信息
			Session newSession = subject.getSession();
			for (Object key : attributes.keySet()) {
				newSession.setAttribute(key, attributes.get(key));
			}
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			LOGGER.error("登录失败。{}", e.getLocalizedMessage());
			return onLoginFailure(token, e, request, response);
		}
	}
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//		Subject subject = SecurityUtils.getSubject();
//		UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");
//
//		subject.login(token);
//
//		System.out.println("登录成功！...");
//
//		if (this.isLoginRequest(request, response)) {
//			if (this.isLoginSubmission(request, response)) {
//				if (LOGGER.isTraceEnabled()) {
//					LOGGER.trace("Login submission detected.  Attempting to execute login.");
//				}
//				return this.executeLogin(request, response);
//			} else {
//				if (LOGGER.isTraceEnabled()) {
//					LOGGER.trace("Login page view.");
//				}
//				return true;
//			}
//		}
		return true;
	}
}
