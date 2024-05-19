package coursework;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBoxUsers;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Keep main frame open
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSelectUser = new JLabel("Select User:");
        lblSelectUser.setBounds(100, 80, 80, 25);
        contentPane.add(lblSelectUser);

        comboBoxUsers = new JComboBox<>();
        comboBoxUsers.setBounds(200, 80, 160, 25);
        contentPane.add(comboBoxUsers);

        // Populate the JComboBox with usernames
        populateUserComboBox();

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedUser = (String) comboBoxUsers.getSelectedItem();
                loginUser(selectedUser);
            }
        });
        btnLogin.setBounds(160, 160, 100, 25);
        contentPane.add(btnLogin);
    }

    private void populateUserComboBox() {
        try (BufferedReader reader = new BufferedReader(new FileReader("UserAccounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String username = parts[1].trim();
                    comboBoxUsers.addItem(username);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loginUser(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("UserAccounts.txt"))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7 && parts[1].trim().equals(username)) {
                    found = true;
                    String role = parts[6].trim().toLowerCase();
                    if (role.equals("admin")) {
                        JOptionPane.showMessageDialog(this, "Login successful. Welcome Admin!");
                        AdminFrame adminFrame = new AdminFrame();
                        adminFrame.setVisible(true);
                    } else if (role.equals("customer")) {
                        JOptionPane.showMessageDialog(this, "Login successful. Welcome Customer!");
                        CustomerFrame customerFrame = new CustomerFrame(username); // Pass the username to the CustomerFrame
                        customerFrame.setVisible(true);
                    }
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "Invalid username.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
