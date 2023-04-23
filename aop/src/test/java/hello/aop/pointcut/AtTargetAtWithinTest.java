package hello.aop.pointcut;

import hello.aop.order.aop.member.MemberService;
import hello.aop.order.aop.member.annotation.ClassAop;
import hello.aop.order.aop.member.annotation.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * packageName :  hello.aop.pointcut
 * fileName : AtTargetAtWithinTest
 * author :  JinWoong
 * date : 2023/04/23
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/23           eomjin-ung          init
 */

@Slf4j
@Import({AtTargetAtWithinTest.Config.class})
@SpringBootTest
public class AtTargetAtWithinTest implements MemberService {

    @Autowired
    Child child;

    @Test
    void success() {
        log.info("child Proxy={}", child.getClass());
        child.childMethod();    // 부모, 자식 모두 있는 메소드
        child.parentMethod();   // 부모 클래스만 있는 메소드
    }

    static class Config {
        @Bean
        public Parent parent() {
            return new Parent();}

        @Bean
        public Child child() {
            return new Child();}

        @Bean
        public AtTargetAtWithinAspect atTargetAtWithinAspect() {
            return new AtTargetAtWithinAspect();}
    }

    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }


    static class Parent {
        public void parentMethod() {
        } // 부모에만 있는 메소드
    }

    @ClassAop
    static class Child extends Parent {
        public void childMethod(){}
    }

    @Slf4j
    @Aspect
    static class AtTargetAtWithinAspect{
        // @target: 인스턴스 기준으로 모든 메소드의 조인 포인트를 선정, 부모 타입의 메소드도 적용
        @Around("execution(* hello.aop.order.aop..*(..)) && @target(hello.aop.order.aop.member.annotation.ClassAop)")
        public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@target] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        // @within : 선택된 클래스 내부에 있는 메소드만 조인 포인트로 선정, 부모 타입의 메소드는 적용되지 않음
        @Around("execution(* hello.aop..*(..)) && @within(hello.aop.order.aop.member.annotation.ClassAop)")
        public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@within] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

    }



}
