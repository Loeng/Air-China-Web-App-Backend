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
package com.stevejrong.airchina.oauth.model;

import com.stevejrong.airchina.oauth.common.model.BaseModel;

/**
 * Model - t_base_role
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午12:05:02
 */
public class RoleModel extends BaseModel {
	private String roleId; // 角色编号
	private String roleCode; // 角色代码
	private String roleName; // 角色名称
	private Integer state; // 角色状态-使用:1,禁用:0

	public RoleModel() {
	}

	public RoleModel(String roleId, String roleCode, String roleName, Integer state) {
		this.roleId = roleId;
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.state = state;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
