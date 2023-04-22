package hello.advanced;

import hello.advanced.trace.logtrace.FieldLogTrace;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName :  hello.advanced
 * fileName : LogTraceConfig
 * author :  JinWoong
 * date : 2023/04/08
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/04/08           eomjin-ung          init
 */
@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
//        return new FieldLogTrace();
        return new ThreadLocalLogTrace();
    }
}
