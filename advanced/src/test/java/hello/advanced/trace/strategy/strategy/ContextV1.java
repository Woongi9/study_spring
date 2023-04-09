package hello.advanced.trace.strategy.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.advanced.trace.strategy.strategy
 * fileName : ContextV1
 * author :  JinWoong
 * date : 2023/04/09
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/09           eomjin-ung          init
 */

@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        strategy.call();    // 위임
        log.info("비즈니스 로직1 실행");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
