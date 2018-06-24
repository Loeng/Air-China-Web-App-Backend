import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author wangjing
 * @since 1.0 create date: 2018年06月24日 上午11:59
 */
@Ignore
public class OauthCaseTest4 {

    @Before
    public void before() {
        System.out.println("测试开始……");
    }

    @After
    public void after() {
        System.out.println("测试结束……");
    }

    @Test
    public void test() {
        String hashAlgorithName = "MD5";
        String password = "123456";
        int hashIterations = 1024;//加密次数
        ByteSource credentialsSalt = ByteSource.Util.bytes("zhangsan@gmail.com");
        String result = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations).toHex();
        System.out.println(result);
    }
}
