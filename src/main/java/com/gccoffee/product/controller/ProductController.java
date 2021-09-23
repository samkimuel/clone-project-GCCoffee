package com.gccoffee.product.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gccoffee.product.domain.Product;
import com.gccoffee.product.dto.ProductRequestDto;
import com.gccoffee.product.dto.ProductUpdateRequestDto;
import com.gccoffee.product.service.ProductService;

@Controller
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public String productsPage(Model model) {
		var products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "product-list";
	}

	@GetMapping("new-product")
	public String newProductPage() {
		return "new-product";
	}

	@PostMapping("/products")
	public String newProduct(ProductRequestDto productRequestDto) {
		productService.createProduct(
			productRequestDto.getProductName(),
			productRequestDto.getCategory(),
			productRequestDto.getPrice(),
			productRequestDto.getDescription());
		return "redirect:/products";
	}

	@GetMapping("update-product/{productId}")
	public String updateProductPage(Model model, @PathVariable("productId") String productId) {
		Product product = productService.getProductByProductId(UUID.fromString(productId));
		model.addAttribute("product", product);
		return "update-product";
	}

	@PostMapping("/update-product")
	public String updateProduct(ProductUpdateRequestDto productUpdateRequestDto) {
		productService.updateProduct(
			UUID.fromString(productUpdateRequestDto.getProductId()),
			productUpdateRequestDto.getProductName(),
			productUpdateRequestDto.getCategory(),
			productUpdateRequestDto.getPrice(),
			productUpdateRequestDto.getDescription());
		return "redirect:/products";
	}

	@GetMapping("delete-product/{productId}")
	public String updateProductPage(@PathVariable("productId") String productId) {
		productService.deleteProduct(UUID.fromString(productId));
		return "redirect:/products";
	}

}
