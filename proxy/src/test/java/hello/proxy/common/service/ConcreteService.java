package hello.proxy.common.service;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.proxy.common.service
 * fileName : ConcreteService
 * author :  JinWoong
 * date : 2023/04/19
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/19           eomjin-ung          init
 */

@Slf4j
public class ConcreteService {

    public void call() {
        log.info("ConcreteService 호출");
    }
}
