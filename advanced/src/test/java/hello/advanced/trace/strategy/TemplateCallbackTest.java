package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.template.Callback;
import hello.advanced.trace.strategy.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * packageName :  hello.advanced.trace.strategy
 * fileName : TemplateCallbackTest
 * author :  JinWoong
 * date : 2023/04/09
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/09           eomjin-ung          init
 */

@Slf4j
public class TemplateCallbackTest {
    /**
     * 템플릿 콜백 패턴 - 익명 내부 클래스
     */
    @Test
    void callbackV1() {
        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
        timeLogTemplate.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });

        timeLogTemplate.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }

    @Test
    void callbackV2() {
        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
        timeLogTemplate.execute(() -> log.info("비즈니스 로직1 실행"));
        timeLogTemplate.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}
