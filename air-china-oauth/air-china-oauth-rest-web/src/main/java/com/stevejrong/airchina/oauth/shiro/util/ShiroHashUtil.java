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
package com.stevejrong.airchina.oauth.shiro.util;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.SimpleByteSource;

/**
 * Util - Shiro哈希值生成工具
 * 
 * @author Steve Jrong
 * @since 1.0 create date: 2018年2月28日 下午4:10:23
 */
public class ShiroHashUtil {
	private static final int HASH_ITERATIONS = 1;
	private static final String SHA256_HASH_ALGORITHM = Sha256Hash.ALGORITHM_NAME;
private static final String MD5_HASH_ALGORITHM = Md5Hash.ALGORITHM_NAME;

	private static CredentialsMatcher credentials;
	private static RandomNumberGenerator salter;

	public static synchronized CredentialsMatcher getCredentialsMatcher() {
		if (credentials == null) {
			HashedCredentialsMatcher credentials = new HashedCredentialsMatcher(MD5_HASH_ALGORITHM);
			credentials.setHashIterations(HASH_ITERATIONS);
			credentials.setStoredCredentialsHexEncoded(true);
		}
		return credentials;
	}

	private static synchronized RandomNumberGenerator getSalter() {
		if (salter == null) {
			salter = new SecureRandomNumberGenerator();
		}
		return salter;
	}

	public static byte[] getSaltedBytes() {
		return getSalter().nextBytes().getBytes();
	}

	public static String getHashString(String secret, byte[] salt) {
		return (secret == null) ? null
				: new SimpleHash(MD5_HASH_ALGORITHM, secret, new SimpleByteSource(salt), HASH_ITERATIONS).toHex();
	}

}
