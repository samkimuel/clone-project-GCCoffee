package com.gccoffee.product.service;

import java.util.List;
import java.util.UUID;

import com.gccoffee.model.Category;
import com.gccoffee.product.domain.Product;

public interface ProductService {

	List<Product> getProductsByCategory(Category category);

	List<Product> getAllProducts();

	Product createProduct(String productName, Category category, long price);

	Product createProduct(String productName, Category category, long price, String description);

	Product getProductByProductId(UUID productId);

	Product updateProduct(UUID productId, String productName, Category category, long price, String description);

	void deleteProduct(UUID productId);
}
