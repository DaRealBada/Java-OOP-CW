// Keyboard Class
package coursework;

public class Keyboard extends Product {
    private String type;
    private String layout;

    public Keyboard(int barcode, String brand, String color, ConnectivityType connectivity, int quantityInStock, double originalCost, double retailPrice, ProductCategory category, String type, String layout) {
        super(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category);
        this.type = type;
        this.layout = layout;
    }

    public String getType() {
        return type;
    }

    public String getLayout() {
        return layout;
    }

    @Override
    public String toString() {
        return "Keyboard Details: " + getBrand() + ", " + getColor() + ", " + getRetailPrice() + ", " + getCategory() + ", " + getType() + ", " + getLayout();
    }
}
