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
 * @since 1.0 create date: 2018年05月17日 下午7:52
 */
@Ignore
public class OauthCaseTest3 {

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
        String url = "http://127.0.0.1:8888/users/auth";
        Map<String, String> headerParams = new HashMap<String, String>();
        headerParams.put("Content-Type", "application/json");
        Map<String, String> bodyParams = new HashMap<String, String>();
        bodyParams.put("username", "zhangsan@gmail.com");
        bodyParams.put("password", "123456");

        JSONObject result = HttpUtil.launchPostSync(url, headerParams, bodyParams);
        System.out.println(result);
    }
}
