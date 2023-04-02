package hello.advanced.v2;

import hello.advanced.v1.hellotrace.HelloTraceV1;
import hello.advanced.v1.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName :  hello.advanced.v2
 * fileName : HelloTraceV2Test
 * author :  JinWoong
 * date : 2023/04/03
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/03           eomjin-ung          init
 */
class HelloTraceV2Test {

    @Test
    void begin_end() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception() {
        HelloTraceV2 traceV2 = new HelloTraceV2();
        TraceStatus status1 = traceV2.begin("hello");
        TraceStatus status2 = traceV2.beginSync(status1.getTraceId(), "hello2");

        traceV2.exception(status2, new IllegalStateException());
        traceV2.exception(status1, new IllegalStateException());
    }
}