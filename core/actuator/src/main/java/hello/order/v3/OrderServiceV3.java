package hello.order.v3;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import hello.order.OrderService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
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
public class OrderServiceV3 implements OrderService {

	private final MeterRegistry registry;
	private AtomicInteger stock = new AtomicInteger(100);

	public OrderServiceV3(MeterRegistry registry) {
		this.registry = registry;
	}

	@Counted("my.order")
	@Override
	public void order() {
		Timer timer = Timer.builder("my.order")
			.tag("class", this.getClass().getName())
			.tag("method", "order")
			.description("order")
			.register(registry);

		timer.record(() -> {
			log.info("주문");
			stock.decrementAndGet();
			sleep(500);
		});

	}

	@Counted("my.order")
	@Override
	public void cancel() {
		Timer timer = Timer.builder("my.order")
			.tag("class", this.getClass().getName())
			.tag("method", "cancel")
			.description("order")
			.register(registry);

		timer.record(() -> {
			log.info("취소");
			stock.decrementAndGet();
			sleep(200);
		});
	}

	private static void sleep(int time) {
		try {
			Thread.sleep(time + new Random().nextInt(200));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public AtomicInteger getStock() {
		return stock;
	}
}
