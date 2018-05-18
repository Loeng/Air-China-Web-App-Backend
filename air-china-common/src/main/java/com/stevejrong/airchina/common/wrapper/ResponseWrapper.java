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
package com.stevejrong.airchina.common.wrapper;

import java.io.Serializable;

/**
 * 消息封装类
 *
 * @param <T>
 * @author Steve Jrong
 * @since 1.0 create date: 2018年5月16日 下午8:47:14
 */
public class ResponseWrapper<T> implements Serializable {
    private Integer code; // 操作码

    private String message; // 操作消息

    private T data; // 数据

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    private ResponseWrapper(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private ResponseWrapper(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseWrapper response(Integer code, String message) {
        return new ResponseWrapper(code, message);
    }

    public static ResponseWrapper response(Integer code, String message, Object data) {
        return new ResponseWrapper(code, message, data);
    }
}
