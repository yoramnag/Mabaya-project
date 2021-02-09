package com.example.mabaya.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mabaya.entity.Campaign;
import com.example.mabaya.entity.Product;
import com.example.mabaya.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductRestController {
	
	
	private ProductService productService;
	
	@Autowired
	public ProductRestController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@GetMapping("/products")
	public List<Product> findAll() {
		return productService.findAll();
	}
	
	// add mapping for GET products/{productId}
	@GetMapping("/products/{productId}")
	public Product getCampaign(@PathVariable int productId) {
		Product theProduct = productService.findById(productId);
		
		if (theProduct == null) {
			throw new RuntimeException("campaign id not found - " + productId);
		}
		
		return theProduct;
	}
	
	// add mapping for POST /product - add new Product
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product theProduct) {
		
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		theProduct.setId(0);
		
		productService.save(theProduct);
		
		return theProduct;
	}
	
	// add mapping for PUT /products - update existing product
	@PutMapping("/products")
	public Product updateCampaignr(@RequestBody Product theProduct) {
		
		productService.save(theProduct);
		
		
		return theProduct;
	}
	
	// add mapping for DELETE /products/{productId} - delete product
	@DeleteMapping("/products/{productId}")
	public String deleteCampaign(@PathVariable int productId) {
		
		
		Product tempProduct = productService.findById(productId);
		
		// throw exception if null
		
		if (tempProduct == null) {
			throw new RuntimeException("campaign id not found - " + productId);
		}
		
		productService.deleteById(productId);
		
		return "Deleted campaign id - " + productId;
	}
	
	

}
