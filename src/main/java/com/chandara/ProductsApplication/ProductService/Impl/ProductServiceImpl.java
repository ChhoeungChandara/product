package com.chandara.ProductsApplication.ProductService.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chandara.ProductsApplication.Entity.Product;
import com.chandara.ProductsApplication.Exception.ResourceNotFoundException;
import com.chandara.ProductsApplication.ProductRepository.ProductRepository;
import com.chandara.ProductsApplication.ProductService.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;

	@Override
	public Product Create(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product getProductById(Long id) {
		return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product", id));
	}

	@Override
	public Product updateProduct(Long id, Product product) {
		Product ProductId = getProductById(id);
		ProductId.setName(product.getName());
		ProductId.setImage(product.getImage());
		ProductId.setDescription(product.getDescription());
		ProductId.setPrice(product.getPrice());
		return productRepository.save(ProductId);
	}

	@Override
	public void deleteProduct(Long id) {
		Product productId = getProductById(id);
		productRepository.delete(productId);
	}

	@Override
	public List<Product> getAll() {
		
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public void deleteAllProduct() {
		productRepository.deleteAll();
	}

	@Override
	public List<Product> findByPriceLessThan(double price) {
		return productRepository.findByPriceLessThan(price);
	}

	@Override
	public List<Product> findByPriceGreaterThan(double price) {
		return productRepository.findByPriceGreaterThan(price);
	}





}
