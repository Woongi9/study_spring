package hello.advanced.trace.strategy.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.advanced.trace.strategy.strategy
 * fileName : StrategyLogic1
 * author :  JinWoong
 * date : 2023/04/09
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/09           eomjin-ung          init
 */

@Slf4j
public class StrategyLogic2 implements Strategy{

    @Override
    public void call() {
        log.info("ㅂㅣ즈니스 로직 2 실행");
    }
}
