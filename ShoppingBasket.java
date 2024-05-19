package coursework;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    private List<Product> items;

    public ShoppingBasket() {
        items = new ArrayList<>();
    }

    public void clear() {
        items.clear();
    }

    public List<Product> getItems() {
        return items;
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : items) {
            total += product.getRetailPrice();
        }
        return total;
    }

    public void addItem(Product product) {
        items.add(product);
    }
}
