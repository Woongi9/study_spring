package hello.advanced.v1.hellotrace;

import hello.advanced.app.v1.hellotrace.HelloTraceV1;
import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

/**
 * packageName :  hello.advanced.v1.hellotrace
 * fileName : HelloTraceV1Test
 * author :  JinWoong
 * date : 2023/04/02
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/02           eomjin-ung          init
 */
class HelloTraceV1Test {

    @Test
    void begin_end() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    void begin_exception() {
        HelloTraceV1 traceV1 = new HelloTraceV1();
        TraceStatus status = traceV1.begin("hello");
        traceV1.exception(status, new IllegalStateException());
    }

}