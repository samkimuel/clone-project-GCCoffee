package com.gccoffee.order.service;

import java.util.List;

import com.gccoffee.model.Email;
import com.gccoffee.order.domain.Order;
import com.gccoffee.order.domain.OrderItem;

public interface OrderService {

	Order createOrder(Email email, String address, String postcode, List<OrderItem> orderItems);

}
