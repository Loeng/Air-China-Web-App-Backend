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
package com.stevejrong.airchina.oauth.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stevejrong.airchina.oauth.mapper.RoleMapper;
import com.stevejrong.airchina.oauth.model.RoleModel;
import com.stevejrong.airchina.oauth.repository.RoleRepository;

/**
 * Repository Implementation - Role
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午8:38:40
 */
@Component("roleRepository")
public class RoleRepositoryImpl implements RoleRepository {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public RoleModel getOne() {
		return null;
	}

	@Override
	public List<RoleModel> listAll() {
		return null;
	}

	@Override
	public Long countAll() {
		return null;
	}

	@Override
	public void save(RoleModel object) {

	}

	@Override
	public void update(RoleModel object) {

	}

	@Override
	public void remove(RoleModel object) {

	}

	@Override
	public List<RoleModel> listAllRolesByUserId(String userId) {
		return roleMapper.listAllRolesByUserId(userId);
	}
}
