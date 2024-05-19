package coursework;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_8;
    private JComboBox<String> comboBox;
    private JComboBox<String> comboBox_1;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminFrame frame = new AdminFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AdminFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Use DISPOSE_ON_CLOSE to close only this frame
        setBounds(100, 100, 512, 372);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(6, 6, 500, 332);
        contentPane.add(tabbedPane);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("View Products", null, panel_1, null);

        JButton btnShowProducts = new JButton("Show Products");
        btnShowProducts.setBounds(10, 10, 150, 30);
        btnShowProducts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Read the contents of the Stock.txt file, sort by retail price, and display
                try (BufferedReader reader = new BufferedReader(new FileReader("Stock.txt"))) {
                    List<String> lines = new ArrayList<>();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }
                    // Sort the lines based on retail price
                    Collections.sort(lines, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            double price1 = Double.parseDouble(o1.split(",")[8].trim());
                            double price2 = Double.parseDouble(o2.split(",")[8].trim());
                            return Double.compare(price1, price2);
                        }
                    });
                    // Display the sorted lines
                    StringBuilder sb = new StringBuilder();
                    for (String sortedLine : lines) {
                        sb.append(sortedLine);
                        sb.append("\n");
                    }
                    textArea.setText(sb.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel_1.setLayout(null);

        panel_1.add(btnShowProducts);

        textArea = new JTextArea();
        textArea.setEditable(false);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 50, 563, 265);
        panel_1.add(scrollPane);

        JPanel panel = new JPanel();
        tabbedPane.addTab("Add Products", null, panel, null);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Barcode:");
        lblNewLabel.setBounds(3, 10, 53, 16);
        panel.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(71, 5, 130, 26);
        panel.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Connectivity:");
        lblNewLabel_1.setBounds(241, 125, 83, 16);
        panel.add(lblNewLabel_1);

        textField_1 = new JTextField();
        textField_1.setBounds(331, 5, 130, 26);
        panel.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Category:");
        lblNewLabel_2.setBounds(3, 48, 60, 16);
        panel.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Quantity in Stock:");
        lblNewLabel_3.setBounds(206, 48, 113, 16);
        panel.add(lblNewLabel_3);

        textField_3 = new JTextField();
        textField_3.setBounds(331, 43, 130, 26);
        panel.add(textField_3);
        textField_3.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Device Type:");
        lblNewLabel_4.setBounds(241, 10, 98, 16);
        panel.add(lblNewLabel_4);

        textField_4 = new JTextField();
        textField_4.setBounds(71, 81, 130, 26);
        panel.add(textField_4);
        textField_4.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("Brand:");
        lblNewLabel_5.setBounds(3, 86, 61, 16);
        panel.add(lblNewLabel_5);

        textField_5 = new JTextField();
        textField_5.setBounds(331, 81, 130, 26);
        panel.add(textField_5);
        textField_5.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("Colour:");
        lblNewLabel_6.setBounds(270, 86, 61, 16);
        panel.add(lblNewLabel_6);

        textField_6 = new JTextField();
        textField_6.setBounds(81, 120, 130, 26);
        panel.add(textField_6);
        textField_6.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("Retail Price:");
        lblNewLabel_7.setBounds(3, 125, 76, 16);
        panel.add(lblNewLabel_7);

        JLabel lblNewLabel_8 = new JLabel("Original Cost:");
        lblNewLabel_8.setBounds(2, 161, 88, 16);
        panel.add(lblNewLabel_8);

        textField_8 = new JTextField();
        textField_8.setBounds(91, 158, 130, 26);
        panel.add(textField_8);
        textField_8.setColumns(10);

        JButton btnNewButton = new JButton("Add Products");
        btnNewButton.setBounds(241, 156, 117, 29);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (FileWriter writer = new FileWriter("Stock.txt", true);
                     BufferedWriter bw = new BufferedWriter(writer);
                     PrintWriter out = new PrintWriter(bw)) {

                    // Read values from text fields and combo boxes
                    String barcode = textField.getText();
                    String connectivity = comboBox_1.getSelectedItem().toString();
                    String category = comboBox.getSelectedItem().toString();
                    String quantity = textField_3.getText();
                    String deviceType = textField_1.getText();
                    String brand = textField_4.getText();
                    String color = textField_5.getText();
                    String retailPrice = textField_6.getText();
                    String originalCost = textField_8.getText();

                    // Format the data and write to the file
                    String productData = String.format("\n%s, %s, %s, %s, %s, %s, %s, %s, %s\n",
                            barcode, category, deviceType, brand, color,
                            connectivity, quantity, originalCost, retailPrice);
                    out.println(productData);

                    // Clear the text fields for next entry
                    textField.setText("");
                    textField_3.setText("");
                    textField_1.setText("");
                    textField_4.setText("");
                    textField_5.setText("");
                    textField_6.setText("");
                    textField_8.setText("");

                    // Notify user of successful addition
                    System.out.println("Product added successfully!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(btnNewButton);

        comboBox = new JComboBox<>();
        comboBox.setBounds(71, 44, 123, 27);
        panel.add(comboBox);
        comboBox.addItem("MOUSE");
        comboBox.addItem("KEYBOARD");

        comboBox_1 = new JComboBox<>();
        comboBox_1.setBounds(336, 121, 125, 27);
        panel.add(comboBox_1);

        comboBox_1.addItem("WIRED");
        comboBox_1.addItem("WIRELESS");
        
        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("Logout", null, panel_2, null);
        panel_2.setLayout(null);
        
        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(171, 95, 147, 67);
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminFrame.this.dispose(); // Close only the AdminFrame
            }
        });
        panel_2.add(btnLogout);
    }
}
