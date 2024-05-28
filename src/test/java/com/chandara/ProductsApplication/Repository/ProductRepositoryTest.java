package com.chandara.ProductsApplication.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.chandara.ProductsApplication.Entity.Product;
import com.chandara.ProductsApplication.ProductRepository.ProductRepository;
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindByName_ExistingName_ReturnsMatchingProducts() {
    	List<Product> products= new ArrayList<>();
		products.add(new Product(1L,"Product A",1200));
		products.add(new Product(2L,"ProductB",1260));
		products.add(new Product(3L,"ProductC",1000));
        productRepository.saveAll(products);
        List<Product> foundProducts = productRepository.findByName("Product A");
        assertEquals(1,foundProducts.size());
    }
    @Test
    public void testFindByName_NonExistingName_ReturnsEmptyList() {
    	List<Product> products= new ArrayList<>();
		products.add(new Product(1L,"Product A",1200));
		products.add(new Product(2L,"ProductB",1260));
		products.add(new Product(3L,"ProductC",1000));
        productRepository.saveAll(products);
        List<Product> foundProducts = productRepository.findByName("Non-existing Product");
        assertTrue(foundProducts.isEmpty());
    }
    
    @Test
    public void testFindByPriceLessThan() {
    	List<Product> products= new ArrayList<>();
		products.add(new Product(1L,"Product A",1200));
		products.add(new Product(2L,"ProductB",1260));
		products.add(new Product(3L,"ProductC",1000));
        productRepository.saveAll(products);
        List<Product> findPriceLessThan = productRepository.findByPriceLessThan(1200);
        assertEquals(1,findPriceLessThan.size());
    }
    
    @Test
    public void testFindByPriceGreaterThan() {
    	List<Product> products= new ArrayList<>();
		products.add(new Product(1L,"Product A",1200));
		products.add(new Product(2L,"ProductB",1260));
		products.add(new Product(3L,"ProductC",1000));
        productRepository.saveAll(products);
        List<Product> findPriceGreateThan = productRepository.findByPriceGreaterThan(1000);
        assertEquals(2,findPriceGreateThan.size());
    }
}