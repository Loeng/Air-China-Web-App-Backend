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
package com.stevejrong.airchina.user.rest.web.controller;

import com.stevejrong.airchina.common.util.HttpStatus;
import com.stevejrong.airchina.common.wrapper.ResponseWrapper;
import com.stevejrong.airchina.user.common.constant.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller - 用户
 *
 * @author Steve Jrong
 * @since 1.0 create date: 2018年06月24日 上午10:59
 */
@Api(description = "用户API")
@RestController
@RequestMapping(Constants.REQUEST_ROOT_SUFFIX)
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * 获取所有用户
     * @return
     */
    @ApiOperation(value = "获取所有用户")
    @GetMapping("/list")
    @RequiresAuthentication
    @RequiresRoles("super-admin")
    public @ResponseBody
    Object listAllUsers() {
        return ResponseWrapper.response(HttpStatus.OK.code(), HttpStatus.OK.message(), "Microservice of user response mock data");
    }
}
