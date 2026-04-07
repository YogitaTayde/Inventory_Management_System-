package com.example.demo.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
	@Table(uniqueConstraints = @UniqueConstraint(columnNames = "sku"))
	public class Product {
	    @Id
	    @GeneratedValue
	    private Long id;

	    private String name;
	    private String sku;
	    private BigDecimal price;
	    private Integer lowStockThreshold;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
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
		public Integer getLowStockThreshold() {
			return lowStockThreshold;
		}
		public void setLowStockThreshold(Integer lowStockThreshold) {
			this.lowStockThreshold = lowStockThreshold;
		}
	    
	}

