package hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.order.OrderService;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName :  hello.controller
 * fileName : OrderController
 * author :  JinWoong
 * date : 11/4/23 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 11/4/23           JinWoong            init
 */

@Slf4j
@RestController
public class OrderController {

	public OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/order")
	public String order() {
		log.info("order");
		orderService.order();
		return "order";
	}

	@GetMapping("/cancel")
	public String cancel() {
		log.info("cancel");
		orderService.cancel();
		return "cancel";
	}

	@GetMapping("/stock")
	public int stock() {
		log.info("stock");
		return orderService.getStock().get();
	}
}
