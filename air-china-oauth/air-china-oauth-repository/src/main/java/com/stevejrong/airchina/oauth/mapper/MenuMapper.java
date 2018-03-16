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

import com.stevejrong.airchina.oauth.model.MenuModel;

/**
 * Mapper - t_base_menu
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午9:14:28
 */
public interface MenuMapper {
	String listAllMenusByUserId =
			"SELECT menu.* FROM t_base_menu_base_role_rela menu_role INNER JOIN t_base_menu menu ON menu_role.menu_id = menu.menu_id " + 
			"WHERE EXISTS " +
			"("+
			"	SELECT ugroup_urole.role_id FROM t_base_user_group_base_role_rela ugroup_urole WHERE EXISTS " +
			"	(" +
			"		SELECT u_ugroup.user_group_id FROM t_base_user_base_user_group_rela u_ugroup WHERE u_ugroup.user_id = #{userId} AND u_ugroup.user_group_id = ugroup_urole.user_group_id" +
			"	) AND ugroup_urole.role_id = menu_role.role_id" +
			")";
	
	@Select(listAllMenusByUserId)
	@Results(value = { 
			@Result(property = "id", column = "id"), 
			@Result(property = "gmtCreate", column = "gmt_create"),
			@Result(property = "gmtModified", column = "gmt_modified"),
			@Result(property = "menuId", column = "menu_id"),
			@Result(property = "menuName", column = "menu_name"),
			@Result(property = "menuType", column = "menu_type"),
			@Result(property = "isParentMenu", column = "is_parent_menu"),
			@Result(property = "isRootMenu", column = "is_root_menu"),
			@Result(property = "parentMenuId", column = "parent_menu_id"),
			@Result(property = "order", column = "order"),
			@Result(property = "requestUrl", column = "request_url")
	})
	List<MenuModel> listAllMenusByUserId(String userId);
	
}
