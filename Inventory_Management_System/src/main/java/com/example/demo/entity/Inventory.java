package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity 
	@Table(uniqueConstraints = @UniqueConstraint  (columnNames = {"productId","warehouseId"}))
	public class Inventory {

	    @Id
	    @GeneratedValue
	    private Long id;

	    private Long productId;
	    private Long warehouseId;
	    private Integer quantity;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getProductId() {
			return productId;
		}
		public void setProductId(Long productId) {
			this.productId = productId;
		}
		public Long getWarehouseId() {
			return warehouseId;
		}
		public void setWarehouseId(Long warehouseId) {
			this.warehouseId = warehouseId;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
	    
	}


