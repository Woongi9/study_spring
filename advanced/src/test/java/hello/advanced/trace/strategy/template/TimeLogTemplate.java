package hello.advanced.trace.strategy.template;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.advanced.trace.strategy.template
 * fileName : TimeLogTemplate
 * author :  JinWoong
 * date : 2023/04/09
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/09           eomjin-ung          init
 */

@Slf4j
public class TimeLogTemplate {
    public void execute(Callback callback) {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        callback.call();    // 위임
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
