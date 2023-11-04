package hello.order.v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.order.OrderService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * packageName :  hello.order.v0
 * fileName : OrderConfigV0
 * author :  JinWoong
 * date : 11/4/23 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 11/4/23           JinWoong            init
 */

@Configuration
public class OrderConfigV2 {

	@Bean
	OrderService orderService() {
		return new OrderServiceV2();
	}

	@Bean
	public CountedAspect countedAspect(MeterRegistry registry) {
		return new CountedAspect(registry);
	}
}
