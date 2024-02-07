package com.chandara.ProductsApplication.ProductService;

import java.util.List;

import org.springframework.data.domain.Page;

import com.chandara.ProductsApplication.Entity.Product;

public interface ProductService {
	
	Product Create(Product product);
	Product getProductById(Long id);
	List<Product> getAll();
	Product updateProduct(Long id,Product product);
	void deleteProduct(Long id);
	List<Product> getProductByName(String name);
	void deleteAllProduct();
	List<Product> findByPriceLessThan(double price);
	List<Product> findByPriceGreaterThan(double price);
	Page<Product> pagenation(int page, int size);
	

}