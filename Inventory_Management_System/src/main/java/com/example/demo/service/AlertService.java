package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LowStockAlertDTO;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSupplier;
import com.example.demo.entity.Sale;
import com.example.demo.entity.Supplier;
import com.example.demo.entity.Warehouse;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ProductSupplierRepository;
import com.example.demo.repository.SaleRepository;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.repository.WarehouseRepository;


	@Service
	public class AlertService {

	    @Autowired private WarehouseRepository warehouseRepo;
	    @Autowired private InventoryRepository inventoryRepo;
	    @Autowired private ProductRepository productRepo;
	    @Autowired private SaleRepository saleRepo;
	    @Autowired private ProductSupplierRepository psRepo;
	    @Autowired private SupplierRepository supplierRepo;

	    public List<LowStockAlertDTO> getLowStock(Long companyId) {

	        List<Warehouse> warehouses = warehouseRepo.findByCompanyId(companyId);

	        List<Long> wIds = warehouses.stream().map(Warehouse::getId).toList();

	        List<Inventory> inventories = inventoryRepo.findByWarehouseIdIn(wIds);

	        List<LowStockAlertDTO> result = new ArrayList  <>();

	        for (Inventory inv : inventories) {

	            Product product = productRepo.findById(inv.getProductId()).orElse(null);
	            if (product == null) continue;

	            if (inv.getQuantity() >= product.getLowStockThreshold()) continue;

	            
	            List<Sale> sales = saleRepo.findByProductIdAndSaleDateAfter(
	                    product.getId(),
	                    LocalDateTime.now().minusDays(7)
	            );

	            if (sales.isEmpty()) continue;

	            int total = sales.stream().mapToInt(Sale::getQuantity).sum();
	            int avg = total / 7;
	            if (avg == 0) avg = 1;

	            int days = inv.getQuantity() / avg;

	            ProductSupplier  ps = psRepo.findByProductId(product.getId()).orElse(null);
	            Supplier   supplier = (ps != null)
	                    ? supplierRepo.findById(ps.getSupplierId()).orElse(null)
	                    : null;

	            LowStockAlertDTO dto = new LowStockAlertDTO(); 
	            dto.productId = product.getId();
	            dto.productName = product.getName();
	            dto.sku = product.getSku();
	            dto.warehouseId = inv.getWarehouseId();
	            dto.currentStock = inv.getQuantity();
	            dto.threshold = product.getLowStockThreshold();
	            dto.daysUntilStockout = days;

	            if (supplier != null) {
	                dto.supplier = new LowStockAlertDTO      .Supplier(
	                        supplier  .getId(),
	                        supplier.getName(),
	                        supplier.getContactEmail()
	                );
	            }

	            result.add(dto);
	        }

	        return result;
	    }
	}