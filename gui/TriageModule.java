package gui;

import integration.CppBridge;
import Config.Config;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class TriageModule extends JPanel {

    private JTextField txtId, txtSymptoms;
    private JComboBox<String> cmbPriority;
    private JTextArea txtOutput;
    private JButton btnAdd, btnTreat, btnView, btnClear;

    private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private final Color CRITICAL_COLOR = new Color(231, 76, 60);
    private final Color URGENT_COLOR = new Color(230, 126, 34);
    private final Color SUCCESS_COLOR = new Color(39, 174, 96);
    private final Color LIGHT_BG = new Color(236, 240, 241);

    public TriageModule() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel contentWrapper = new JPanel(new BorderLayout(20, 0));
        contentWrapper.setBackground(Color.WHITE);
        contentWrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel leftPanel = createInputPanel();
        contentWrapper.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = createOutputPanel();
        contentWrapper.add(rightPanel, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(contentWrapper);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(480, 700));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(CRITICAL_COLOR);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        titlePanel.setMaximumSize(new Dimension(480, 120));

        JLabel titleLabel = new JLabel("Emergency Triage");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Priority-based patient queue management");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitleLabel.setForeground(new Color(236, 240, 241));
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(8));
        titlePanel.add(subtitleLabel);

        panel.add(titlePanel);
        panel.add(Box.createVerticalStrut(30));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));

        txtId = createStyledTextField();
        txtSymptoms = createStyledTextField();

        String[] priorities = {
                "1 - Critical (Life Threatening)",
                "2 - Urgent (Severe Condition)",
                "3 - Moderate (Stable)",
                "4 - Non-urgent (Minor)"
        };
        cmbPriority = new JComboBox<>(priorities);
        cmbPriority.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        cmbPriority.setPreferredSize(new Dimension(430, 42));
        cmbPriority.setMaximumSize(new Dimension(430, 42));
        cmbPriority.setBackground(Color.WHITE);

        formPanel.add(createFieldPanel("Patient ID *", txtId));
        formPanel.add(Box.createVerticalStrut(18));
        formPanel.add(createPriorityPanel());
        formPanel.add(Box.createVerticalStrut(18));
        formPanel.add(createFieldPanel("Symptoms / Condition *", txtSymptoms));

        panel.add(formPanel);
        panel.add(Box.createVerticalStrut(30));

        JPanel legendPanel = createPriorityLegend();
        panel.add(legendPanel);
        panel.add(Box.createVerticalStrut(25));

        JPanel buttonPanel = createButtonPanel();
        panel.add(buttonPanel);
        panel.add(Box.createVerticalStrut(20));

        return panel;
    }

    private JPanel createFieldPanel(String labelText, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(430, 75));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(52, 73, 94));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        textField.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);
        panel.add(Box.createVerticalStrut(6));
        panel.add(textField);

        return panel;
    }

    private JPanel createPriorityPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(430, 75));

        JLabel label = new JLabel("Priority Level *");
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(52, 73, 94));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        cmbPriority.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);
        panel.add(Box.createVerticalStrut(6));
        panel.add(cmbPriority);

        return panel;
    }

    private JPanel createPriorityLegend() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(LIGHT_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 25, 0, 25),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));
        panel.setMaximumSize(new Dimension(430, 140));

        JLabel titleLabel = new JLabel("Priority Guide:");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        titleLabel.setForeground(new Color(52, 73, 94));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(10));

        panel.add(createLegendItem("🔴 Critical", "Cardiac arrest, severe trauma", CRITICAL_COLOR));
        panel.add(createLegendItem("🟠 Urgent", "Chest pain, difficulty breathing", URGENT_COLOR));
        panel.add(createLegendItem("🟡 Moderate", "Fractures, infections", new Color(241, 196, 15)));
        panel.add(createLegendItem("🟢 Non-urgent", "Minor injuries, cold", SUCCESS_COLOR));

        return panel;
    }

    private JPanel createLegendItem(String priority, String examples, Color color) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 3));
        panel.setBackground(LIGHT_BG);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel priorityLabel = new JLabel(priority);
        priorityLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        priorityLabel.setForeground(color);

        JLabel exampleLabel = new JLabel("- " + examples);
        exampleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        exampleLabel.setForeground(new Color(127, 140, 141));

        panel.add(priorityLabel);
        panel.add(exampleLabel);

        return panel;
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setPreferredSize(new Dimension(430, 42));
        field.setMaximumSize(new Dimension(430, 42));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
                BorderFactory.createEmptyBorder(5, 12, 5, 12)
        ));
        return field;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 12, 12));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 25, 25, 25));
        panel.setMaximumSize(new Dimension(430, 110));

        btnAdd = createStyledButton("➕ Add to Queue", SUCCESS_COLOR);
        btnTreat = createStyledButton("👨‍⚕️ Treat Next", PRIMARY_COLOR);
        btnView = createStyledButton("📋 View Queue", PRIMARY_COLOR);
        btnClear = createStyledButton("🔄 Clear", new Color(149, 165, 166));

        panel.add(btnAdd);
        panel.add(btnTreat);
        panel.add(btnView);
        panel.add(btnClear);

        // YOUR EXISTING ACTIONS
        btnAdd.addActionListener(e -> addToQueue());
        btnTreat.addActionListener(e -> treatNext());
        btnView.addActionListener(e -> viewQueue());
        btnClear.addActionListener(e -> clearFields());

        return panel;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(200, 48));
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
        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(700, 700));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(LIGHT_BG);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(18, 25, 18, 25));

        JLabel titleLabel = new JLabel("🚨 Emergency Queue Status");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(52, 73, 94));

        titlePanel.add(titleLabel, BorderLayout.WEST);
        panel.add(titlePanel, BorderLayout.NORTH);

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        txtOutput.setFont(new Font("Consolas", Font.PLAIN, 14));
        txtOutput.setBackground(new Color(250, 250, 250));
        txtOutput.setForeground(new Color(44, 62, 80));
        txtOutput.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        txtOutput.setLineWrap(true);
        txtOutput.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(txtOutput);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 12, 12, 12),
                BorderFactory.createLineBorder(new Color(189, 195, 199), 2)
        ));

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // YOUR EXISTING METHODS
    private void addToQueue() {
        String id = txtId.getText().trim();
        String symptoms = txtSymptoms.getText().trim();
        String priorityStr = cmbPriority.getSelectedItem().toString().substring(0, 1);

        if (id.isEmpty() || symptoms.isEmpty()) {
            txtOutput.setForeground(CRITICAL_COLOR);
            txtOutput.setText("❌ ERROR: Patient ID and Symptoms are required!");
            return;
        }

        txtOutput.setForeground(new Color(52, 152, 219));
        txtOutput.setText("⏳ Adding patient to queue...\n\nPlease wait...");

        String result = CppBridge.processRequest(
                Config.QUEUE_EXE,
                Config.QUEUE_INPUT,
                Config.QUEUE_OUTPUT,
                "ADD",
                id,
                priorityStr,
                symptoms
        );

        txtOutput.setForeground(new Color(44, 62, 80));
        txtOutput.setText(result);
        viewQueue(); // Auto refresh
    }

    private void treatNext() {
        txtOutput.setForeground(new Color(52, 152, 219));
        txtOutput.setText("👨‍⚕️ Treating next patient...\n\nPlease wait...");

        String result = CppBridge.processRequest(
                Config.QUEUE_EXE,
                Config.QUEUE_INPUT,
                Config.QUEUE_OUTPUT,
                "TREAT_NEXT"
        );

        if (result.contains("SUCCESS")) {
            txtOutput.setForeground(SUCCESS_COLOR);
        } else {
            txtOutput.setForeground(CRITICAL_COLOR);
        }
        txtOutput.setText(result);
    }

    private void viewQueue() {
        txtOutput.setForeground(new Color(52, 152, 219));
        txtOutput.setText("📋 Loading queue...\n\nPlease wait...");

        String result = CppBridge.processRequest(
                Config.QUEUE_EXE,
                Config.QUEUE_INPUT,
                Config.QUEUE_OUTPUT,
                "VIEW_QUEUE"
        );

        txtOutput.setForeground(new Color(44, 62, 80));
        txtOutput.setText(result);
    }

    private void clearFields() {
        txtId.setText("");
        txtSymptoms.setText("");
        cmbPriority.setSelectedIndex(0);
        txtOutput.setText("");
        txtOutput.setForeground(new Color(44, 62, 80));
    }
}