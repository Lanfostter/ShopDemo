package com.example.ShopDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ShopDemo.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{

}
