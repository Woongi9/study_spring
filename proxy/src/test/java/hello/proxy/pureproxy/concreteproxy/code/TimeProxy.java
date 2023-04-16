package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.proxy.pureproxy.concreteproxy.code
 * fileName : TimeProxy
 * author :  JinWoong
 * date : 2023/04/16
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/16           eomjin-ung          init
 */

@Slf4j
public class TimeProxy extends ConcreteLogic{

    private ConcreteLogic concreteLogic;

    public TimeProxy(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    @Override
    public String opeation() {
        log.info("TimeDecorator 실행");
        long startTime = System.currentTimeMillis();
        String result = concreteLogic.opeation();

        long endTim = System.currentTimeMillis();
        long resultTime = endTim - startTime;
        log.info("TimeDecorator 종료 resultTime={}ms", resultTime);
        return result;
    }
}
