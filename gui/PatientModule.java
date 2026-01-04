package gui;

import integration.CppBridge;
import Config.Config;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class PatientModule extends JPanel {

    private JTextField txtPatientId, txtName, txtAge, txtBloodGroup, txtContact;
    private JTextArea txtOutput;
    private JButton btnAdd, btnSearch, btnDelete, btnViewAll, btnClear;

    private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private final Color SUCCESS_COLOR = new Color(39, 174, 96);
    private final Color DANGER_COLOR = new Color(231, 76, 60);
    private final Color LIGHT_BG = new Color(236, 240, 241);

    public PatientModule() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Main content without scroll - fit everything on screen
        JPanel contentWrapper = new JPanel(new GridBagLayout());
        contentWrapper.setBackground(Color.WHITE);
        contentWrapper.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.4;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 10);

        JPanel leftPanel = createInputPanel();
        contentWrapper.add(leftPanel, gbc);

        gbc.weightx = 0.6;
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 10, 0, 0);

        JPanel rightPanel = createOutputPanel();
        contentWrapper.add(rightPanel, gbc);

        add(contentWrapper, BorderLayout.CENTER);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Title Section - Compact
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(PRIMARY_COLOR);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Patient Registration");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Enter patient information below");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(236, 240, 241));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitleLabel);

        panel.add(titlePanel, BorderLayout.NORTH);

        // Form Panel - Compact spacing
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        txtPatientId = createCompactTextField();
        txtName = createCompactTextField();
        txtAge = createCompactTextField();
        txtBloodGroup = createCompactTextField();
        txtContact = createCompactTextField();

        formPanel.add(createCompactFieldPanel("Patient ID *", txtPatientId));
        formPanel.add(Box.createVerticalStrut(8));
        formPanel.add(createCompactFieldPanel("Full Name *", txtName));
        formPanel.add(Box.createVerticalStrut(8));
        formPanel.add(createCompactFieldPanel("Age *", txtAge));
        formPanel.add(Box.createVerticalStrut(8));
        formPanel.add(createCompactFieldPanel("Blood Group *", txtBloodGroup));
        formPanel.add(Box.createVerticalStrut(8));
        formPanel.add(createCompactFieldPanel("Contact *", txtContact));
        formPanel.add(Box.createVerticalStrut(12));

        // Button Panel - Compact
        JPanel buttonPanel = createCompactButtonPanel();
        formPanel.add(buttonPanel);

        panel.add(formPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createCompactFieldPanel(String labelText, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(new Color(52, 73, 94));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        textField.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);
        panel.add(Box.createVerticalStrut(4));
        panel.add(textField);

        return panel;
    }

    private JTextField createCompactTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setPreferredSize(new Dimension(300, 32));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(4, 10, 4, 10)
        ));
        return field;
    }

    private JPanel createCompactButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 8, 8));
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

        btnAdd = createCompactButton("➕ Add", SUCCESS_COLOR);
        btnSearch = createCompactButton("🔍 Search", PRIMARY_COLOR);
        btnDelete = createCompactButton("🗑️ Delete", DANGER_COLOR);
        btnViewAll = createCompactButton("📋 View All", PRIMARY_COLOR);
        btnClear = createCompactButton("🔄 Clear", new Color(149, 165, 166));

        JButton btnDummy = new JButton();
        btnDummy.setVisible(false);

        panel.add(btnAdd);
        panel.add(btnSearch);
        panel.add(btnDelete);
        panel.add(btnViewAll);
        panel.add(btnClear);
        panel.add(btnDummy);

        btnAdd.addActionListener(e -> addPatient());
        btnSearch.addActionListener(e -> searchPatient());
        btnDelete.addActionListener(e -> deletePatient());
        btnViewAll.addActionListener(e -> viewAllPatients());
        btnClear.addActionListener(e -> clearFields());

        return panel;
    }

    private JButton createCompactButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 11));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private JPanel createOutputPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBackground(Color.WHITE);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(LIGHT_BG);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));

        JLabel titleLabel = new JLabel("📊 Output Console");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(52, 73, 94));

        titlePanel.add(titleLabel, BorderLayout.WEST);
        panel.add(titlePanel, BorderLayout.NORTH);

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        txtOutput.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtOutput.setBackground(new Color(250, 250, 250));
        txtOutput.setForeground(new Color(44, 62, 80));
        txtOutput.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        txtOutput.setLineWrap(true);
        txtOutput.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(txtOutput);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void addPatient() {
        String id = txtPatientId.getText().trim();
        String name = txtName.getText().trim();
        String ageStr = txtAge.getText().trim();
        String bloodGroup = txtBloodGroup.getText().trim();
        String contact = txtContact.getText().trim();

        if (id.isEmpty() || name.isEmpty() || ageStr.isEmpty() || bloodGroup.isEmpty() || contact.isEmpty()) {
            showError("❌ ERROR: All fields are required!");
            return;
        }

        try {
            int age = Integer.parseInt(ageStr);
            if (age <= 0 || age > 150) {
                showError("❌ ERROR: Invalid age! (Must be 1-150)");
                return;
            }
        } catch (NumberFormatException ex) {
            showError("❌ ERROR: Age must be a number!");
            return;
        }

        txtOutput.setText("⏳ Processing...");
        txtOutput.setForeground(new Color(52, 152, 219));

        String result = CppBridge.processRequest(
                Config.PATIENT_EXE,
                Config.PATIENT_INPUT,
                Config.PATIENT_OUTPUT,
                "ADD", id, name, ageStr, bloodGroup, contact
        );

        if (result.contains("SUCCESS")) {
            txtOutput.setForeground(SUCCESS_COLOR);
        } else {
            txtOutput.setForeground(DANGER_COLOR);
        }
        txtOutput.setText(result);
    }

    private void searchPatient() {
        String id = txtPatientId.getText().trim();

        if (id.isEmpty()) {
            showError("❌ ERROR: Please enter Patient ID!");
            return;
        }

        txtOutput.setText("🔍 Searching...");
        txtOutput.setForeground(new Color(52, 152, 219));

        String result = CppBridge.processRequest(
                Config.PATIENT_EXE,
                Config.PATIENT_INPUT,
                Config.PATIENT_OUTPUT,
                "SEARCH", id
        );

        if (result.contains("FOUND")) {
            txtOutput.setForeground(SUCCESS_COLOR);
        } else {
            txtOutput.setForeground(DANGER_COLOR);
        }
        txtOutput.setText(result);
    }

    private void deletePatient() {
        String id = txtPatientId.getText().trim();

        if (id.isEmpty()) {
            showError("❌ ERROR: Please enter Patient ID!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete patient " + id + "?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            txtOutput.setText("🗑️ Deleting...");
            txtOutput.setForeground(new Color(52, 152, 219));

            String result = CppBridge.processRequest(
                    Config.PATIENT_EXE,
                    Config.PATIENT_INPUT,
                    Config.PATIENT_OUTPUT,
                    "DELETE", id
            );

            if (result.contains("SUCCESS")) {
                txtOutput.setForeground(SUCCESS_COLOR);
            } else {
                txtOutput.setForeground(DANGER_COLOR);
            }
            txtOutput.setText(result);
        }
    }

    private void viewAllPatients() {
        txtOutput.setText("📋 Loading...");
        txtOutput.setForeground(new Color(52, 152, 219));

        String result = CppBridge.processRequest(
                Config.PATIENT_EXE,
                Config.PATIENT_INPUT,
                Config.PATIENT_OUTPUT,
                "VIEWALL"
        );

        txtOutput.setForeground(new Color(44, 62, 80));
        txtOutput.setText(result);
    }

    private void clearFields() {
        txtPatientId.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtBloodGroup.setText("");
        txtContact.setText("");
        txtOutput.setText("");
        txtOutput.setForeground(new Color(44, 62, 80));
    }

    private void showError(String message) {
        txtOutput.setForeground(DANGER_COLOR);
        txtOutput.setText(message);
    }
}