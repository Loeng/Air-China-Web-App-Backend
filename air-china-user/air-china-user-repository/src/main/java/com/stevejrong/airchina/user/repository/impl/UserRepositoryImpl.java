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

import com.stevejrong.airchina.user.mapper.UserMapper;
import com.stevejrong.airchina.user.model.UserModel;
import com.stevejrong.airchina.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Repository Implementation - User
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午11:36:56
 */
@Component("userRepository")
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserModel getOne() {
		return null;
	}

	@Override
	public List<UserModel> listAll() {
		return userMapper.listAll();
	}

	@Override
	public Long countAll() {
		return null;
	}

	@Override
	public void save(UserModel object) {

	}

	@Override
	public void update(UserModel object) {

	}

	@Override
	public void remove(UserModel object) {

	}

	@Override
	public UserModel getByEmail(String email) {
		return userMapper.getByEmail(email);
	}
}
