package com.chandara.ProductsApplication.ProductRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chandara.ProductsApplication.Entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{
	List<Product> findByName(String name);
	List<Product> findByPriceLessThan(double price);
	List<Product>findByPriceGreaterThan(double price);
	
}
