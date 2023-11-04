package hello.order.v2;

import java.util.concurrent.atomic.AtomicInteger;

import hello.order.OrderService;
import io.micrometer.core.annotation.Counted;
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
public class OrderServiceV2 implements OrderService {

	private AtomicInteger stock = new AtomicInteger(100);

	@Counted("my.order")
	@Override
	public void order() {
		log.info("주문");
		stock.decrementAndGet();	// 값 감소
	}

	@Counted("my.order")
	@Override
	public void cancel() {
		log.info("취소");
		stock.incrementAndGet();
	}

	@Override
	public AtomicInteger getStock() {
		return stock;
	}
}
