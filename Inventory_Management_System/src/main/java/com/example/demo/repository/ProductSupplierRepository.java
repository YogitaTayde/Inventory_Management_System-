package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ProductSupplier;

public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, Long>{
    Optional<ProductSupplier> findByProductId(Long productId);
}