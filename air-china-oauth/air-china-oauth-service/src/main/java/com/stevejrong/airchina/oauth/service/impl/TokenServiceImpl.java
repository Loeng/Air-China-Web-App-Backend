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
package com.stevejrong.airchina.oauth.service.impl;

import com.stevejrong.airchina.oauth.common.constant.Constants;
import com.stevejrong.airchina.oauth.model.TokenModel;
import com.stevejrong.airchina.oauth.repository.TokenRepository;
import com.stevejrong.airchina.oauth.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Repository Implementation - Token
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午2:02:06
 */
@Service("tokenService")
public class TokenServiceImpl implements TokenService {

	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public String createAuthenticationToken(String userId, String email) {
		LocalDate currentDate = LocalDate.now();
		LocalDate sevenDaysLaterDate = currentDate.plusDays(7); // 当前时间7天后的时间
		Date date = Date.from(sevenDaysLaterDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		String token = Jwts.builder().setSubject(email).setExpiration(date)
				.signWith(SignatureAlgorithm.HS256, Constants.SECURET.getBytes()).compact();

		TokenModel tokenModel = new TokenModel(userId, email, token, 1);
		tokenRepository.save(tokenModel);
		
		return token;
	}

	@Override
	public TokenModel getByUserId(String userId) {
		return tokenRepository.getByUserId(userId);
	}

	@Override
	public TokenModel getByEmail(String email) {
		return tokenRepository.getByEmail(email);
	}
}
