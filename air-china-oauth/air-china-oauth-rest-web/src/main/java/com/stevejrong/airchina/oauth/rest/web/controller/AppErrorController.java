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
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Controller - 全局异常处理控制器
 *
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午12:18:14
 */
@RestController
@EnableWebMvc
public class AppErrorController implements ErrorController {

    private static final String ERROR_ATTRIBUTE = DefaultErrorAttributes.class.getName() + ".ERROR";

    private ErrorAttributes errorAttributes;

    public AppErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    /**
     * error异常请求统一处理
     *
     * @param request HttpServletRequest对象
     * @return JSON格式的响应体。格式为：{ timestamp:[时间戳], status:[HTTP状态码], error:[异常原因], message:[异常详细信息], path:[异常请求路径] }
     */
    @RequestMapping(value = Constants.ERROR_SUFFIX, produces = Constants.APPLICATION_JSON_UTF8)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request);
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(body, status);
    }

    /**
     * 返回异常跳转地址
     */
    @Override
    public String getErrorPath() {
        return Constants.ERROR_SUFFIX;
    }

    /**
     * 自定义处理异常发生后需要返回的异常信息
     *
     * @param request HttpServletRequest对象
     * @return
     */
    private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        Integer code = null;
        String exceptionReason = null;
        Long timestamp = null;

        Throwable exception = (Throwable) requestAttributes.getAttribute(ERROR_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        if (exception == null) {
            exception = (Throwable) requestAttributes.getAttribute("javax.servlet.error.exception", RequestAttributes.SCOPE_REQUEST);
        }

        if (null != exception) {
            // 通过反射获取Throwable对象中的code和message
            Field[] fields = exception.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                try {
                    Field field = fields[i]; // 得到属性
                    field.setAccessible(true); // 允许私有访问
                    String name = field.getName(); // 获取属性名
                    Object value = field.get(exception); // 获取属性值

                    if ("rootCause".equals(name)) {
                        Field[] subFields = value.getClass().getDeclaredFields();
                        for (int j = 0; j < subFields.length; j++) {
                            Field subField = subFields[j]; // 得到属性
                            subField.setAccessible(true); // 允许私有访问
                            String subFieldName = subField.getName(); // 获取属性名
                            Object subFieldValue = subField.get(value); // 获取属性值

                            if ("timestamp".equals(subFieldName)) {
                                timestamp = Long.valueOf(subFieldValue.toString());
                                continue;
                            }

                            if ("code".equals(subFieldName)) {
                                code = Integer.valueOf(subFieldValue.toString());
                                continue;
                            }

                            if ("message".equals(subFieldName)) {
                                exceptionReason = subFieldValue.toString();
                                continue;
                            }
                        }

                        break;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            errorAttributes.put("timestamp", timestamp);
            errorAttributes.put("code", code);
            errorAttributes.put("message", exceptionReason);
        }

        return errorAttributes;
    }

    /**
     * 获取HTTP状态码
     *
     * @param request HttpServletRequest对象
     * @return HttpStatus对象
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception ex) {
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
