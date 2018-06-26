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
package com.stevejrong.airchina.oauth;

import com.stevejrong.airchina.oauth.config.AppInitializerConfig;
import com.stevejrong.airchina.oauth.config.MyBatisConfig;
import com.stevejrong.airchina.oauth.config.ShiroConfig;
import com.stevejrong.airchina.oauth.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动类
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月21日 下午11:02:07
 */
@Import({ AppInitializerConfig.class, WebConfig.class, ShiroConfig.class, MyBatisConfig.class })
@ComponentScan(basePackages = { "com.stevejrong.airchina.oauth" })
@EnableDiscoveryClient
@EnableFeignClients
@EnableAsync
@SpringBootConfiguration
@SpringBootApplication
public class OauthBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(OauthBootApplication.class, args);
	}
}
