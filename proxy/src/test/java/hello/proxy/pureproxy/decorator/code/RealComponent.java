package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.proxy.pureproxy.decorator.code
 * fileName : RealComponent
 * author :  JinWoong
 * date : 2023/04/16
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/16           eomjin-ung          init
 */
@Slf4j
public class RealComponent implements Component{

    @Override
    public String operation() {
        log.info("RealComponent 실행");
        return "data";
    }
}
