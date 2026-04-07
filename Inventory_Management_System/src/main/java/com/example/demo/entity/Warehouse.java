package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
	public class Warehouse {
	    @Id
	    @GeneratedValue
	    private Long id;

	    private String name;
	    private Long companyId;
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
		public Long getCompanyId() {
			return companyId;
		}
		public void setCompanyId(Long companyId) {
			this.companyId = companyId;
		}
	    
}