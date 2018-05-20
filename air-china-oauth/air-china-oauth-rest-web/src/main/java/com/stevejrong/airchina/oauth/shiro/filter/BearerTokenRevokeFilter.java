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

import com.stevejrong.airchina.common.util.DateTimeUtil;
import com.stevejrong.airchina.common.util.HttpStatus;
import com.stevejrong.airchina.common.util.HttpUtil;
import com.stevejrong.airchina.oauth.common.constant.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filter - 路径匹配过滤器
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午4:21:17
 */
public class BearerTokenRevokeFilter extends PathMatchingFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(BearerTokenRevokeFilter.class);

	public BearerTokenRevokeFilter() {
		super();
	}

	public void setLogoutUrl(String logoutUrl) {
		this.appliedPaths.put(Constants.SHIRO_LOGOUT_SUFFIX, null);
	}

	/**
	 * 当匹配到包含设置的路径时，将在执行父类中isFilterChainContinued()方法之前执行此方法 此方法可在其中做日志记录或退出等操作
	 */
	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.logout();
		} catch (SessionException sessionException) {
			LOGGER.error("退出失败...{}", sessionException.getLocalizedMessage());
		}
		HttpUtil.outputJsonMessage(HttpUtil.convertServletResponseToHttp(response), Constants.MESSAGE_TIMESTAMP, DateTimeUtil.getTimestampByNow(),
				Constants.MESSAGE_CODE, HttpStatus.OK.code(), Constants.MESSAGE, null);
		return false;
	}
}
