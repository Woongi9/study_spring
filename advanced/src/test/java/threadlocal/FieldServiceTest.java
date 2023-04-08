package threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import threadlocal.code.FieldService;

/**
 * packageName :  threadlocal.code
 * fileName : FieldServiceTest
 * author :  JinWoong
 * date : 2023/04/08
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/08           eomjin-ung          init
 */
@Slf4j
public class FieldServiceTest {

    private FieldService fieldService =  new FieldService();

    @Test
    void field() {
        log.info("main start");

        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
//        sleep(2000);    // 동시성 문제 발생 X
        sleep(100); // 동시성 문제 발생 X
        threadB.start();

        sleep(2000);    // 메인 쓰레드 종료 대기
        log.info("main exit");

    }

    private void sleep(int millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
