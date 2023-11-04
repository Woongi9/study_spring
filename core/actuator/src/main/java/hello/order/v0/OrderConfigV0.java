package hello.order.v0;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.order.OrderService;

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
public class OrderConfigV0 {

	@Bean
	OrderService orderService() {
		return new OrderServiceV0();
	}
}
