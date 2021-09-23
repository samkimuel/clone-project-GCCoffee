package com.gccoffee.order.repository;

import com.gccoffee.order.domain.Order;

public interface OrderRepository {

	Order insert(Order order);

}
