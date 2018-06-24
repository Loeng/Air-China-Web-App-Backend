package com.stevejrong.airchina.oauth.shiro.util;

import com.stevejrong.airchina.oauth.common.constant.Constants;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Util - Shiro MD5工具
 *
 * @author Steve Jrong
 * @since 1.0 create date: 2018年06月24日 下午1:12
 */
public class ShiroMd5Util {

    /**
     * 明文密码MD5加密
     * @param salt 盐
     * @param password 明文密码
     * @return Hex编码的密文密码
     */
    public static String encryptPasswordToHex(String salt, String password) {
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        return new SimpleHash(Constants.DEFAULT_HASH_ALGORITH_NAME, password, credentialsSalt, Constants.MD5_HASH_ITERATIONS).toHex();
    }
}
