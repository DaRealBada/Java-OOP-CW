package coursework;

import java.util.Comparator;
import java.util.List;

public class Admin {
    public void viewAllProducts(List<Product> products) {
        products.sort(Comparator.comparingDouble(Product::getRetailPrice));
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    public void addProduct(Product product, List<Product> stock) {
        stock.add(product);
    }
}

