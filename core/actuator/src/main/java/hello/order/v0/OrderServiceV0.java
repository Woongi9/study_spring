package hello.order.v0;

import java.util.concurrent.atomic.AtomicInteger;

import hello.order.OrderService;
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
public class OrderServiceV0 implements OrderService {
	private AtomicInteger stock = new AtomicInteger(100);
	@Override
	public void order() {
		log.info("주문");
		stock.decrementAndGet();	// 값 감소
	}

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
