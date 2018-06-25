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
package com.stevejrong.airchina.user.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Configuration - MyBatis
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月25日 下午3:13:05
 */
@Configuration
@MapperScan("com.stevejrong.airchina.user.mapper")
public class MyBatisConfig {

	@Value("${jdbc.driver}")
	private String driver;

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	@Value("${jdbc.maxActive}")
	private int maxActive;

	@Value("${jdbc.maxIdel}")
	private int maxIdel;

	@Value("${jdbc.maxWait}")
	private long maxWait;

	/**
	 * 创建DataSource实例Bean
	 * 
	 * @return
	 */
	@Bean
	public DruidDataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setMaxActive(maxActive);
		dataSource.setMaxWait(maxWait);
		dataSource.setValidationQuery("SELECT 1");
		dataSource.setTestOnBorrow(true);
		return dataSource;
	}

	/**
	 * 创建事务管理器Bean
	 * 
	 * @return
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	/**
	 * 创建SQL Session工厂Bean
	 * 
	 * @return
	 */
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource());
		try {
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
