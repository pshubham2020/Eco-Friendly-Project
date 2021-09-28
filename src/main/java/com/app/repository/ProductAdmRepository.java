package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entity.Product;

public interface ProductAdmRepository extends JpaRepository<Product, Long> {
	
}
