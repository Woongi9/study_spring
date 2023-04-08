package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;

/**
 * packageName :  hello.advanced.v1.trace
 * fileName : LogTrace
 * author :  JinWoong
 * date : 2023/04/08
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/08           eomjin-ung          init
 */
public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus sta, Exception e);
}
