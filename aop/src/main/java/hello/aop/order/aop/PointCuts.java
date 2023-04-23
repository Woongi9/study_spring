package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * packageName :  hello.aop.order.aop
 * fileName : PointCuts
 * author :  JinWoong
 * date : 2023/04/23
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/23           eomjin-ung          init
 */

public class PointCuts {

    // hello.aop.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder() {} // pointcut signature

    // 클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..)))")
    public void allService() {
    }

    // allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void orderAndService() {

    }
}
