package cn.bd.microservice.order.controller;

import cn.bd.microservice.order.pojo.Order;
import cn.bd.microservice.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	@Autowired
    private OrderService orderService;

	@GetMapping(value = "order/{orderId}")
	public Order queryOrderById(@PathVariable("orderId") String orderId) {
		return this.orderService.queryOrderById(orderId);
	}

}