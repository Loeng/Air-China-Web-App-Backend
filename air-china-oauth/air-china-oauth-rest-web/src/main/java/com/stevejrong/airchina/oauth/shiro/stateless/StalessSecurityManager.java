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
package com.stevejrong.airchina.oauth.shiro.stateless;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

/**
 * 无状态安全管理器
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午4:53:34
 */
public class StalessSecurityManager extends DefaultWebSecurityManager {
	public StalessSecurityManager() {
		setSubjectFactory(new StalessSubjectFactory());
		DefaultSubjectDAO subjectDAO = (DefaultSubjectDAO) this.getSubjectDAO();
		DefaultSessionStorageEvaluator sessionStorageEvaluator = (DefaultSessionStorageEvaluator) subjectDAO
				.getSessionStorageEvaluator();
		sessionStorageEvaluator.setSessionStorageEnabled(false); // 对于无状态的会话管理，关闭Session存储方式
		this.setRememberMeManager(null); // 取消记住用户管理器
	}

	@Override
	protected SubjectContext createSubjectContext() {
		return new StalessSubjectContext();
	}

	@Override
	protected SubjectContext copy(SubjectContext subjectContext) {
		if (subjectContext instanceof StalessSubjectContext) {
			return new StalessSubjectContext(subjectContext);
		}
		return super.copy(subjectContext);
	}
}
