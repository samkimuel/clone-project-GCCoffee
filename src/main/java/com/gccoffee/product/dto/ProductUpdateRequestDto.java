package com.gccoffee.product.dto;

import com.gccoffee.model.Category;

public class ProductUpdateRequestDto {
	private final String productId;
	private final String productName;
	private final Category category;
	private final long price;
	private final String description;

	public ProductUpdateRequestDto(String productId, String productName, Category category, long price,
		String description) {
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.description = description;
	}

	public String getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public Category getCategory() {
		return category;
	}

	public long getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

}
