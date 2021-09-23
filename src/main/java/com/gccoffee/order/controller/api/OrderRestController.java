package com.gccoffee.order.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gccoffee.model.Email;
import com.gccoffee.order.domain.Order;
import com.gccoffee.order.dto.OrderRequestDto;
import com.gccoffee.order.service.OrderService;

@RestController
public class OrderRestController {

	private final OrderService orderService;

	public OrderRestController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/api/v1/orders")
	public Order createOrder(@RequestBody OrderRequestDto orderRequestDto) {
		return orderService.createOrder(
			new Email(orderRequestDto.getEmail()), orderRequestDto.getAddress(), orderRequestDto.getPostcode(),
			orderRequestDto.getOrderItems()
		);
	}
}
