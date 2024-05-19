package coursework;

import javax.swing.JComboBox;

public class ConcreteProduct extends Product {

    public ConcreteProduct(int barcode, String brand, String color, ConnectivityType connectivity, int quantityInStock, double originalCost, double retailPrice, ProductCategory category) {
        super(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category);
    }

    // Constructor to initialize fields from a String representation
    public ConcreteProduct(String productString, JComboBox<ConnectivityType> connectivityComboBox, JComboBox<ProductCategory> categoryComboBox) {
        super(productString, connectivityComboBox, categoryComboBox);
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s, %s, %s, %d, %.2f, %.2f", 
                getBarcode(), 
                getCategory().toString(), 
                getCategory(), 
                getBrand(), 
                getColor(), 
                getConnectivity(), 
                getQuantityInStock(), 
                getOriginalCost(), 
                getRetailPrice());
    }
}
