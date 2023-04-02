package hello.advanced.v1.trace;

/**
 * packageName :  hello.advanced.v1
 * fileName : TraceStatus
 * author :  JinWoong
 * date : 2023/04/02
 * description : 로그 시작할 때의 상태 정보 (로그 종료시 사용)
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/02           eomjin-ung          init
 */

public class TraceStatus {

    private TraceId traceId;
    private Long startTimeMs;
    private String message;

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {

        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }
}
