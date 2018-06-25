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

import com.stevejrong.airchina.oauth.mapper.TokenMapper;
import com.stevejrong.airchina.oauth.model.TokenModel;
import com.stevejrong.airchina.oauth.repository.TokenRepository;

/**
 * Repository Implementation - Token
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午2:12:25
 */
@Component("tokenRepository")
public class TokenRepositoryImpl implements TokenRepository {

	@Autowired
	private TokenMapper tokenMapper;

	@Override
	public TokenModel getOne() {
		return null;
	}

	@Override
	public List<TokenModel> listAll() {
		return null;
	}

	@Override
	public Long countAll() {
		return null;
	}

	@Override
	public void save(TokenModel object) {
		tokenMapper.insert(object);
	}

	@Override
	public void update(TokenModel object) {
		tokenMapper.update(object);
	}

	@Override
	public void remove(TokenModel object) {

	}

	@Override
	public TokenModel getByEmail(String email) {
		return tokenMapper.getByEmail(email);
	}

	@Override
	public TokenModel getByUserId(String userId) {
		return tokenMapper.getByUserId(userId);
	}
}
