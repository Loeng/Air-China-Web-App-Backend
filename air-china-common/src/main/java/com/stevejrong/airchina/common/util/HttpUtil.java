package com.stevejrong.airchina.common.util;
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


import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Util - HTTP工具类
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午1:18:37
 */
public final class HttpUtil {
	
	/**
	 * 输出错误信息
	 * @param response
	 * @param error
	 */
	public static void outputErrorMessage(HttpServletResponse response, HttpStatus error) {
		try {
			response.sendError(error.code());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 输出JSON消息
	 * @param response
	 * @param args
	 */
	public static void outputJsonMessage(HttpServletResponse response, Object... args) {
		response.setContentType(MimeType.JSON.type());
		response.setStatus(HttpStatus.OK.code());
		try {
			response.getWriter().println(JsonUtil.jsonMessageAppendFromArgs(args).toJSONString());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将 ServletRequest 对象 转换为 HttpServletRequest 对象
	 * @param request
	 * @return
	 */
	public static HttpServletRequest convertServletRequestToHttp(ServletRequest request) {
        return (HttpServletRequest) request;
    }
	
	/**
	 * 将 ServletResponse 对象 转换为 HttpServletResponse 对象
	 * @param request
	 * @return
	 */
	public static HttpServletResponse convertServletResponseToHttp(ServletResponse response) {
        return (HttpServletResponse) response;
    }
}
