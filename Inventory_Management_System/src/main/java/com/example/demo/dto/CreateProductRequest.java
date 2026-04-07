package com.example.demo.dto;

import java.math.BigDecimal;

public class CreateProductRequest {
    public String name;
    public String sku;
    public BigDecimal price;
    public Long warehouseId;
    public Integer initialQuantity;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Integer getInitialQuantity() {
		return initialQuantity;
	}
	public void setInitialQuantity(Integer initialQuantity) {
		this.initialQuantity = initialQuantity;
	}
    
}
