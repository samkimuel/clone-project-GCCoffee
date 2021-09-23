package com.gccoffee.order.dto;

import java.util.List;
import java.util.Objects;

import com.gccoffee.order.domain.OrderItem;

public class OrderRequestDto {
	private final String email;
	private final String address;
	private final String postcode;
	private final List<OrderItem> orderItems;

	OrderRequestDto(String email, String address, String postcode, List<OrderItem> orderItems) {
		this.email = email;
		this.address = address;
		this.postcode = postcode;
		this.orderItems = orderItems;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getPostcode() {
		return postcode;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		OrderRequestDto that = (OrderRequestDto)o;
		return Objects.equals(email, that.email) && Objects.equals(address, that.address) &&
			Objects.equals(postcode, that.postcode) && Objects.equals(orderItems, that.orderItems);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, address, postcode, orderItems);
	}

	@Override
	public String toString() {
		return "OrderRequestDto{" +
			"email='" + email + '\'' +
			", address='" + address + '\'' +
			", postcode='" + postcode + '\'' +
			", orderItems=" + orderItems +
			'}';
	}

}
