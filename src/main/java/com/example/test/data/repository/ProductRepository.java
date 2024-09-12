package com.example.test.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.test.data.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
