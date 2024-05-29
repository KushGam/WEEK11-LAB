package week11.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import week11.domain.Country;
import week11.domain.Parcel;

public class ParcelFrame extends JFrame {
    private JTextField weightField;
    private JTextArea addressArea;
    private JComboBox<String> countryComboBox;
    private JCheckBox fragileCheckBox;
    private JRadioButton flatButton, smallButton, mediumButton, largeButton;
    private JLabel messageLabel;
    private ArrayList<Country> countries;

    public ParcelFrame(ArrayList<Country> countries) {
        this.countries = countries;
        setTitle("Parcel Delivery Calculator");
        setLayout(new GridLayout(7, 1)); // Single column layout

        // Title
        JLabel titleLabel = new JLabel("Parcel Delivery Calculator", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(46, 139, 87));
        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        add(titlePanel);

        // Weight
        JPanel weightPanel = new JPanel();
        weightPanel.add(new JLabel("Weight (kg):"));
        weightField = new JTextField(5);
        weightPanel.add(weightField);
        add(weightPanel);

        // Package Size
        JPanel sizePanel = new JPanel();
        sizePanel.add(new JLabel("Package size:"));
        flatButton = new JRadioButton("Flat");
        smallButton = new JRadioButton("Small");
        mediumButton = new JRadioButton("Medium");
        largeButton = new JRadioButton("Large");
        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(flatButton);
        sizeGroup.add(smallButton);
        sizeGroup.add(mediumButton);
        sizeGroup.add(largeButton);
        flatButton.setSelected(true); // Default selection
        sizePanel.add(flatButton);
        sizePanel.add(smallButton);
        sizePanel.add(mediumButton);
        sizePanel.add(largeButton);
        add(sizePanel);

        // Delivery Address
        JPanel addressPanel = new JPanel();
        addressPanel.add(new JLabel("Deliver to:"));
        addressArea = new JTextArea(3, 20);
        addressPanel.add(new JScrollPane(addressArea));
        add(addressPanel);

        // Country and Fragile
        JPanel countryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        countryPanel.add(new JLabel("Country:"));
        countryComboBox = new JComboBox<>();
        for (Country country : countries) {
            countryComboBox.addItem(country.getName());
        }
        countryPanel.add(countryComboBox);
        fragileCheckBox = new JCheckBox("Fragile Item?");
        countryPanel.add(fragileCheckBox);
        add(countryPanel);

        // Message Label
        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(new Color(255, 239, 213));
        messageLabel = new JLabel("Enter details above.");
        messagePanel.add(messageLabel);
        add(messagePanel);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton calculateButton = new JButton("Calculate Cost");
        calculateButton.addActionListener(new ButtonResponder());
        JButton clearButton = new JButton("Clear All");
        clearButton.addActionListener(new ButtonResponder());
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        add(buttonPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class ButtonResponder implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            if (source.getText().equals("Calculate Cost")) {
                processParcel();
            } else if (source.getText().equals("Clear All")) {
                clearAllFields();
            }
        }
    }

    private void processParcel() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            String address = addressArea.getText();
            boolean isFragile = fragileCheckBox.isSelected();
            int sizeCategory = Parcel.FLAT;
            if (smallButton.isSelected()) sizeCategory = Parcel.SMALL_BOX;
            else if (mediumButton.isSelected()) sizeCategory = Parcel.MEDIUM_BOX;
            else if (largeButton.isSelected()) sizeCategory = Parcel.LARGE_BOX;

            int countryIndex = countryComboBox.getSelectedIndex();
            Country country = countries.get(countryIndex);

            Parcel parcel = new Parcel(address, country, weight, isFragile, sizeCategory);
            double cost = parcel.calculateCost();
            messageLabel.setText("The cost will be $" + cost);
        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid weight.");
        }
    }

    private void clearAllFields() {
        weightField.setText("");
        addressArea.setText("");
        fragileCheckBox.setSelected(false);
        flatButton.setSelected(true);
        countryComboBox.setSelectedIndex(0);
        messageLabel.setText("Enter details above.");
    }
}