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

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * 自定义Subject工厂设置其不创建Session
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月28日 下午4:14:26
 */
public class StalessSubjectFactory extends DefaultWebSubjectFactory {
	public Subject createSubject(SubjectContext context) {
		// 不创建session, 如果之后调用Subject.getSession()将抛出DisabledSessionException异常
		context.setSessionCreationEnabled(false);
		return super.createSubject(context);
	}
}
