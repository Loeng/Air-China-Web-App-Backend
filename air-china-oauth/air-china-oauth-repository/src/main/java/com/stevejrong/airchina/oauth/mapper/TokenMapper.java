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
package com.stevejrong.airchina.oauth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.stevejrong.airchina.oauth.model.TokenModel;

/**
 * Mapper - t_base_token
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月25日 下午11:07:50
 */
public interface TokenMapper {
	String listAll = "select * from t_base_token";
	String getByToken = "select * from t_base_token where token = #{token}";
	String getByUserId = "select * from t_base_token t where t.user_id = #{userId}";
	String getByEmail = "select * from t_base_token t where t.email = #{email}";
	String deleteByToken = "delete from t_base_token where token = #{token}";
	String insertToken = "insert into t_base_token (id, gmt_create, gmt_modified, user_id, email, token, state) values (default, now(), null, #{userId}, #{email}, #{token}, #{state})";
	String updateToken = "update t_base_token set gmt_modified = #{gmt_modified}, token = #{token} where user_id = #{userId}";

	@Select(listAll)
	@Results(value = { 
			@Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "token", column = "token"),
            @Result(property = "state", column = "state")
	})
	List<TokenModel> listAll();
	
	@Select(getByToken)
    @Results(value = {
    		@Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "token", column = "token"),
            @Result(property = "state", column = "state")
    })
	TokenModel getByToken(String token);
	
	/**
	 * 根据电子邮件地址获取令牌信息
	 * @param email 电子邮件地址
	 * @return
	 */
	@Select(getByEmail)
	@Results(value = {
			@Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "token", column = "token"),
            @Result(property = "state", column = "state")
    })
	TokenModel getByEmail(String email);
	
	/**
	 * 根据电子邮件地址获取令牌信息
	 * @param email 电子邮件地址
	 * @return
	 */
	@Select(getByUserId)
	@Results(value = {
			@Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "token", column = "token"),
            @Result(property = "state", column = "state")
    })
	TokenModel getByUserId(String userId);
	
    @Delete(deleteByToken)
    void deleteByToken(String token);

    @Insert(insertToken)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(TokenModel token);
}
