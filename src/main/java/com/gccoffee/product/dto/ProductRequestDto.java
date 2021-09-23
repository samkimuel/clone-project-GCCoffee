package com.gccoffee.product.dto;

import java.util.Objects;

import com.gccoffee.model.Category;

public class ProductRequestDto {
	private final String productName;
	private final Category category;
	private final long price;
	private final String description;

	public ProductRequestDto(String productName, Category category, long price, String description) {
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.description = description;
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ProductRequestDto that = (ProductRequestDto)o;
		return Objects.equals(productName, that.productName) &&
			category == that.category &&
			price == that.price &&
			Objects.equals(description, that.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productName, category, price, description);
	}

	@Override
	public String toString() {
		return "ProductRequestDto{" +
			"productName='" + productName + '\'' +
			", category=" + category +
			", price=" + price +
			", description='" + description + '\'' +
			'}';
	}
}
