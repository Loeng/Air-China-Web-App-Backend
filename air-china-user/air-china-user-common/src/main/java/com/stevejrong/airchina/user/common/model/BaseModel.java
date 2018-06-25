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
package com.stevejrong.airchina.user.common.model;

import com.stevejrong.airchina.common.util.ReflectionUtil;

import java.util.Date;

/**
 * Model - 基类
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月25日 下午11:51:37
 */
public class BaseModel {
	protected Integer id; // 主键

	protected Date gmtCreate; // 创建时间

	protected Date gmtModified; // 修改时间

	protected Integer getId() {
		return id;
	}

	protected void setId(Integer id) {
		this.id = id;
	}

	protected Date getGmtCreate() {
		return gmtCreate;
	}

	protected void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	protected Date getGmtModified() {
		return gmtModified;
	}

	protected void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	@Override
	public String toString() {
		return ReflectionUtil.getAllPropertiesForClassToJsonString(this);
	}
}
