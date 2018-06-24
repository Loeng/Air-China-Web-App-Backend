package com.stevejrong.airchina.oauth.rest.common.web.bean;

/**
 * Bean - 用户授权
 *
 * @author Steve Jrong
 * @since 1.0 create date: 2018年06月24日 上午11:19
 */
public class AuthenticateBean {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
