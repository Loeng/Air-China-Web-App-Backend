/**
 * Copyright 2018 Steve Jrong - https://www.stevejrong.top
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.alibaba.fastjson.JSONObject;
import com.stevejrong.airchina.common.util.HttpUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录鉴权测试用例2
 *
 * @author Steve Jrong
 * @since 1.0 create date: 2018年5月16日 下午9:00:25
 */
@Ignore
public class OauthCaseTest2 {

    @Before
    public void before() {
        System.out.println("测试开始……");
    }

    @After
    public void after() {
        System.out.println("测试结束……");
    }

    @Test
    public void testGetUsersListTakeToken() {
        String url = "http://127.0.0.1:8888/users/list";
        Map<String, String> headerParams = new HashMap<String, String>();
        headerParams.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ6aGFuZ3NhbkBnbWFpbC5jb20iLCJleHAiOjE1Mjg5OTIwMDB9.3Tv-Odp6QOZWWFg5TTc60PuUUL-2N-VKjKjN1wI_nPs");
        headerParams.put("Accept", "application/json");

        JSONObject result = HttpUtil.launchGetSync(url, headerParams, null);
        System.out.println(result);
    }
}
