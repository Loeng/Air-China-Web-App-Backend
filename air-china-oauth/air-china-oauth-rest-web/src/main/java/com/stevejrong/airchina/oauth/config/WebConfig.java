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
package com.stevejrong.airchina.oauth.config;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.stevejrong.airchina.oauth.rest.web.controller.AppErrorController;

/**
 * Configuration - Spring Web MVC
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月25日 下午10:55:55
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurationSupport {

	@Autowired
	private ErrorAttributes errorAttributes;

	/**
	 * 设置一个简单的策略：在不确定的情况下，使用所有默认值并返回JSON
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON_UTF8).mediaType("xml", MediaType.APPLICATION_JSON_UTF8)
				.mediaType("json", MediaType.APPLICATION_JSON);
	}

	/**
	 * 重写此方法以添加自定义httpmessageconverters使用RequestMappingHandlerAdapter和exceptionhandlerexceptionresolver。
	 * 向列表中添加转换器关闭默认注册的默认转换器。也看到adddefaulthttpmessageconverters(List)，可以用来添加默认消息转换器
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
		converters.add(new MappingJackson2XmlHttpMessageConverter(builder.createXmlMapper(true).build()));
	}

	/**
	 * 使用Jackson2ObjectMapperBuilder自定义XML和JSON消息转换器
	 */
	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder objectMapperBuilder = new Jackson2ObjectMapperBuilder();
		objectMapperBuilder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		return objectMapperBuilder;
	}

	/**
	 * 自定义异常控制器
	 * @return
	 */
	@Bean
	public AppErrorController appErrorController() {
		return new AppErrorController(errorAttributes);
	}
}
