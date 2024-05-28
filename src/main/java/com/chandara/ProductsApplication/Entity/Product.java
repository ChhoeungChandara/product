package com.chandara.ProductsApplication.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="products")
@Entity
@NoArgsConstructor
public class Product {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	
	private Long id;
	@Column(name="name")
	private String name;
	
	@Column(name="image_product")
	private String image;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price")
	private double price; // $

	public Product(Long id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
}
