package com.stevejrong.airchina.oauth.common.constant;

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

/**
 * 异常相关枚举
 *
 * @author Steve Jrong
 * @since 1.0 create date: 2018年5月26日 下午10:52
 */
public enum ExceptionConstantsEnum {
    TOKEN_EXPIRED(1000, "Token已过期，请您重新登录"),
    TOKEN_NOT_EXIST(1001, "Token不存在或无效"),
    HEADER_PARAMS_INCORRECT(2000, "请求头参数错误"),
    UNKNOWN_EXPERTION(-1, "未知错误");

    private Integer exceptionCode; // 异常代码

    private String exceptionMessage; // 异常信息

    private ExceptionConstantsEnum(Integer exceptionCode, String exceptionMessage) {
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
    }

    public Integer exceptionCode() {
        return exceptionCode;
    }

    public String exceptionMessage() {
        return exceptionMessage;
    }
}
