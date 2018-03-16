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
package com.stevejrong.airchina.oauth.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.stevejrong.airchina.oauth.shiro.filter.BearerTokenAuthenticatingFilter;
import com.stevejrong.airchina.oauth.shiro.filter.BearerTokenRevokeFilter;
import com.stevejrong.airchina.oauth.shiro.realm.BearerTokenAuthenticatingRealm;
import com.stevejrong.airchina.oauth.shiro.realm.DatabaseRealm;
import com.stevejrong.airchina.oauth.shiro.stateless.StalessSecurityManager;

/**
 * Configuration - Shiro
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月25日 下午3:45:36
 */
@Configuration
public class ShiroConfig {

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager());
		shiroFilter.setUnauthorizedUrl("/403");

		Map<String, String> filterChainDefinitionMapping = new HashMap<>();
		filterChainDefinitionMapping.put("/users/auth", "tokenAuthc");
		filterChainDefinitionMapping.put("/users/list", "tokenAuthc");
		filterChainDefinitionMapping.put("/users/logout", "tokenAuthc,tokenLogout");
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);

		Map<String, Filter> filters = new HashMap<String, Filter>();
		filters.put("tokenAuthc", bearerTokenAuthenticatingFilter());
		filters.put("tokenLogout", bearerTokenRevokeFilter());
		shiroFilter.setFilters(filters);

		return shiroFilter;
	}

	/**
	 * 创建安全管理器Bean
	 * 
	 * @return
	 */
	@Bean
	public org.apache.shiro.mgt.SecurityManager securityManager() {
		DefaultSecurityManager securityManager = new StalessSecurityManager();
		Collection<Realm> realms = Arrays.asList(bearerTokenAuthenticatingRealm(), databaseRealm());
		securityManager.setRealms(realms);
		securityManager.setSessionManager(sessionManager());
		org.apache.shiro.SecurityUtils.setSecurityManager(securityManager);
		return securityManager;
	}

	/**
	 * 创建Session管理器Bean并通过sessionValidationSchedulerEnabled禁用掉会话调度器，因为我们禁用掉了会话，所以没必要再定期过期会话了。
	 * 
	 * @return
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		final DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionValidationSchedulerEnabled(false);
		return sessionManager;
	}

	/**
	 * 设置lifecycleBeanPostProcessor类来自动的对Bean的生命周期进行管理
	 * 
	 * @return
	 */
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public BearerTokenAuthenticatingRealm bearerTokenAuthenticatingRealm() {
		final BearerTokenAuthenticatingRealm realm = new BearerTokenAuthenticatingRealm();
		return realm;
	}

	/**
	 * 设置自定义认证数据源Bean
	 * 
	 * @return
	 */
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DatabaseRealm databaseRealm() {
		final DatabaseRealm realm = new DatabaseRealm();
		realm.setCredentialsMatcher(credentialsMatcher());
		return realm;
	}

	/**
	 * 创建凭证匹配器Bean以实现密码加密和比对
	 * 
	 * @return
	 */
	@Bean(name = "credentialsMatcher")
	public PasswordMatcher credentialsMatcher() {
		final PasswordMatcher credentialsMatcher = new PasswordMatcher();
		credentialsMatcher.setPasswordService(passwordService());
		return credentialsMatcher;
	}

	/**
	 * 创建密码服务以实现密码加密和比对
	 * Shiro默认使用SHA256加密方式使用特定的Slat值结合生成一串不可逆的散列值作为密码存储在数据库中
	 * 
	 * @return
	 */
	@Bean(name = "passwordService")
	public DefaultPasswordService passwordService() {
		return new DefaultPasswordService();
	}

	/**
	 * 设置 lifecycleBeanPostProcessor 类来自动的对Bean的生命周期进行管理
	 * 
	 * @return
	 */
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 自定义Token权限过滤器以支持Token验证
	 * 
	 * @return
	 */
	@Bean
	public BearerTokenAuthenticatingFilter bearerTokenAuthenticatingFilter() {
		BearerTokenAuthenticatingFilter filter = new BearerTokenAuthenticatingFilter();
		filter.setUserNameParam("username"); // 创建Token时的用户名（电子邮件地址）入参
		filter.setPasswordParam("password"); // 创建Token时的用户密码入参
		filter.setLoginUrl("/users/auth"); // 获取新Token的地址
		return filter;
	}

	@Bean
	public BearerTokenRevokeFilter bearerTokenRevokeFilter() {
		return new BearerTokenRevokeFilter();
	}

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	/**
	 * 设置AuthorizationAttributeSourceAdvisor类以解析Shiro注解
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * 针对多个认证数据源的管理器
	 * 
	 * @return
	 */
	// @Bean
	// public ModularRealmAuthenticator modularRealmAuthenticator() {
	// ModularRealmAuthenticator modularRealmAuthenticator = new
	// ModularRealmAuthenticator();
	// modularRealmAuthenticator.setAuthenticationStrategy(new
	// AtLeastOneSuccessfulStrategy());
	// return modularRealmAuthenticator;
	// }
}
