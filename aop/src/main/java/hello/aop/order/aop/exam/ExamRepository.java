package hello.aop.order.aop.exam;

import hello.aop.order.aop.exam.annotation.Retry;
import hello.aop.order.aop.exam.annotation.Trace;

/**
 * packageName :  hello.aop.order.aop.exam
 * fileName : Repository
 * author :  JinWoong
 * date : 2023/04/23
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/23           eomjin-ung          init
 */

@org.springframework.stereotype.Repository
public class ExamRepository {

    private static int seq = 0;

    /**
     * 5번에 1번 실패하는 요청
     */
    @Trace
    @Retry(4)
    public String save(String itemId) {
        seq++;
        if (seq % 5 == 0) {
            throw new IllegalStateException("예외 발생 ");
        }
        return "ok";
    }

}
