package com.chandara.ProductsApplication.ServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.chandara.ProductsApplication.Entity.Product;
import com.chandara.ProductsApplication.Exception.ResourceNotFoundException;
import com.chandara.ProductsApplication.ProductRepository.ProductRepository;
import com.chandara.ProductsApplication.ProductService.ProductService;
import com.chandara.ProductsApplication.ProductService.Impl.ProductServiceImpl;
@ExtendWith(MockitoExtension.class)
public class TestProductServiceImpl {
	@Mock
	private  ProductRepository productRepository;
	private ProductService productService;
	@BeforeEach
	public void setUp() {
		productService = new ProductServiceImpl(productRepository);
	}
	@Captor
	private ArgumentCaptor<Product> productCaptor;


	@Test
	public void testCreate() {
		Product product = new Product(1L,"ProductA",1220);
		productService.Create(product);
		verify(productRepository,timeout(1)).save(product);
	}
	
	@Test

	public void testGetProductById() {
		Product product = new Product(1L,"ProductA",1220);	
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		productService.getProductById(1L);
		assertEquals(1,product.getId());
		assertEquals("ProductA", product.getName());
	}
	
	@Test

	public void testGetProductByIdNotFound() {
		Long productId = 1L;
		when(productRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(productId));
		verify(productRepository,times(1)).findById(productId);
	}
	@Test
	public void testUpdate() {
		Product exitingProduct = new Product(1L,"ProductA",1220);
		Product updateProduct = new Product(1L,"ProductA",1320);
		when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(exitingProduct));
		Product afterUpdateProduct = productService.updateProduct(1L, updateProduct);
		
		verify(productRepository,times(1)).findById(1L);
		verify(productRepository,times(1)).save(productCaptor.capture());
		assertEquals(1L,productCaptor.getValue().getId());
		assertEquals(1320,productCaptor.getValue().getPrice());
	}
	
	@Test
	public void deleteProductById() {
		Product product = new Product(1L,"ProductA",1220);
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		productService.deleteProduct(1L);
		verify(productRepository,times(1)).delete(product);
	}
	
	@Test
	public void getAllProduct() {
		List<Product> products= new ArrayList<>();
		
		products.add(new Product(1L,"ProductA",1222));
		products.add(new Product(2L,"ProductB",1220));
		products.add(new Product(3L,"ProductC",1220));
		when(productRepository.findAll()).thenReturn(products);
		
		List<Product> listProduct = productService.getAll();
		verify(productRepository,times(1)).findAll();
		assertEquals(listProduct,products);
	}
	@Test
	public void testSearchProductByName() {
		String productName = "ProductA";
        List<Product> products= new ArrayList<>();
		products.add(new Product(1L,"ProductA",1222));
		products.add(new Product(2L,"ProductA",1220));
		products.add(new Product(3L,"ProductA",1220));
		
		when(productRepository.findByName(productName)).thenReturn(products);
		List<Product> searchName = productService.getProductByName(productName);
		verify(productRepository,times(1)).findByName(productName);
		assertEquals(products,searchName);
	}
	@Test
	public void testfindByPriceLessThan() {
		List<Product> products= new ArrayList<>();
		products.add(new Product(1L,"ProductA",1200));
		products.add(new Product(2L,"ProductB",1260));
		products.add(new Product(3L,"ProductC",1000));
	    when(productRepository.findByPriceLessThan(1200)).thenReturn(products);
	    List<Product> productsResult = productService.findByPriceLessThan(1200);
	    verify(productRepository,times(1)).findByPriceLessThan(1200);
	    assertEquals(products.get(2).getPrice(),productsResult.get(2).getPrice()); 
	}
	@Test
	public void testfindByPriceGreateThan() {
		List<Product> products= new ArrayList<>();
		products.add(new Product(1L,"ProductA",1200));
		products.add(new Product(2L,"ProductB",1260));
		products.add(new Product(3L,"ProductC",1000));
	    when(productRepository.findByPriceGreaterThan(1200)).thenReturn(products);
	    List<Product> productsResult = productService.findByPriceGreaterThan(1200);
	    verify(productRepository,times(1)).findByPriceGreaterThan(1200);
	    assertEquals(products.get(1).getPrice(),productsResult.get(1).getPrice()); 
	}
	
	@Test
	public void testPagenation() {
		Page<Product> mockPage = mock(Page.class);
		when(productRepository.findAll(any(PageRequest.class))).thenReturn(mockPage);
		Page<Product> result = productService.pagination(1, 10);
		 assertEquals(mockPage, result);
	}
	
}

