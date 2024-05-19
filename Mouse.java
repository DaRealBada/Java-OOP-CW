package coursework;

public class Mouse extends Product {
    private int numberOfButtons;

    public Mouse(int barcode, String brand, String color, ConnectivityType connectivity, int quantityInStock, double originalCost, double retailPrice, ProductCategory category, int numberOfButtons) {
        super(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category);
        this.numberOfButtons = numberOfButtons;
    }

    public int getNumberOfButtons() {
        return numberOfButtons;
    }

    @Override
    public String toString() {
        return "Mouse Details: " + getBrand() + ", " + getColor() + ", " + getRetailPrice() + ", " + getCategory() + ", " + getNumberOfButtons() + " buttons";
    }
}







