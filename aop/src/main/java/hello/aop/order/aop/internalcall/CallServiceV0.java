package hello.aop.order.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * packageName :  hello.aop.order.aop.internalcall
 * fileName : CallServiceV0
 * author :  JinWoong
 * date : 2023/04/29
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/29           eomjin-ung          init
 */

@Slf4j
@Component
public class CallServiceV0 {

    public void external() {
        log.info("call external");
        internal(); // 내부 메소드 호출
    }

    public void internal() {
        log.info("call internal");

    }
}
