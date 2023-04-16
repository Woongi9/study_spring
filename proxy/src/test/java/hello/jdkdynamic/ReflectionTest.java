package hello.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * packageName :  hello.jdkdynamic
 * fileName : ReflectionTest
 * author :  JinWoong
 * date : 2023/04/16
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/16           eomjin-ung          init
 */

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();
        // 공통 로직1 시작
        log.info("start");
        String resultA = target.callA();
        log.info("result={}", resultA);
        // 공통 로직1 종료

        // 공통 로직2 시작
        log.info("start");
        String resultB = target.callB();
        log.info("result={}", resultB);
        // 공통 로직2 종료
    }

    @Test
    void reflection1() throws Exception {
        // 클래스 정보 획득
        Class classHello = Class.forName("hello.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        // callA 메소드 정보
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result1={}", result1);

        // callB 메소드 정보
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2={}", result2);
    }

    @Test
    void reflection2() throws Exception {
        // 클래스 정보 획득
        Class classHello = Class.forName("hello.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        // callA 메소드 정보
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        // callB 메소드 정보
        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception{
        log.info("start");
        Object result = method.invoke(target);
        log.info("result = {}", result);
    }

    @Slf4j
    static class Hello{
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }

}
