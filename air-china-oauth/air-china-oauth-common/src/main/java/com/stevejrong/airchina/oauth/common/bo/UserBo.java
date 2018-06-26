package com.stevejrong.airchina.oauth.common.bo;

/**
 * Bo - User
 *
 * @author Steve Jrong
 * @since 1.0 create date: 2018年06月26日 下午9:02
 */
public class UserBo {
    private String userId; // 用户识别码（具有唯一性）
    private String userName; // 用户姓名
    private String password; // 用户密码
    private String email; // 用户电子邮件地址
    private Integer age; // 用户年龄
    private Integer gender; // 用户性别(1-男,0-女)
    private String telephone; // 用户联系电话
    private String address; // 联系地址

    public UserBo(String userId, String userName, String password, String email, Integer age, Integer gender, String telephone, String address) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.telephone = telephone;
        this.address = address;
    }

    public UserBo() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
