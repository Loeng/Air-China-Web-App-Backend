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


import com.alibaba.fastjson.JSONObject;

/**
 * Util - JSON工具类
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午3:45:48
 */
public final class JsonUtil {

	/**
	 * 输出动态JSON消息时的动态参数拼接
	 * 
	 * @param args
	 * @return
	 */
	public static JSONObject jsonMessageAppendFromArgs(Object... args) {
		JSONObject obj = new JSONObject();
		for (int i = 0; i < args.length; i += 2) {
			obj.put((String) args[i], args[i + 1]);
		}
		return obj;
	}
}
