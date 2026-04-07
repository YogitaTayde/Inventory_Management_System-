Issues Identified:

1. No transaction management leading to inconsistent data.
2. SKU uniqueness not enforced.
3. Product incorrectly tied to single warehouse.
4. No validation for inputs.
5. No error handling and rollback.
6. Assumes optional fields always present.

impact:
Data inconsistency, duplicate records, system crashes, and violation of business rules.
Fixed Approach:
@Transactional
public Long createProduct(CreateProductRequest req) {
    if (productRepo.existsBySku(req.sku)) {
        throw new ApiException("SKU already exists", HttpStatus.BAD_REQUEST);
    }
    Warehouse warehouse = warehouseRepo.findById(req.warehouseId)
        .orElseThrow(() -> new ApiException("Warehouse not found", HttpStatus.NOT_FOUND));
    Product product = new Product();
    product.setName(req.name);
    product.setSku(req.sku);
    product.setPrice(req.price);
    productRepo.save(product);
    if (req.initialQuantity != null) {
        Inventory inv = new Inventory();
        inv.setProductId(product.getId());
        inv.setWarehouseId(warehouse.getId());
        inv.setQuantity(req.initialQuantity);
        inventoryRepo.save(inv);
    }
    return product.getId();
}

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

• Attach supplier details.
int avg = totalSales / 7;
if (avg == 0) avg = 1;
int daysLeft = inventory / avg;

Edge Cases:
• No warehouses or inventory → empty response.
• No recent sales → skip.
• Division by zero handled.
• Missing supplier handled gracefully
