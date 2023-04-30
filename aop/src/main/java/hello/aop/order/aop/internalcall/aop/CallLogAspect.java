package hello.aop.order.aop.internalcall.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * packageName :  hello.aop.order.aop.internalcall.aop
 * fileName : CallLogAspect
 * author :  JinWoong
 * date : 2023/04/29
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/29           eomjin-ung          init
 */

@Slf4j
@Aspect
public class CallLogAspect {

    @Before("execution(* hello.aop.order.aop..*.*(..))")
    public void doLog(JoinPoint joinPoint) {
        log.info("aop={}", joinPoint.getSignature());
    }
}
