package hello.springtx;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.springtx
 * fileName : OrderServiceTest
 * author :  JinWoong
 * date : 2023/08/06 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/08/06           JinWoong            init
 */

@Slf4j
@SpringBootTest
class OrderServiceTest {

	@Autowired
	OrderService orderService;
	@Autowired
	OrderRepository orderRepository;

	@Test
	void order() throws NotEnoughMoneyException {
		// given
		Order order = new Order();
		order.setUsername("정상");

		// when
		orderService.order(order);

		// then
		Order findOrder = orderRepository.findById(order.getId()).get();
		assertThat(findOrder.getPayStatus()).isEqualTo("완료");

	}
}