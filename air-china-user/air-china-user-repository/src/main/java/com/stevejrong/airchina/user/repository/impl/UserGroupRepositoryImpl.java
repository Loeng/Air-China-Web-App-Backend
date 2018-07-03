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
package com.stevejrong.airchina.user.repository.impl;

import com.stevejrong.airchina.user.model.UserGroupModel;
import com.stevejrong.airchina.user.repository.UserGroupRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Repository Implementation - User Group
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年3月2日 下午2:13:46
 */
@Component
public class UserGroupRepositoryImpl implements UserGroupRepository {

	@Override
	public UserGroupModel getOne() {
		return null;
	}

	@Override
	public List<UserGroupModel> listAll() {
		return null;
	}

	@Override
	public Long countAll() {
		return null;
	}

	@Override
	public void save(UserGroupModel object) {

	}

	@Override
	public void update(UserGroupModel object) {

	}

	@Override
	public void remove(UserGroupModel object) {

	}
}
