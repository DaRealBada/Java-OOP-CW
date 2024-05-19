package coursework;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class CustomerFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldPayPal;
    private JTextField textFieldCreditCard;
    private JTextField textFieldSecurityCode;
    private JTextArea textArea;
    private Customer customer;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CustomerFrame frame = new CustomerFrame("user2"); // Example username
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CustomerFrame(String username) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Use DISPOSE_ON_CLOSE to close only this frame
        setBounds(100, 100, 512, 372);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        customer = new Customer(username);
        contentPane.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(6, 6, 481, 309);
        contentPane.add(tabbedPane);
        
        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("View Products", null, panel_1, null);
        panel_1.setLayout(null);
        
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 45, 400, 150);
        panel_1.add(scrollPane);
        
        JButton btnShowProducts = new JButton("Show Available Products");
        btnShowProducts.setBounds(20, 6, 206, 29);
        btnShowProducts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Read the contents of the Stock.txt file and display them, excluding original cost
                try (BufferedReader reader = new BufferedReader(new FileReader("Stock.txt"))) {
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length >= 9) {
                            // Format: barcode, category, device type, brand, color, connectivity, quantity, original cost, retail price
                            // Exclude the original cost (parts[7])
                            sb.append(String.format("%s, %s, %s, %s, %s, %s, %s, %s%n",
                                    parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(),
                                    parts[4].trim(), parts[5].trim(), parts[6].trim(), parts[8].trim()));
                        }
                    }
                    textArea.setText(sb.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel_1.add(btnShowProducts);
        
        JButton btnFilterByBarcode = new JButton("Filter by Barcode");
        btnFilterByBarcode.setBounds(248, 217, 162, 29);
        btnFilterByBarcode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String barcode = JOptionPane.showInputDialog(null, "Enter Barcode:");
                if (barcode != null && !barcode.trim().isEmpty()) {
                    filterByBarcode(barcode);
                }
            }
        });
        panel_1.add(btnFilterByBarcode);
        
        JButton btnFilterByMouseButton = new JButton("Filter by Mouse Button Quantity");
        btnFilterByMouseButton.setBounds(10, 217, 226, 29);
        btnFilterByMouseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String quantityStr = JOptionPane.showInputDialog(null, "Enter Mouse Button Quantity:");
                if (quantityStr != null && !quantityStr.trim().isEmpty()) {
                    try {
                        int quantity = Integer.parseInt(quantityStr);
                        filterByMouseButtonQuantity(quantity);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid integer quantity.");
                    }
                }
            }
        });
        panel_1.add(btnFilterByMouseButton);
        
        JButton btnAddToBasket = new JButton("Add To Basket");
        btnAddToBasket.setBounds(238, -3, 134, 46);
        panel_1.add(btnAddToBasket);
        btnAddToBasket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedProduct = textArea.getSelectedText();
                if (selectedProduct != null && !selectedProduct.trim().isEmpty()) {
                    String[] parts = selectedProduct.split(",");
                    if (parts.length >= 8) {
                        String barcode = parts[0].trim();
                        Product product = getProductByBarcode(barcode);
                        if (product != null) {
                            customer.addToBasket(product);
                            System.out.println("Product added to basket: " + product);
                        }
                    }
                }
            }
        });

        JPanel panel = new JPanel();
        tabbedPane.addTab("View Shopping Basket", null, panel, null);
        panel.setLayout(null);

        JTextArea basketTextArea = new JTextArea();
        JScrollPane basketScrollPane = new JScrollPane(basketTextArea);
        basketScrollPane.setBounds(10, 65, 400, 150); // Adjust bounds as needed
        panel.add(basketScrollPane);

        JButton btnShowBasket = new JButton("Show Basket");
        btnShowBasket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                for (Product item : customer.getBasketItems()) {
                    sb.append(item).append("\n");
                }
                basketTextArea.setText(sb.toString());
            }
        });
        btnShowBasket.setBounds(150, 24, 117, 29);
        panel.add(btnShowBasket);

        JButton btnClearBasket = new JButton("Clear Basket");
        btnClearBasket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customer.clearBasket();
                basketTextArea.setText("");
            }
        });
        btnClearBasket.setBounds(162, 228, 117, 29);
        panel.add(btnClearBasket);

        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("Payment", null, panel_2, null);
        panel_2.setLayout(null);

        JLabel lblPayPal = new JLabel("PayPal");
        lblPayPal.setBounds(17, 38, 75, 16);
        panel_2.add(lblPayPal);

        textFieldPayPal = new JTextField();
        textFieldPayPal.setBounds(150, 33, 200, 26);
        panel_2.add(textFieldPayPal);
        textFieldPayPal.setColumns(10);

        JLabel lblCreditCard = new JLabel("Credit card:");
        lblCreditCard.setBounds(17, 72, 92, 16);
        panel_2.add(lblCreditCard);

        textFieldCreditCard = new JTextField();
        textFieldCreditCard.setBounds(150, 67, 130, 26);
        panel_2.add(textFieldCreditCard);
        textFieldCreditCard.setColumns(10);

        JLabel lblSecurityCode = new JLabel("Security Code:");
        lblSecurityCode.setBounds(17, 106, 92, 16);
        panel_2.add(lblSecurityCode);

        textFieldSecurityCode = new JTextField();
        textFieldSecurityCode.setBounds(150, 101, 130, 26);
        panel_2.add(textFieldSecurityCode);
        textFieldSecurityCode.setColumns(10);

        JButton btnPay = new JButton("PAY");
        btnPay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String paypalEmail = textFieldPayPal.getText();
                String creditCardNumber = textFieldCreditCard.getText();
                String securityCode = textFieldSecurityCode.getText();

                if (!paypalEmail.isEmpty()) {
                    if (paypalEmail.contains("@") && paypalEmail.contains(".")) {
                        pay("PayPal", paypalEmail);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a valid PayPal email address.");
                    }
                } else if (!creditCardNumber.isEmpty() && !securityCode.isEmpty()) {
                    if (creditCardNumber.length() == 6 && securityCode.length() == 3 && creditCardNumber.matches("\\d+") && securityCode.matches("\\d+")) {
                        pay("Credit Card", creditCardNumber);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a valid 6-digit card number and 3-digit security code.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter payment details.");
                }
            }
        });
        btnPay.setBounds(180, 205, 117, 29);
        panel_2.add(btnPay);
        
        JPanel panel_3 = new JPanel();
        tabbedPane.addTab("Logout", null, panel_3, null);
        panel_3.setLayout(null);
        
        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(136, 80, 191, 82);
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CustomerFrame.this.dispose(); // Close only the CustomerFrame
            }
        });
        panel_3.add(btnLogout);
        
        JLabel lblNewLabel = new JLabel("Please Click To Logout");
        lblNewLabel.setBounds(136, 18, 191, 50);
        panel_3.add(lblNewLabel);
    }

    private void pay(String paymentMethod, String paymentDetails) {
        double amount = customer.calculateTotal(); // Call calculateTotal() on customer object
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String deliveryAddress = customer.getAddress(); // Get the address from the customer object

        String receipt;
        if (paymentMethod.equals("PayPal")) {
            receipt = String.format("Total Amount: %s\nPaid by PayPal using %s on %s\nDelivery Address: %s", amount, paymentDetails, today, deliveryAddress);
        } else if (paymentMethod.equals("Credit Card")) {
            receipt = String.format("Total Amount: %s\nPaid by Credit Card using %s on %s\nDelivery Address: %s", amount, paymentDetails, today, deliveryAddress);
        } else {
            receipt = "Invalid payment method.";
        }

        JOptionPane.showMessageDialog(null, receipt);

        // Update stock and clear basket
        customer.clearBasket();
    }

    private void filterByBarcode(String barcode) {
        StringBuilder result = new StringBuilder();
        boolean found = false;

        // Read the contents of the Stock.txt file
        try (BufferedReader reader = new BufferedReader(new FileReader("Stock.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into parts separated by commas
                String[] parts = line.split(",");
                // Check if the barcode matches the one provided
                if (parts.length > 0 && parts[0].trim().equals(barcode)) {
                    // Convert relevant parts to uppercase
                    parts[1] = parts[1].trim().toUpperCase();
                    parts[2] = parts[2].trim().toUpperCase();
                    parts[5] = parts[5].trim().toUpperCase();

                    // Product found, append its details to the result
                    result.append(String.join(", ", parts)).append("\n");
                    found = true;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Display the result
        if (found) {
            textArea.setText(result.toString());
        } else {
            textArea.setText("Product with barcode " + barcode + " not found.");
        }
    }

    private void filterByMouseButtonQuantity(int quantity) {
        StringBuilder result = new StringBuilder();
        boolean found = false;

        // Read the contents of the Stock.txt file
        try (BufferedReader reader = new BufferedReader(new FileReader("Stock.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into parts separated by commas
                String[] parts = line.split(",");

                // Check if the mouse button quantity matches the one provided
                if (parts.length > 0 && parts[1].trim().equalsIgnoreCase("mouse") && Integer.parseInt(parts[9].trim()) == quantity) {
                    // Convert relevant parts to uppercase
                    parts[1] = parts[1].trim().toUpperCase();
                    parts[2] = parts[2].trim().toUpperCase();
                    parts[5] = parts[5].trim().toUpperCase();

                    // Product found, append its details to the result
                    result.append(String.join(", ", parts)).append("\n");
                    found = true;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Display the result
        if (found) {
            textArea.setText(result.toString());
        } else {
            textArea.setText("No products found with mouse button quantity: " + quantity);
        }
    }

    private Product getProductByBarcode(String barcode) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Stock.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 9 && parts[0].trim().equals(barcode)) {
                    String connectivityString = parts[5].trim().toUpperCase();
                    ConnectivityType connectivityType = getConnectivityType(connectivityString);
                    if (connectivityType == null) {
                        System.out.println("Invalid connectivity type: " + parts[5]);
                        continue; // Skip this line if connectivity type is invalid
                    }

                    String categoryString = parts[2].trim().toUpperCase();
                    ProductCategory productCategory = getProductCategory(categoryString);
                    if (productCategory == null) {
                        System.out.println("Invalid product category: " + parts[2]);
                        continue; // Skip this line if product category is invalid
                    }

                    return new ConcreteProduct(
                        Integer.parseInt(parts[0].trim()), // barcode
                        parts[3].trim(),                   // brand
                        parts[4].trim(),                   // color
                        connectivityType,                  // connectivity
                        Integer.parseInt(parts[6].trim()), // quantityInStock
                        Double.parseDouble(parts[7].trim()), // originalCost
                        Double.parseDouble(parts[8].trim()), // retailPrice
                        productCategory // category
                    );
                }
            }
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private ConnectivityType getConnectivityType(String connectivityString) {
        try {
            return ConnectivityType.valueOf(connectivityString);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private ProductCategory getProductCategory(String categoryString) {
        try {
            return ProductCategory.valueOf(categoryString);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
