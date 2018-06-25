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

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Util - HTTP工具类
 *
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午1:18:37
 */
public final class HttpUtil {

	private static final ThreadLocal<OkHttpClient> httpClientThreadLocal = new ThreadLocal<OkHttpClient>();

	/**
	 * 获取线程安全的OkHttpClient实例
	 *
	 * @return 线程安全的OkHttpClient实例
	 */
	private static OkHttpClient getOkHttpClientInstance() {
		OkHttpClient httpClient = httpClientThreadLocal.get();
		if (null == httpClient) {
			httpClient = new OkHttpClient();
			httpClientThreadLocal.set(httpClient);
		}
		return httpClient;
	}


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
	 * @param response
	 * @return
	 */
	public static HttpServletResponse convertServletResponseToHttp(ServletResponse response) {
		return (HttpServletResponse) response;
	}

	/**
	 * 以同步方式发起Get请求（内部方法）
	 *
	 * @param url 请求地址
	 * @param headerParams 请求头参数Map
	 * @param requestParams 请求参数Map
	 * @return ResponseBody响应体
	 */
	private static ResponseBody getSync(String url, Map<String, String> headerParams, Map<String, String> requestParams){
		ResponseBody result = null;
		OkHttpClient httpClient = getOkHttpClientInstance();

		// 添加请求头参数
		Request.Builder requestBuilder = new Request.Builder();
		headerParams.forEach(requestBuilder::addHeader);

		// URL参数拼接
		StringBuffer urlParamsStr = new StringBuffer("");
		if (null != requestParams) {
			urlParamsStr.append("?");
			requestParams.forEach((requestKey, requestVal) -> {
				urlParamsStr.append(requestKey).append("=").append(requestVal);
			});
		}

		Request request = requestBuilder.url(url + urlParamsStr).build();

		Call call = httpClient.newCall(request);

		try {
			Response response = call.execute();
			result = response.body();
		} catch (IOException e){
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 以同步方式发起Post请求（内部方法）
	 *
	 * @param url 请求地址
	 * @param headerParams 请求头参数Map
	 * @param bodyParams 请求参数Map
	 * @return ResponseBody响应体
	 */
	private static ResponseBody postSync(String url, Map<String, String> headerParams, Map<String, String> bodyParams){
		ResponseBody result = null;
		OkHttpClient httpClient = getOkHttpClientInstance();

		// 添加请求头参数
		Request.Builder requestBuilder = new Request.Builder();
		headerParams.forEach(requestBuilder::addHeader);

		// 添加请求参数到Body中
		JSONObject jsonObject = new JSONObject();
		bodyParams.forEach(jsonObject::put);

		// 创建RequestBody对象并使用JSON格式进行封装
		RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString());

		Request request = new Request.Builder().post(requestBody).url(url).build();

		Call call = httpClient.newCall(request);

		try {
			Response response = call.execute();
			result = response.body();
		} catch (IOException e){
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 以同步方式发起无请求头且带参的Get请求
	 *
	 * @param url 请求地址
	 * @param requestParams 请求参数Map
	 * @return JSON格式响应结果
	 */
	public static JSONObject launchGetSync(String url, Map<String, String> requestParams){
		JSONObject result = null;
		try {
			result = JSONObject.parseObject(getSync(url, null, requestParams).string());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 以同步方式发起有请求头的且带参的Get请求
	 *
	 * @param url 请求地址
	 * @param headerParams 请求头参数Map
	 * @param requestParams 请求参数Map
	 * @return JSON格式响应结果
	 */
	public static JSONObject launchGetSync(String url, Map<String, String> headerParams, Map<String, String> requestParams){
		JSONObject result = null;
		try {
			result = JSONObject.parseObject(getSync(url, headerParams, requestParams).string());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 以同步方式发起无请求头且带参的Post请求
	 *
	 * @param url 请求地址
	 * @param bodyParams 请求参数Map
	 * @return JSON格式响应结果
	 */
	public static JSONObject launchPostSync(String url, Map<String, String> bodyParams){
		JSONObject result = null;
		try {
			result = JSONObject.parseObject(postSync(url, null, bodyParams).string());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 以同步方式发起有请求头的且带参的Post请求
	 *
	 * @param url 请求地址
	 * @param headerParams 请求头参数Map
	 * @param bodyParams 请求参数Map
	 * @return JSON格式响应结果
	 */
	public static JSONObject launchPostSync(String url, Map<String, String> headerParams, Map<String, String> bodyParams){
		JSONObject result = null;
		try {
			result = JSONObject.parseObject(postSync(url, headerParams, bodyParams).string());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}