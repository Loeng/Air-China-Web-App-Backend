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
package com.stevejrong.airchina.user.common.repository;

import java.util.List;

/**
 * Repository - 基类
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午12:43:16
 */
public interface BaseRepository<T> {

	/**
	 * 获取单个对象
	 * 
	 * @return
	 */
	T getOne();

	/**
	 * 获取对象集合
	 * 
	 * @return List对象集合
	 */
	List<T> listAll();

	/**
	 * 获取数据总数
	 * 
	 * @return Long类型的数据总数
	 */
	Long countAll();

	/**
	 * 保存单个对象
	 */
	void save(T object);

	/**
	 * 更新单个对象
	 * 
	 * @param object
	 */
	void update(T object);

	/**
	 * 移除单个对象
	 * 
	 * @param object
	 */
	void remove(T object);
}
