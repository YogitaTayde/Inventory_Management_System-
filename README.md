Issues Identified:

1. No transaction management leading to inconsistent data.
2. SKU uniqueness not enforced.
3. Product incorrectly tied to single warehouse.
4. No validation for inputs.
5. No error handling and rollback.
6. Assumes optional fields always present.

impact:
Data inconsistency, duplicate records, system crashes, and violation of business rules.

   

Part 2: Database Design
Key Tables:
Company, Warehouse, Product, Inventory, Supplier, ProductSupplier, Sale, InventoryLog,
ProductBundle.

Design Decisions:
• Unique SKU ensures global product identity.
• Inventory table enables multi-warehouse support.
• InventoryLog tracks changes for auditing.
• ProductBundle supports composite products.


Part 3: Low Stock Alert API
Endpoint: GET /api/companies/{companyId}/alerts/low-stock

Logic:
• Fetch company warehouses.
• Check inventory below threshold.
• Filter by recent sales.
• Calculate average usage and stockout days.


Edge Cases:
• No warehouses or inventory → empty response.
• No recent sales → skip.
• Division by zero handled.
• Missing supplier handled gracefully
