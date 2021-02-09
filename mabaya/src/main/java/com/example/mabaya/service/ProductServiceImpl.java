package com.example.mabaya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mabaya.dao.ProductRepository;
import com.example.mabaya.entity.Product;

@Service
public class ProductServiceImpl implements ProductService{
	
	private ProductRepository productRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}
	
	// return all products
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	// get a specific product by it's Id
	@Override
	public Product findById(int theId) {
		Optional<Product> result = productRepository.findById(theId);
		
		Product theProduct = null;
		
		if (result.isPresent()) {
			theProduct = result.get();
		} else {
			throw new RuntimeException("didn't find Product ID - " + theId);
		}
		return theProduct;
	}
	
	// add new product
	@Override
	public void save(Product theProduct) {
		productRepository.save(theProduct);
		
	}

	// delete a specific product by it's Id
	@Override
	public void deleteById(int theId) {
		productRepository.deleteById(theId);
		
	}

}
