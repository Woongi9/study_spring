package hello.advanced.trace.strategy.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.advanced.trace.strategy.strategy
 * fileName : ContextV1
 * author :  JinWoong
 * date : 2023/04/09
 * description : 전략을 파라미터로 전달 받는 방식
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/09           eomjin-ung          init
 */

@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy) {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        strategy.call();    // 위임
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
