/**
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
package com.stevejrong.airchina.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration - Swagger
 *
 * @author Steve Jrong
 * @since 1.0 create date: 2018年06月24日 上午10:04
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket createRestApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name("Authorization").description("权限认证Bearer Token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(true).build();
        pars.add(ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("UserGroup")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stevejrong.airchina.user.rest.web.controller"))
                .paths(PathSelectors.ant("/user/**"))
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Air-China机票预订Web App的用户应用").description("GitHub：https://github.com/SteveJrong/Air-China-Web-App-Backend").termsOfServiceUrl("no terms of service")
                .version("1.0.0")
                .build();
    }
}
