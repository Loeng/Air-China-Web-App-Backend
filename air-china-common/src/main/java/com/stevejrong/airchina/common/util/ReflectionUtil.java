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

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * Util - 反射工具类
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年3月2日 上午10:59:02
 */
public final class ReflectionUtil {

	/**
	 * 获取父类和当前类的所有属性和属性值并转换为JSON字符串输出
	 * @param clazz 需要获取属性的类
	 * 
	 * @return 属性和属性值并转换为JSON字符串
	 */
	public static String getAllPropertiesForClassToJsonString(Object clazz) {
		JSONObject jsonObject = new JSONObject();
		
		synchronized (clazz) {
			try {
				for (Field field : clazz.getClass().getSuperclass().getDeclaredFields()) {
					field.setAccessible(true);
					jsonObject.put(field.getName(), Optional.ofNullable(field.get(clazz)));
				}
				for (Field field : clazz.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					jsonObject.put(field.getName(), Optional.ofNullable(field.get(clazz)));
				}
			} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return jsonObject.toString();
	}
}
