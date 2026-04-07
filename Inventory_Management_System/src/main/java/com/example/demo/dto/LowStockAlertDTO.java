package com.example.demo.dto;


	public class LowStockAlertDTO {

	    public Long productId;
	    public String productName;
	    public String sku;

	    public Long warehouseId;
	    public String warehouseName;

	    public Integer currentStock;
	    public Integer threshold;

	    public Integer daysUntilStockout;

	    public Supplier supplier;

	    public static class Supplier {
	        public Long id;
	        public String name;
	        public String contactEmail;
	        
			public Supplier(Long id, String name, String contactEmail) {
				super();
				this.id = id;
				this.name = name;
				this.contactEmail = contactEmail;
			}
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
			public String getContactEmail() {
				return contactEmail;
			}
			public void setContactEmail(String contactEmail) {
				this.contactEmail = contactEmail;
			}
	        
	    }
	}

