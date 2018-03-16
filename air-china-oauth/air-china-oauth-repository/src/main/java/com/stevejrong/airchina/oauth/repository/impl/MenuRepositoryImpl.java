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

import com.stevejrong.airchina.oauth.mapper.MenuMapper;
import com.stevejrong.airchina.oauth.model.MenuModel;
import com.stevejrong.airchina.oauth.repository.MenuRepository;

/**
 * Repository Implementation - Menu
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午9:21:42
 */
@Component("menuRepository")
public class MenuRepositoryImpl implements MenuRepository {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public MenuModel getOne() {
		return null;
	}

	@Override
	public List<MenuModel> listAll() {
		return null;
	}

	@Override
	public Long countAll() {
		return null;
	}

	@Override
	public void save(MenuModel object) {

	}

	@Override
	public void update(MenuModel object) {

	}

	@Override
	public void remove(MenuModel object) {

	}

	@Override
	public List<MenuModel> listAllMenusByUserId(String userId) {
		return menuMapper.listAllMenusByUserId(userId);
	}
}
