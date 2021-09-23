package com.gccoffee.product.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.gccoffee.model.Category;

public class Product {
	private final UUID productId;
	private final LocalDateTime createdAt;
	private String productName;
	private Category category;
	private long price;
	private String description;
	private LocalDateTime updatedAt;

	public Product(UUID productId, String productName, Category category, long price) {
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public Product(UUID productId, String productName, Category category, long price, String description,
		LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UUID getProductId() {
		return productId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void changeProductName(String productName) {
		this.productName = productName;
		this.updatedAt = LocalDateTime.now();
	}
}
