package hello.order;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * packageName :  hello.order
 * fileName : OrderService
 * author :  JinWoong
 * date : 11/4/23 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 11/4/23           JinWoong            init
 */

public interface OrderService {
	void order();

	void cancel();

	AtomicInteger getStock();	// 멀티 쓰레드 상황에서 안전하게 값 증가 가능
}
