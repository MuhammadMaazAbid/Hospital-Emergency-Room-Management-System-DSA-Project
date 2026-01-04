package gui;

import integration.CppBridge;
import Config.Config;
import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister;

    private final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private final Color SUCCESS_COLOR = new Color(39, 174, 96);

    public LoginScreen() {
        setTitle("Hospital ER System - Admin Login");
        setSize(500, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(500, 150));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel iconLabel = new JLabel("🏥");
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        iconLabel.setForeground(Color.WHITE);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("Hospital ER System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Admin Access Portal");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(236, 240, 241));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(iconLabel);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(subtitleLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        JLabel loginTitle = new JLabel("Sign In");
        loginTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        loginTitle.setForeground(new Color(52, 73, 94));
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.add(loginTitle);
        formPanel.add(Box.createVerticalStrut(30));

        // Username
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUsername.setForeground(new Color(52, 73, 94));
        lblUsername.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtUsername.setPreferredSize(new Dimension(380, 45));
        txtUsername.setMaximumSize(new Dimension(380, 45));
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        formPanel.add(lblUsername);
        formPanel.add(Box.createVerticalStrut(8));
        formPanel.add(txtUsername);
        formPanel.add(Box.createVerticalStrut(25));

        // Password
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPassword.setForeground(new Color(52, 73, 94));
        lblPassword.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtPassword.setPreferredSize(new Dimension(380, 45));
        txtPassword.setMaximumSize(new Dimension(380, 45));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        formPanel.add(lblPassword);
        formPanel.add(Box.createVerticalStrut(8));
        formPanel.add(txtPassword);
        formPanel.add(Box.createVerticalStrut(35));

        // Login Button
        btnLogin = new JButton("🔐 LOGIN");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(PRIMARY_COLOR);
        btnLogin.setPreferredSize(new Dimension(380, 50));
        btnLogin.setMaximumSize(new Dimension(380, 50));
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(PRIMARY_COLOR.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogin.setBackground(PRIMARY_COLOR);
            }
        });

        btnLogin.addActionListener(e -> handleLogin());

        formPanel.add(btnLogin);
        formPanel.add(Box.createVerticalStrut(20));

        // Divider
        JLabel divider = new JLabel("━━━━━━━━━━━━━  OR  ━━━━━━━━━━━━━");
        divider.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        divider.setForeground(new Color(149, 165, 166));
        divider.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.add(divider);
        formPanel.add(Box.createVerticalStrut(20));

        // Register Button
        btnRegister = new JButton("✨ CREATE NEW ACCOUNT");
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setBackground(SUCCESS_COLOR);
        btnRegister.setPreferredSize(new Dimension(380, 45));
        btnRegister.setMaximumSize(new Dimension(380, 45));
        btnRegister.setFocusPainted(false);
        btnRegister.setBorderPainted(false);
        btnRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegister.setBackground(SUCCESS_COLOR.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegister.setBackground(SUCCESS_COLOR);
            }
        });

        btnRegister.addActionListener(e -> handleRegister());

        formPanel.add(btnRegister);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter both username and password!",
                    "Input Required",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Call C++ backend for authentication
        String result = CppBridge.processRequest(
                Config.AUTH_EXE,
                Config.AUTH_INPUT,
                Config.AUTH_OUTPUT,
                "LOGIN",
                username,
                password
        );

        if (result.contains("SUCCESS")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Welcome, " + username + "!",
                    "Login Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );
            dispose();
            new MainGUI().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password!",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
            txtPassword.setText("");
        }
    }

    private void handleRegister() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter both username and password!",
                    "Input Required",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (username.length() < 3) {
            JOptionPane.showMessageDialog(
                    this,
                    "Username must be at least 3 characters!",
                    "Invalid Username",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (password.length() < 4) {
            JOptionPane.showMessageDialog(
                    this,
                    "Password must be at least 4 characters!",
                    "Weak Password",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Create account for username: " + username + "?",
                "Confirm Registration",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            // Call C++ backend for registration
            String result = CppBridge.processRequest(
                    Config.AUTH_EXE,
                    Config.AUTH_INPUT,
                    Config.AUTH_OUTPUT,
                    "REGISTER",
                    username,
                    password
            );

            if (result.contains("SUCCESS")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Account created successfully!\nYou can now login.",
                        "Registration Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );
                txtPassword.setText("");
            } else if (result.contains("EXISTS")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Username already exists!\nPlease choose a different username.",
                        "Registration Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Registration failed! Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}