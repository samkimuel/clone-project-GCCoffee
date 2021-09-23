package com.gccoffee.order.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gccoffee.order.domain.Order;
import com.gccoffee.order.domain.OrderItem;

@Repository
public class OrderJdbcRepository implements OrderRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	public OrderJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	@Transactional
	public Order insert(Order order) {
		jdbcTemplate.update(
			"INSERT INTO orders(order_id, email, address, postcode, order_status, created_at, updated_at) " +
				"VALUES (UUID_TO_BIN(:orderId), :email, :address, :postcode, :orderStatus, :createdAt, :updatedAt)",
			toOrderParamMap(order));

		order.getOrderItems()
			.forEach(orderItem ->
				jdbcTemplate.update(
					"INSERT INTO order_items(order_id, product_id, category, price, quantity, created_at, updated_at) "
						+ "VALUES (UUID_TO_BIN(:orderId), UUID_TO_BIN(:productId), :category, :price, :quantity, :createdAt, :updatedAt)",
					toOrderItemParamMap(order.getOrderId(), order.getCreatedAt(), order.getUpdatedAt(), orderItem)));
		return order;
	}

	private Map<String, Object> toOrderParamMap(Order order) {
		var paramMap = new HashMap<String, Object>();
		paramMap.put("orderId", order.getOrderId().toString().getBytes());
		paramMap.put("email", order.getEmail().getAddress());
		paramMap.put("address", order.getAddress());
		paramMap.put("postcode", order.getPostcode());
		paramMap.put("orderStatus", order.getOrderStatus().toString());
		paramMap.put("createdAt", order.getCreatedAt());
		paramMap.put("updatedAt", order.getUpdatedAt());
		return paramMap;
	}

	private Map<String, Object> toOrderItemParamMap(UUID orderId, LocalDateTime createdAt, LocalDateTime updatedAt,
		OrderItem orderItem) {
		var paramMap = new HashMap<String, Object>();
		paramMap.put("orderId", orderId.toString().getBytes());
		paramMap.put("productId", orderItem.getProductId().toString().getBytes());
		paramMap.put("category", orderItem.getCategory().toString());
		paramMap.put("price", orderItem.getPrice());
		paramMap.put("quantity", orderItem.getQuantity());
		paramMap.put("createdAt", createdAt);
		paramMap.put("updatedAt", updatedAt);
		return paramMap;
	}

}
