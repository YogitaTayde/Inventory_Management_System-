package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CreateProductRequest;
import com.example.demo.service.ProductService;
@RestController  
public class ProductController {
	@Autowired 
	ProductService service;    
	@PostMapping ("/products")
	public ResponseEntity create(@RequestBody  CreateProductRequest  req) {
	    Long id = service.createProduct(req);
	    return ResponseEntity.ok(Map.of("product_id", id));
	}

}
