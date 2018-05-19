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

import com.alibaba.fastjson.JSONObject;
import com.stevejrong.airchina.common.util.HttpStatus;
import com.stevejrong.airchina.common.util.HttpUtil;
import com.stevejrong.airchina.oauth.common.constant.Constants;
import com.stevejrong.airchina.oauth.common.constant.ExceptionConstantsEnum;
import com.stevejrong.airchina.oauth.model.TokenModel;
import com.stevejrong.airchina.oauth.model.UserModel;
import com.stevejrong.airchina.oauth.rest.common.web.exception.AirChinaExpiredTokenException;
import com.stevejrong.airchina.oauth.service.TokenService;
import com.stevejrong.airchina.oauth.service.UserService;
import com.stevejrong.airchina.oauth.token.BearerToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Filter - Token权限认证过滤器
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午12:25:03
 */
public class BearerTokenAuthenticatingFilter extends AuthenticatingFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(BearerTokenAuthenticatingFilter.class);

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserService userService;

	private String userNameParam; // 获取新Token接口的用户名入参
	private String passwordParam; // 获取新Token接口的用户密码入参

	private String getUserNameParam() {
		return userNameParam;
	}

	public void setUserNameParam(String userNameParam) {
		this.userNameParam = userNameParam;
	}

	private String getPasswordParam() {
		return passwordParam;
	}

	public void setPasswordParam(String passwordParam) {
		this.passwordParam = passwordParam;
	}

	/**
	 * 创建Token
	 */
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {

		if (isLoginRequest(request, response)) { // 判断是否是登录请求，是则继续执行
			String JSON = request.getReader().readLine();
			JSONObject contents = JSONObject.parseObject(JSON);
			String userName = (String) contents.get(getUserNameParam()); // 请求头中的用户名
			String password = (String) contents.get(getPasswordParam()); // 请求头中的密码
			return createToken(userName, password, request, response);
		} else {
			String authorizeHeader = getAuthorizationHeader(request);
			String authorizeParameter = getAuthorizationParameter(request);
			String[] principlesAndCredentials;

			// 判断请求中Token所处的位置
			if (isHeaderLoginAttempt(authorizeHeader)) { // Token信息在时候包含在请求头中
				principlesAndCredentials = this.getHeaderPrincipalsAndCredentials(authorizeHeader);
			} else if (isParameterLoginAttempt(authorizeParameter)) { // Token信息是否包含在请求的参数中
				principlesAndCredentials = this.getParameterPrincipalsAndCredentials(authorizeParameter);
			} else {
				return null;
			}

			if (principlesAndCredentials == null || principlesAndCredentials.length != 2) {
				return null;
			}

			String userName = principlesAndCredentials[0];
			String token = principlesAndCredentials[1];
			return new BearerToken(userName, token);
		}
	}

	/**
	 * 获取请求头中为Authorization的头信息
	 * 
	 * @param request
	 * @return
	 */
	String getAuthorizationHeader(ServletRequest request) {
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		return httpRequest.getHeader(Constants.AUTHORIZATION_HEADER);
	}

	String getAuthorizationParameter(ServletRequest request) {
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		return WebUtils.getCleanParam(httpRequest, Constants.AUTHORIZATION_PARAM);
	}

	/**
	 * 是否在请求头中携带数据进行登录
	 * 
	 * @param authorizeHeader
	 * @return
	 */
	boolean isHeaderLoginAttempt(String authorizeHeader) {
		if (authorizeHeader == null)
			return false;
		String authorizeScheme = Constants.AUTHORIZATION_SCHEME.toLowerCase(Locale.ENGLISH);
		String authorizeSchemeAlt = Constants.AUTHORIZATION_SCHEME_ALT.toLowerCase(Locale.ENGLISH);
		String test = authorizeHeader.toLowerCase(Locale.ENGLISH);
		return test.startsWith(authorizeScheme) || test.startsWith(authorizeSchemeAlt);
	}

	/**
	 * 从请求头中获取账户和凭据信息
	 * 
	 * @param authorizeHeader
	 * @return
	 */
	String[] getHeaderPrincipalsAndCredentials(String authorizeHeader) throws AirChinaExpiredTokenException {
		if (authorizeHeader == null) {
			return null;
		}
		String[] authTokens = authorizeHeader.split(" ");
		if (authTokens == null || authTokens.length < 2) {
			return null;
		}
		return getPrincipalsAndCredentials(authTokens[1]);
	}

	String[] getPrincipalsAndCredentials(String authorizeParam) throws AirChinaExpiredTokenException {
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(Constants.SECURET.getBytes())
					.parseClaimsJws(authorizeParam);
		} catch (ExpiredJwtException e) {
			// 如果是Jwt Token过期异常，就捕获到并抛出一个自定义的异常
			throw new AirChinaExpiredTokenException(ExceptionConstantsEnum.EXPIRED_TOKEN.exceptionCode(),
					ExceptionConstantsEnum.EXPIRED_TOKEN.exceptionMessage());
		}
		String email = claims.getBody().getSubject();
		return new String[] { email, authorizeParam };
	}

	/**
	 * 是否在请求参数中携带数据进行登录
	 * 
	 * @param authorizeParam
	 * @return
	 */
	boolean isParameterLoginAttempt(String authorizeParam) {
		return (authorizeParam != null) && Base64.isBase64(authorizeParam.getBytes());
	}

	/**
	 * 从请求参数中获取账户和凭据信息
	 * 
	 * @param authorizeParam
	 * @return
	 */
	String[] getParameterPrincipalsAndCredentials(String authorizeParam) throws AirChinaExpiredTokenException {
		if (authorizeParam == null) {
			return null;
		}
		return getPrincipalsAndCredentials(authorizeParam);
	}

	/**
	 * 当访问拒绝时是否已经处理 true - 需要继续处理;false - 该拦截器实例已经处理并立即返回
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		boolean authHasToken = hasAuthorizationToken(request);
		boolean isLogin = isLoginRequest(request, response);
		if (authHasToken || isLogin) {
			if (isLoginSubmission(request, response) || authHasToken) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("是登录提交类型，尝试登录...");
				}
				return executeLogin(request, response);
			} else {
				HttpUtil.outputErrorMessage(HttpUtil.convertServletResponseToHttp(response), HttpStatus.UNAUTHORIZED);
				return false;
			}
		} else {
			HttpUtil.outputErrorMessage(HttpUtil.convertServletResponseToHttp(response), HttpStatus.UNAUTHORIZED);
			return false;
		}
	}

	/**
	 * 在请求中是否包含Token
	 * 
	 * @param request
	 * @return
	 */
	boolean hasAuthorizationToken(ServletRequest request) {
		String authorizeHeader = getAuthorizationHeader(request);
		String authorizeParam = getAuthorizationParameter(request);
		return isHeaderLoginAttempt(authorizeHeader) || isParameterLoginAttempt(authorizeParam);
	}

	/**
	 * 判断请求是否是POST请求
	 * 
	 * @param request
	 *            ServletRequest
	 * @param response
	 *            ServletResponse
	 * @return
	 */
	protected boolean isLoginSubmission(ServletRequest request, ServletResponse response) {
		return (request instanceof HttpServletRequest)
				&& WebUtils.toHttp(request).getMethod().equalsIgnoreCase(POST_METHOD);
	}

	/**
	 * 登录成功后执行的方法
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		if (isLoginRequest(request, response)) { // 判断是否是登录请求，是则继续执行
			String email = (String) subject.getPrincipal();
			UserModel user = userService.getByEmail(email);
			TokenModel existToken = tokenService.getByUserId(user.getUserId());
			
			String newToken = createAuthenticationToken(existToken, user, email);

			HttpUtil.outputJsonMessage(HttpUtil.convertServletResponseToHttp(response), Constants.MESSAGE_STATUS,
					HttpStatus.OK.code(), Constants.MESSAGE, HttpStatus.OK.code(), Constants.USER_IDENTITY_CODE,
					email, Constants.TOKEN, newToken);
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 创建Token并保存在数据库中
	 * @param existToken
	 * @param user
	 * @param email
	 * @return
	 */
	String createAuthenticationToken (TokenModel existToken, UserModel user, String email){
		if (existToken == null) {
			return tokenService.createAuthenticationToken(user.getUserId(), email);
		} else {
			return existToken.getToken();
		}
	}

	/**
	 * 登录失败后执行的方法
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		if (isLoginRequest(request, response)) {
			HttpUtil.outputJsonMessage(HttpUtil.convertServletResponseToHttp(response), Constants.MESSAGE_STATUS,
					HttpStatus.OK.code(), Constants.MESSAGE, "unauthorized", Constants.FAILED_REASON,
					e.getLocalizedMessage());
		} else {
			HttpUtil.outputErrorMessage(HttpUtil.convertServletResponseToHttp(response), HttpStatus.UNAUTHORIZED);
		}
		return false;
	}

	/**
	 * 是否允许访问
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		return !(!isLoginRequest(request, response) && isPermissive(mappedValue) && hasAuthorizationToken(request))
				&& (super.isAccessAllowed(request, response, mappedValue) || (!isLoginRequest(request, response)
						&& isPermissive(mappedValue) && !hasAuthorizationToken(request)));
	}
}
