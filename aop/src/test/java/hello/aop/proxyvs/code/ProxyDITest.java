package hello.aop.proxyvs.code;

import hello.aop.order.aop.member.MemberService;
import hello.aop.order.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * packageName :  hello.aop.proxyvs.code
 * fileName : ProxyDITest
 * author :  JinWoong
 * date : 2023/04/29
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/29           eomjin-ung          init
 */

@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) // CGLIB 프록시
@SpringBootTest
@Import(ProxyDIAspect.class)
public class ProxyDITest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");
    }
}
