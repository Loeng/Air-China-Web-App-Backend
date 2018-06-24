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
package com.stevejrong.airchina.oauth.rest.web.controller;

import com.stevejrong.airchina.oauth.common.constant.Constants;
import com.stevejrong.airchina.oauth.rest.common.web.bean.AuthenticateBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller - 权限验证
 *
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月27日 上午11:58:34
 */
@Api(description = "权限验证API")
@RestController
@RequestMapping(Constants.REQUEST_ROOT_SUFFIX)
public class OauthRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OauthRestController.class);

    /**
     * 用户授权
     *
     * @param credentials
     */
    @ApiOperation(value = "用户授权")
    @PostMapping("/authenticate")
    public void authenticate(@ApiParam(required = true, name = "credentials", value = "登录凭证")
                             @RequestBody AuthenticateBean credentials) {
    }
}
