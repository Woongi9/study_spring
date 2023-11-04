package hello.order.v4;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import hello.order.OrderService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
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

@Timed("my.order")
@Slf4j
public class OrderServiceV4 implements OrderService {

	private AtomicInteger stock = new AtomicInteger(100);

	@Counted("my.order")
	@Override
	public void order() {

		log.info("주문");
		stock.decrementAndGet();
		sleep(500);
	}

	@Counted("my.order")
	@Override
	public void cancel() {
		log.info("취소");
		stock.decrementAndGet();
		sleep(200);
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
