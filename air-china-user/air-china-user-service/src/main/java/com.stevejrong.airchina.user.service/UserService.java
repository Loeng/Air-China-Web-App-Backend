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
package com.stevejrong.airchina.user.service;

import com.stevejrong.airchina.user.model.UserModel;

/**
 * Service Interface- User
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年3月2日 下午8:12:39
 */
public interface UserService {

	/**
	 * 根据电子邮件地址获取用户信息
	 * 
	 * @param email 电子邮件地址
	 * @return
	 */
	UserModel getByEmail(String email);
}
