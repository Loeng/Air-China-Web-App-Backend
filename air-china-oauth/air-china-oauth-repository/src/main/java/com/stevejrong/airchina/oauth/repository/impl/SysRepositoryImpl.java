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

import org.springframework.stereotype.Component;

import com.stevejrong.airchina.oauth.model.SysModel;
import com.stevejrong.airchina.oauth.repository.SysRepository;

/**
 * Repository Implementation - Sys
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年3月2日 下午2:10:07
 */
@Component("sysRepository")
public class SysRepositoryImpl implements SysRepository {

	@Override
	public SysModel getOne() {
		return null;
	}

	@Override
	public List<SysModel> listAll() {
		return null;
	}

	@Override
	public Long countAll() {
		return null;
	}

	@Override
	public void save(SysModel object) {

	}

	@Override
	public void update(SysModel object) {

	}

	@Override
	public void remove(SysModel object) {

	}
}
