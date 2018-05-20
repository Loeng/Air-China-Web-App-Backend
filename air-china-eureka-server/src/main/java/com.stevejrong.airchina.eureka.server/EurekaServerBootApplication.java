package com.stevejrong.airchina.eureka.server;/**
 * Copyright 2018 Steve Jrong - https://www.stevejrong.top
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Server启动类
 *
 * @author Steve Jrong
 * @since 1.0 create date: 2018年05月20日 下午6:06
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerBootApplication.class, args);
    }
}
