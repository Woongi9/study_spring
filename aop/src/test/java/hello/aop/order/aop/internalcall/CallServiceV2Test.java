package hello.aop.order.aop.internalcall;

import hello.aop.order.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName :  hello.aop.order.aop.internalcall
 * fileName : CallServiceV2Test
 * author :  JinWoong
 * date : 2023/04/29
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/29           eomjin-ung          init
 */

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV2Test {

    @Autowired
    CallServiceV2 callServiceV2;

    @Test
    void external() {
        log.info("start external");
        callServiceV2.external();
    }

    @Test
    void internal() {
        log.info("start internal");
        callServiceV2.internal();
    }
}