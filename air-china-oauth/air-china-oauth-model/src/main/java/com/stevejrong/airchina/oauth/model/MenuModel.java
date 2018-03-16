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
 * Model - t_base_menu
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午12:08:36
 */
public class MenuModel extends BaseModel {
	private String menuId; // 菜单编号
	private String menuName; // 菜单名称
	private Integer menuType; // 菜单类型(保留字段:1-系统菜单,2-其他菜单)
	private Integer isParentMenu; // 是否为父级菜单(1-是,0-否)
	private Integer isRootMenu; // 是否为根菜单(1-是,0-否)
	private String parentMenuId; // 父级菜单编号
	private Integer order; // 菜单顺序
	private String requestUrl; // 访问地址

	public MenuModel() {
	}

	public MenuModel(String menuId, String menuName, Integer menuType, Integer isParentMenu, Integer isRootMenu,
			String parentMenuId, Integer order, String requestUrl) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuType = menuType;
		this.isParentMenu = isParentMenu;
		this.isRootMenu = isRootMenu;
		this.parentMenuId = parentMenuId;
		this.order = order;
		this.requestUrl = requestUrl;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public Integer getIsParentMenu() {
		return isParentMenu;
	}

	public void setIsParentMenu(Integer isParentMenu) {
		this.isParentMenu = isParentMenu;
	}

	public Integer getIsRootMenu() {
		return isRootMenu;
	}

	public void setIsRootMenu(Integer isRootMenu) {
		this.isRootMenu = isRootMenu;
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
