package hello.advanced.v2;

import hello.advanced.v1.trace.TraceId;
import hello.advanced.v1.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * packageName :  hello.advanced.v0
 * fileName : OrderRepository
 * author :  JinWoong
 * date : 2023/04/02
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/02           eomjin-ung          init
 */

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId) {


        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId, "OrderRepository.request()");
            // 저장 로직
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 꼭 다시 던져주어야 한다.
        }

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
