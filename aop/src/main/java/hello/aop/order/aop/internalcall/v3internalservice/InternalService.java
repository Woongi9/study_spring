package hello.aop.order.aop.internalcall.v3internalservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * packageName :  hello.aop.order.aop.internalcall.v3internalservice
 * fileName : InternalService
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
public class InternalService {

    public void internal() {
        log.info("call internal");

    }
}
