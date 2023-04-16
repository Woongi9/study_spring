package hello.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.jdkdynamic.code
 * fileName : AImpl
 * author :  JinWoong
 * date : 2023/04/17
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/17           eomjin-ung          init
 */

@Slf4j
public class AImpl implements AInterface{
    @Override
    public String call() {
        log.info("A 호출");
        return "A";
    }
}
