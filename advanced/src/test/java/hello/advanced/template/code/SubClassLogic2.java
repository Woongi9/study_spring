package hello.advanced.template.code;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.advanced.template.code
 * fileName : SubClassLogic1
 * author :  JinWoong
 * date : 2023/04/09
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/09           eomjin-ung          init
 */

@Slf4j
public class SubClassLogic2 extends AbstractTemplate {

    @Override
    protected void call() {
        log.info("비즈니스 로직 2 실행");
    }
}
