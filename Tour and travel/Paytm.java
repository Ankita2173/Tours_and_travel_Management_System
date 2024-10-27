package travel.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Paytm extends JFrame {

    Paytm() {
        setTitle("Travel Payment Portal");

        // Panel to hold payment form elements
        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new GridLayout(6, 2, 10, 10));
        
        // Title
        JLabel titleLabel = new JLabel("Travel Payment Portal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Card Number
        JLabel cardNumberLabel = new JLabel("Card Number:");
        JTextField cardNumberField = new JTextField();

        // Expiry Date
        JLabel expiryDateLabel = new JLabel("Expiry Date (MM/YY):");
        JTextField expiryDateField = new JTextField();

        // CVV
        JLabel cvvLabel = new JLabel("CVV:");
        JPasswordField cvvField = new JPasswordField();

        // Name on Card
        JLabel nameLabel = new JLabel("Name on Card:");
        JTextField nameField = new JTextField();

        // Add fields to panel
        paymentPanel.add(cardNumberLabel);
        paymentPanel.add(cardNumberField);
        paymentPanel.add(expiryDateLabel);
        paymentPanel.add(expiryDateField);
        paymentPanel.add(cvvLabel);
        paymentPanel.add(cvvField);
        paymentPanel.add(nameLabel);
        paymentPanel.add(nameField);

        // OK button to submit payment with validation
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cardNumber = cardNumberField.getText().trim();
                String expiryDate = expiryDateField.getText().trim();
                String cvv = new String(cvvField.getPassword()).trim();
                String name = nameField.getText().trim();

                // Validate card number (must be 16 digits)
                if (cardNumber.length() != 16 || !cardNumber.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "Invalid card number. It must be 16 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate expiry date (MM/YY format)
                if (!expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
                    JOptionPane.showMessageDialog(null, "Invalid expiry date. Use MM/YY format.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate CVV (must be 3 digits)
                if (cvv.length() != 3 || !cvv.matches("\\d{3}")) {
                    JOptionPane.showMessageDialog(null, "Invalid CVV. It must be 3 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate name on card (cannot be empty)
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Name on card cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // If all validations pass, show success message
                JOptionPane.showMessageDialog(null, "Payment Successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                // Clear the fields after successful payment
                cardNumberField.setText("");
                expiryDateField.setText("");
                cvvField.setText("");
                nameField.setText("");
            }
        });

        // Back button to close the window
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        // Layout setup
        setLayout(new BorderLayout(10, 10));
        add(titleLabel, BorderLayout.NORTH);
        add(paymentPanel, BorderLayout.CENTER);

        // Button panel to hold OK and Back buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    public static void main(String[] args) {
        new Paytm().setVisible(true);
    }
}
