package hello.order.v3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.order.OrderService;
import hello.order.v2.OrderServiceV2;
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
public class OrderConfigV3 {

	@Bean
	OrderService orderService(MeterRegistry registry) {
		return new OrderServiceV3(registry);
	}
}
