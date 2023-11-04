package hello.order.gauge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.order.OrderService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.order.guage
 * fileName : StockConfigV1
 * author :  JinWoong
 * date : 11/4/23 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 11/4/23           JinWoong            init
 */

@Configuration
public class StockConfigV1 {

	@Bean
	public MyStockMetric myStockMetric(OrderService orderService, MeterRegistry registry) {
		return new MyStockMetric(orderService, registry);
	}

	@Slf4j
	static class MyStockMetric {
		private OrderService orderService;
		private MeterRegistry meterRegistry;

		public MyStockMetric(OrderService orderService, MeterRegistry meterRegistry) {
			this.orderService = orderService;
			this.meterRegistry = meterRegistry;
		}

		@PostConstruct
		public void init() {
			Gauge.builder("my.stock", orderService, orderService -> {
				log.info("stock gauge call");
				return orderService.getStock().get();
			}).register(meterRegistry);
		}
	}
}
