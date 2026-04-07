package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.constant.ErrorConstants;
import com.example.demo.dto.CreateProductRequest;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Product;
import com.example.demo.entity.Warehouse;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.WarehouseRepository;
import com.example.demo.exception.ApiException;

import jakarta.transaction.Transactional;

@Service
	public class ProductService {

	    @Autowired 
	    private ProductRepository productRepo;
	    @Autowired 
	    private InventoryRepository inventoryRepo;
	    @Autowired 
	    private WarehouseRepository warehouseRepo;

	    @Transactional
	    public Long createProduct(CreateProductRequest req) {

	        // ✅ SKU validation
	    	if (productRepo.existsBySku(req.sku)) {
	    	    throw new ApiException (
	    	        ErrorConstants  .SKU_ALREADY_EXISTS,
	    	        HttpStatus .BAD_REQUEST
	    	    );
	    	}

	        // ✅ Warehouse check
	    	Warehouse warehouse = warehouseRepo.findById(req.warehouseId)
	    	        .orElseThrow(() -> new ApiException    (
	    	                ErrorConstants.WAREHOUSE_NOT_FOUND,
	    	                HttpStatus.NOT_FOUND
	    	        ));

	        // ✅ Create product
	        Product product = new Product();
	        product.setName(req.name);
	        product.setSku(req.sku);
	        product.setPrice(req.price);

	        productRepo.save(product);

	        // ✅ Optional inventory
	        if (req.initialQuantity != null) {
	            Inventory inv = new Inventory();
	            inv.setProductId(product.getId());
	            inv.setWarehouseId(warehouse.getId());
	            inv.setQuantity(req.initialQuantity);

	            inventoryRepo.save(inv);
	        }

	        return product.getId();
	    }
	}


