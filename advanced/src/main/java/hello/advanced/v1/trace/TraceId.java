package hello.advanced.v1.trace;

import java.util.UUID;

/**
 * packageName :  hello.advanced.v1
 * fileName : TraceId
 * author :  JinWoong
 * date : 2023/04/02
 * description : 로그 추적기에 표현되는 클래스
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/02           eomjin-ung          init
 */

public class TraceId {

    private String id;  // 트랜잭션 ID
    private int level;  // log 레벨

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        // 생성된 UUID 중 8자리만 사용
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    public TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
