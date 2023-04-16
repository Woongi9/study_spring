package hello.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.jdkdynamic.code
 * fileName : BImpl
 * author :  JinWoong
 * date : 2023/04/17
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/17           eomjin-ung          init
 */

@Slf4j
public class BImpl implements BInterface{
    @Override
    public String call() {
        log.info("B 호출");
        return "B";
    }
}
