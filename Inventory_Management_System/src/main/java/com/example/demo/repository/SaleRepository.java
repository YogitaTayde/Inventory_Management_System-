package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByProductIdAndSaleDateAfter(Long productId, LocalDateTime date);
}