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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller - Test
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月26日 下午12:23:00
 */
@RestController
@RequestMapping("/tests")
public class TestRestController {

	@GetMapping(value = "/test1")
	@ResponseBody
	public ResponseWrapper test() {
		 return ResponseWrapper.response(HttpStatus.OK.code(), "This is test message by rest!", null);
	}
}
