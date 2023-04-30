package hello.aop.order.aop.internalcall;

import hello.aop.order.aop.internalcall.v3internalservice.InternalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
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

/**
 * 구조 분리
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV3 {

    private final InternalService internal;

    public void external() {
        log.info("call external");
        internal.internal(); // 내부 메소드 호출
    }
}
