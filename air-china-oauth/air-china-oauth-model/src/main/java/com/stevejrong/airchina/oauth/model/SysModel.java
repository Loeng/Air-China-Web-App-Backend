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
 * Model - t_base_sys
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午12:14:02
 */
public class SysModel extends BaseModel {
	private String sysItemId; // 用户身份识别码
	private String sysItemCode; // 系统设置项代码
	private String sysItemName; // 系统设置项名称
	private String sysItemDescription; // 系统设置项描述
	private String sysItemValue; // 系统设置项值
	private Integer state; // 状态(使用-1,禁用-0)

	public SysModel() {
	}

	public SysModel(String sysItemId, String sysItemCode, String sysItemName, String sysItemDescription,
			String sysItemValue, Integer state) {
		this.sysItemId = sysItemId;
		this.sysItemCode = sysItemCode;
		this.sysItemName = sysItemName;
		this.sysItemDescription = sysItemDescription;
		this.sysItemValue = sysItemValue;
		this.state = state;
	}

	public String getSysItemId() {
		return sysItemId;
	}

	public void setSysItemId(String sysItemId) {
		this.sysItemId = sysItemId;
	}

	public String getSysItemCode() {
		return sysItemCode;
	}

	public void setSysItemCode(String sysItemCode) {
		this.sysItemCode = sysItemCode;
	}

	public String getSysItemName() {
		return sysItemName;
	}

	public void setSysItemName(String sysItemName) {
		this.sysItemName = sysItemName;
	}

	public String getSysItemDescription() {
		return sysItemDescription;
	}

	public void setSysItemDescription(String sysItemDescription) {
		this.sysItemDescription = sysItemDescription;
	}

	public String getSysItemValue() {
		return sysItemValue;
	}

	public void setSysItemValue(String sysItemValue) {
		this.sysItemValue = sysItemValue;
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
