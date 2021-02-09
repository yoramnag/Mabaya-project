package com.example.mabaya.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mabaya.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product , Integer>{

}
