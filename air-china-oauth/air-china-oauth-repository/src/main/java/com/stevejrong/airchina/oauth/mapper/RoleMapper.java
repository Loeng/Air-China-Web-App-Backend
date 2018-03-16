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
package com.stevejrong.airchina.oauth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.stevejrong.airchina.oauth.model.RoleModel;

/**
 * Mapper - t_base_role
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午8:40:56
 */
public interface RoleMapper {
	String listAllRolesByUserId =
		"SELECT role.* FROM t_base_user_group_base_role_rela ugroup_urole INNER JOIN t_base_role role ON ugroup_urole.role_id = role.role_id WHERE EXISTS" +
		"(" +
			"SELECT u_ugroup.user_group_id FROM t_base_user_base_user_group_rela u_ugroup WHERE u_ugroup.user_id = #{userId} AND u_ugroup.user_group_id = ugroup_urole.user_group_id" +
		")";
	
	/**
	 * 根据电子邮件地址查询用户所有角色
	 * @param email 电子邮件地址
	 * @return
	 */
	@Select(listAllRolesByUserId)
	@Results(value = { 
			@Result(property = "id", column = "id"), 
			@Result(property = "gmtCreate", column = "gmt_create"),
			@Result(property = "gmtModified", column = "gmt_modified"),
			@Result(property = "roleId", column = "role_id"),
			@Result(property = "roleCode", column = "role_code"),
			@Result(property = "roleName", column = "role_name"),
			@Result(property = "state", column = "state")
	})
	List<RoleModel> listAllRolesByUserId(String userId);
	
}
