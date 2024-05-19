package coursework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Customer {
    private ShoppingBasket basket;
    private String address;
    private String username;

    public Customer(String username) {
        this.basket = new ShoppingBasket();
        this.username = username;
        this.address = fetchAddressFromUserAccounts(); // Fetch address when customer is created
    }

    public void clearBasket() {
        basket.clear();
    }

    public void addToBasket(Product product) {
        basket.addItem(product);
    }

    public double calculateTotal() {
        return basket.calculateTotal();
    }

    public List<Product> getBasketItems() {
        return basket.getItems();
    }

    public String getAddress() {
        return address;
    }

    private String fetchAddressFromUserAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader("UserAccounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7 && parts[1].trim().equals(username)) {
                    return parts[3].trim() + " " + parts[4].trim() + ", " + parts[5].trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Address not found"; // Default message if address is not found
    }
}
