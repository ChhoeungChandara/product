package com.chandara.ProductsApplication.ProductController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.chandara.ProductsApplication.Entity.Product;
import com.chandara.ProductsApplication.ProductService.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {
	
	private final ProductService productService;
	
	@PostMapping
	public ResponseEntity<?> saveProduct(@RequestBody Product product){
		return ResponseEntity.ok(productService.Create(product));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getProduct(@PathVariable Long id){
		return ResponseEntity.ok(productService.getProductById(id));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Long id,@RequestBody Product product){
		Product updateProduct = productService.updateProduct(id, product);
		return ResponseEntity.ok(updateProduct);
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id){
		productService.deleteProduct(id);
		return ResponseEntity.ok("delete Success");
	}
	
//	@GetMapping
//	public ResponseEntity<?> getAll(){
//		return ResponseEntity.ok(productService.getAll());
//	}
	
	@GetMapping("filter")
	public ResponseEntity<?> getByName(@RequestParam("name") String name){
		return ResponseEntity.ok(productService.getProductByName(name));

   }
	@DeleteMapping()
	public ResponseEntity<?> deleteAllProduct(){
		productService.deleteAllProduct();
		return ResponseEntity.ok("deletes AllProduct Success");
	}
	@GetMapping("lessthan") 
	public ResponseEntity<?> findByPriceLessThan(@RequestParam double price){
		return ResponseEntity.ok(productService.findByPriceLessThan(price));

   }
	@GetMapping("Graterthan") 
	public ResponseEntity<?> findByPriceGreaterThan(@RequestParam double price){
		return ResponseEntity.ok(productService.findByPriceGreaterThan(price));

   }
	@GetMapping
	public ResponseEntity<?> page(@RequestParam(value = "page",defaultValue = "0") int page,
			                      @RequestParam(value = "size",defaultValue = "10") int size
			                      ){
		return ResponseEntity.ok().body(productService.pagenation(page, size));

   }
}
