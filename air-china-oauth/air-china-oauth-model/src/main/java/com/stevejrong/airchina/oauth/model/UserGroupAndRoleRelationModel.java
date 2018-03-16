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
 * Model - t_base_user_group_base_role_rela
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午12:07:03
 */
public class UserGroupAndRoleRelationModel extends BaseModel {
	private String userGroupId; // 用户组编号
	private String roleId; // 角色编号

	public UserGroupAndRoleRelationModel() {
	}

	public UserGroupAndRoleRelationModel(String userGroupId, String roleId) {
		this.userGroupId = userGroupId;
		this.roleId = roleId;
	}

	public String getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
