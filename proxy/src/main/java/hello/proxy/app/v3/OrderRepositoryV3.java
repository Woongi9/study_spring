package hello.proxy.app.v3;

import org.springframework.stereotype.Repository;

/**
 * packageName :  hello.proxy.app.v3
 * fileName : OrderRepositoryV3
 * author :  JinWoong
 * date : 2023/04/16
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/16           eomjin-ung          init
 */

@Repository
public class OrderRepositoryV3 {

    public void save(String itemId) {
        // 저장 로직
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }
        sleep(1000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
