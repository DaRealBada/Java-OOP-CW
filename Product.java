package coursework;

import javax.swing.JComboBox;

public abstract class Product {
    private int barcode;
    private String brand;
    private String color;
    protected ConnectivityType connectivity;
    private int quantityInStock;
    private double originalCost;
    private double retailPrice;
    private ProductCategory category;

    public Product(int barcode, String brand, String color, ConnectivityType connectivity, int quantityInStock, double originalCost, double retailPrice, ProductCategory category) {
        this.barcode = barcode;
        this.brand = brand;
        this.color = color;
        this.connectivity = connectivity;
        this.quantityInStock = quantityInStock;
        this.originalCost = originalCost;
        this.retailPrice = retailPrice;
        this.category = category;
    }

    // Constructor to initialize fields from a String representation
    public Product(String productString, JComboBox<ConnectivityType> connectivityComboBox, JComboBox<ProductCategory> categoryComboBox) {
        String[] fields = productString.split(",");
        if (fields.length != 8) {
            throw new IllegalArgumentException("Invalid product string format");
        }
        this.barcode = Integer.parseInt(fields[0]);
        this.brand = fields[1];
        this.color = fields[2];

        // Validate and parse connectivity type
        this.connectivity = (ConnectivityType) connectivityComboBox.getSelectedItem();

        this.quantityInStock = Integer.parseInt(fields[4]);
        this.originalCost = Double.parseDouble(fields[5]);
        this.retailPrice = Double.parseDouble(fields[6]);

        // Validate and parse product category
        this.category = ProductCategory.valueOf(fields[7].toUpperCase());
    }

    public int getBarcode() {
        return barcode;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public ConnectivityType getConnectivity() {
        return connectivity;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public double getOriginalCost() {
        return originalCost;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public ProductCategory getCategory() {
        return category;
    }

    @Override
    public abstract String toString();
}

