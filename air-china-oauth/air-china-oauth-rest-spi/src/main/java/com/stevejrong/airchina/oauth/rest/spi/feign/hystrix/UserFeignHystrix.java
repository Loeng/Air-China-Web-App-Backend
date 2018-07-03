package com.stevejrong.airchina.oauth.rest.spi.feign.hystrix;

import com.stevejrong.airchina.oauth.common.bo.UserBo;
import com.stevejrong.airchina.oauth.rest.spi.feign.client.UserFeign;
import org.springframework.stereotype.Component;

/**
 * User Feign接口的熔断回退处理
 *
 * @author Steve Jrong
 * @since 1.0 create date: 2018年07月03日 下午5:25
 */
@Component
public class UserFeignHystrix implements UserFeign {

    @Override
    public UserBo getByEmail(String email) {
        return new UserBo();
    }
}
