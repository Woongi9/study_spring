package hello.order.v1;

import java.util.concurrent.atomic.AtomicInteger;

import hello.order.OrderService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.order
 * fileName : OrderServiceV0
 * author :  JinWoong
 * date : 11/4/23 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 11/4/23           JinWoong            init
 */

@Slf4j
public class OrderServiceV1 implements OrderService {

	private final MeterRegistry registry;

	public OrderServiceV1(MeterRegistry registry) {
		this.registry = registry;
	}

	private AtomicInteger stock = new AtomicInteger(100);

	@Override
	public void order() {
		log.info("주문");
		stock.decrementAndGet();	// 값 감소

		Counter.builder("my.order")
			.tag("class", this.getClass().getName())
			.tag("method", "order")
			.description("order")
			.register(registry)
			.increment();
	}

	@Override
	public void cancel() {
		log.info("취소");
		stock.incrementAndGet();

		Counter.builder("my.order")
			.tag("class", this.getClass().getName())
			.tag("method", "cancel")
			.description("order")
			.register(registry)
			.increment();
	}

	@Override
	public AtomicInteger getStock() {
		return stock;
	}
}
