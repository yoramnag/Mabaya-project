package com.example.mabaya.service;

import java.util.List;

import com.example.mabaya.entity.Product;


public interface ProductService {

	public List<Product> findAll();
	
	public Product findById(int theId);
	
	public void save(Product theProduct);
	
	public void deleteById(int theId);
}
