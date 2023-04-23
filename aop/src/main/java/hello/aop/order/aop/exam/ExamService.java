package hello.aop.order.aop.exam;

import hello.aop.order.aop.exam.annotation.Trace;
import lombok.RequiredArgsConstructor;

/**
 * packageName :  hello.aop.order.aop.exam
 * fileName : Service
 * author :  JinWoong
 * date : 2023/04/23
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/23           eomjin-ung          init
 */

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    @Trace
    public void request(String itemId) {
        examRepository.save(itemId);
    }
}
