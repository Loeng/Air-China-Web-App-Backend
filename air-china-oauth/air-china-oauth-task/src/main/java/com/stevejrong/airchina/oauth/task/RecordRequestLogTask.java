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
package com.stevejrong.airchina.oauth.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Thread Task - 记录请求日志
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年3月4日 上午10:30:25
 */
@Component
public class RecordRequestLogTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecordRequestLogTask.class);

	/**
	 * 记录请求日志
	 * 
	 * @param userName
	 */
	@Async("taskAsyncPool")
	public void doRecordRequestLog(String userName) {
		for (int i = 0; i < 10; i++) {
			LOGGER.info("多线程记录日志[{}]：用户名：{}", i, userName);
		}
	}
}
