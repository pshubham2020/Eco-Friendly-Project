package com.app.service;

import java.util.List;
import org.springframework.data.domain.Pageable;

import com.app.entity.Product;

public interface ProductService {
	boolean saveProduct(Product product);
	List<Product> getAllActiveProducts();
	List<Product> getProducts(Pageable pageable);
	public Product getProductByCode(String code);
	List<Product> searchProducts(String name);
	void updateProductByCode(String name, String description, String imageData, double mrpPrice, double price, boolean active, String code);
}
