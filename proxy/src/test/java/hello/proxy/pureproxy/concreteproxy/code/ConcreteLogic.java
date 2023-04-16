package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.proxy.pureproxy.concreteproxy.code
 * fileName : ConcreteLogic
 * author :  JinWoong
 * date : 2023/04/16
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/16           eomjin-ung          init
 */

@Slf4j
public class ConcreteLogic {

    public String opeation() {
        log.info("ConcreteLogic 실행");
        return "data";
    }
}
