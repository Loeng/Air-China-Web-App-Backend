package com.stevejrong.airchina.oauth.exception;

import com.stevejrong.airchina.common.util.HttpStatus;
import org.apache.shiro.authz.UnauthenticatedException;

public class AirChinaUnauthenticatedException extends Exception {

    private Integer code; // 操作码

    private String message; // 消息

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AirChinaUnauthenticatedException() {
        super();
    }

    public AirChinaUnauthenticatedException(String message) {
        super(message);
        this.message = message;
        this.code = HttpStatus.UNAUTHORIZED.code();

    }

    public AirChinaUnauthenticatedException(Throwable cause) {
        super(cause);
    }

    public AirChinaUnauthenticatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
