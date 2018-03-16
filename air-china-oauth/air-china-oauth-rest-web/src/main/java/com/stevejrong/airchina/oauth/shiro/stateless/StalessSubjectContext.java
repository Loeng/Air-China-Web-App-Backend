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

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.subject.support.DefaultSubjectContext;

/**
 * 扩展默认的会话上下文，使其失去支持管理Session会话的功能
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午4:54:48
 */
public class StalessSubjectContext extends DefaultSubjectContext {
	
	private static final long serialVersionUID = -2033693214819968402L;

	public StalessSubjectContext(SubjectContext ctx) {
		super(ctx);
	}

	public StalessSubjectContext() {
	}

	@Override
	public Session getSession() {
		return null;
	}

	@Override
	public boolean isSessionCreationEnabled() {
		return false;
	}
}
