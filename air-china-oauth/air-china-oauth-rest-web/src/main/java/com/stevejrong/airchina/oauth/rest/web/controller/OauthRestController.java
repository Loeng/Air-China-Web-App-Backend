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
package com.stevejrong.airchina.oauth.rest.web.controller;

import com.stevejrong.airchina.common.util.HttpStatus;
import com.stevejrong.airchina.common.wrapper.ResponseWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Controller - 权限验证
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月27日 上午11:58:34
 */
@RestController
@RequestMapping("/users")
public class OauthRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(OauthRestController.class);

//	@Autowired
//	private RecordRequestLogTask recordRequestLogTask;

	/**
	 * 权限验证
	 * 
	 * @param credentials
	 */
	@PostMapping("/auth")
	public void authenticate(@RequestBody final UsernamePasswordToken credentials) {
		LOGGER.info("验证开始...用户名： {}", credentials.getUsername());
		final Subject subject = SecurityUtils.getSubject();
		subject.login(credentials);
	}

	@GetMapping("/list")
	@RequiresAuthentication
	@RequiresRoles("super-admin")
	public @ResponseBody Object getAll() {
		LOGGER.info("获取用户列表...");
//		recordRequestLog();
		return ResponseWrapper.response(HttpStatus.OK.code(), null, "mock data");
	}

	/*private void recordRequestLog() {
		List<?> principals = SecurityUtils.getSubject().getPrincipals().asList();
		String userName = principals.get(0).toString();
		recordRequestLogTask.doRecordRequestLog(userName); // 多线程记录日志
	}*/
}
