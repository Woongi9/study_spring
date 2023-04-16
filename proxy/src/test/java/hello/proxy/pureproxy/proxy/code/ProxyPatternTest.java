package hello.proxy.pureproxy.proxy.code;

import org.junit.jupiter.api.Test;

/**
 * packageName :  hello.proxy.pureproxy.proxy.code
 * fileName : ProxyPatternTest
 * author :  JinWoong
 * date : 2023/04/16
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/16           eomjin-ung          init
 */
public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);

        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void cacheProxyTest() {
        RealSubject realSubject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(realSubject);
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);

        client.execute();
        client.execute();
        client.execute();
    }
}
