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
package com.stevejrong.airchina.user.model;

import com.stevejrong.airchina.user.common.model.BaseModel;

/**
 * Model - t_base_user
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 上午11:56:51
 */
public class UserModel extends BaseModel {
	private String userId; // 用户识别码（具有唯一性）
	private String userName; // 用户姓名
	private String password; // 用户密码
	private String email; // 用户电子邮件地址
	private Integer age; // 用户年龄
	private Integer gender; // 用户性别(1-男,0-女)
	private String telephone; // 用户联系电话
	private String address; // 联系地址

	public UserModel() {
	}

	public UserModel(String userId, String userName, String password, String email, Integer age, Integer gender,
			String telephone, String address) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.age = age;
		this.gender = gender;
		this.telephone = telephone;
		this.address = address;
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

	@Override
	public String toString() {
		return super.toString();
	}
}
