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
package com.stevejrong.airchina.user.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Configuration - 初始化器
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月25日 下午2:43:36
 */
public class AppInitializerConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * 获取Spring应用容器的配置文件
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WebConfig.class };
	}

	/**
	 * 获取Spring MVC应用容器
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	/**
	 * 指定由DispatcherServlet映射的路径
	 * 此处处理所有请求
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}
