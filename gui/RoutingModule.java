package gui;

import integration.CppBridge;
import Config.Config;
import javax.swing.*;
import java.awt.*;

public class RoutingModule extends JPanel {

    private JComboBox<String> cmbSpecialty, cmbLocationStart, cmbLocationEnd;
    private JTextArea txtOutput;
    private JButton btnViewDocs, btnAssign, btnPath, btnClear;

    private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private final Color SUCCESS_COLOR = new Color(39, 174, 96);
    private final Color WARNING_COLOR = new Color(243, 156, 18);
    private final Color LIGHT_BG = new Color(236, 240, 241);

    public RoutingModule() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

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

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(SUCCESS_COLOR);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Doctor Assignment & Navigation");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Assign doctors and find routes");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(236, 240, 241));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitleLabel);

        panel.add(titlePanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // Doctor Section
        JPanel assignPanel = new JPanel();
        assignPanel.setLayout(new BoxLayout(assignPanel, BoxLayout.Y_AXIS));
        assignPanel.setBackground(Color.WHITE);
        assignPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        assignPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        JLabel assignTitle = new JLabel("--- Assign Doctor ---");
        assignTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        assignTitle.setForeground(PRIMARY_COLOR);
        assignTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        assignPanel.add(assignTitle);
        assignPanel.add(Box.createVerticalStrut(8));

        String[] specialties = {"General", "Neurology", "Cardiology", "Surgery", "Diagnostics"};
        cmbSpecialty = createCompactComboBox(specialties);
        assignPanel.add(createCompactFieldPanel("Specialty:", cmbSpecialty));
        assignPanel.add(Box.createVerticalStrut(8));

        JPanel doctorBtnPanel = new JPanel(new GridLayout(1, 2, 8, 0));
        doctorBtnPanel.setBackground(Color.WHITE);
        doctorBtnPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));

        btnAssign = createCompactButton("👨‍⚕️ Assign", SUCCESS_COLOR);
        btnViewDocs = createCompactButton("📋 View", PRIMARY_COLOR);

        doctorBtnPanel.add(btnAssign);
        doctorBtnPanel.add(btnViewDocs);

        assignPanel.add(doctorBtnPanel);

        formPanel.add(assignPanel);
        formPanel.add(Box.createVerticalStrut(12));

        // Navigation Section
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBackground(Color.WHITE);
        navPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(WARNING_COLOR, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        navPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        JLabel navTitle = new JLabel("--- Navigation (Shortest Path) ---");
        navTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        navTitle.setForeground(WARNING_COLOR);
        navTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        navPanel.add(navTitle);
        navPanel.add(Box.createVerticalStrut(8));

        String[] locations = {
                "Entrance", "Triage", "Corridor A", "Corridor B",
                "General Ward", "ICU", "X-Ray", "Pharmacy", "Surgery"
        };
        cmbLocationStart = createCompactComboBox(locations);
        cmbLocationEnd = createCompactComboBox(locations);
        cmbLocationEnd.setSelectedIndex(1);

        navPanel.add(createCompactFieldPanel("Start:", cmbLocationStart));
        navPanel.add(Box.createVerticalStrut(8));
        navPanel.add(createCompactFieldPanel("End:", cmbLocationEnd));
        navPanel.add(Box.createVerticalStrut(8));

        btnPath = createCompactButton("🗺️ Find Path", WARNING_COLOR);
        btnPath.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        navPanel.add(btnPath);

        formPanel.add(navPanel);
        formPanel.add(Box.createVerticalStrut(12));

        btnClear = createCompactButton("🔄 Clear", new Color(149, 165, 166));
        btnClear.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        formPanel.add(btnClear);

        panel.add(formPanel, BorderLayout.CENTER);

        btnViewDocs.addActionListener(e -> viewDoctors());
        btnAssign.addActionListener(e -> assignDoctor());
        btnPath.addActionListener(e -> findPath());
        btnClear.addActionListener(e -> clearFields());

        return panel;
    }

    private JPanel createCompactFieldPanel(String labelText, JComboBox<String> combo) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 11));
        label.setForeground(new Color(52, 73, 94));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        combo.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);
        panel.add(Box.createVerticalStrut(4));
        panel.add(combo);

        return panel;
    }

    private JComboBox<String> createCompactComboBox(String[] items) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        combo.setPreferredSize(new Dimension(300, 30));
        combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        combo.setBackground(Color.WHITE);
        return combo;
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

        JLabel titleLabel = new JLabel("🏥 Results");
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

    private void viewDoctors() {
        txtOutput.setText("📋 Loading...");

        String result = CppBridge.processRequest(
                Config.ROUTING_EXE,
                Config.ROUTING_INPUT,
                Config.ROUTING_OUTPUT,
                "VIEW_DOCTORS"
        );

        txtOutput.setText(result);
    }

    private void assignDoctor() {
        String specialty = cmbSpecialty.getSelectedItem().toString();
        txtOutput.setText("👨‍⚕️ Assigning...");

        String result = CppBridge.processRequest(
                Config.ROUTING_EXE,
                Config.ROUTING_INPUT,
                Config.ROUTING_OUTPUT,
                "ASSIGN", specialty
        );

        txtOutput.setText(result);
    }

    private void findPath() {
        String start = cmbLocationStart.getSelectedItem().toString();
        String end = cmbLocationEnd.getSelectedItem().toString();

        if (start.equals(end)) {
            txtOutput.setText("⚠️ Already at destination!");
            return;
        }

        txtOutput.setText("🗺️ Finding path...");

        String result = CppBridge.processRequest(
                Config.ROUTING_EXE,
                Config.ROUTING_INPUT,
                Config.ROUTING_OUTPUT,
                "FIND_PATH", start, end
        );

        txtOutput.setText(result);
    }

    private void clearFields() {
        cmbSpecialty.setSelectedIndex(0);
        cmbLocationStart.setSelectedIndex(0);
        cmbLocationEnd.setSelectedIndex(1);
        txtOutput.setText("");
    }
}