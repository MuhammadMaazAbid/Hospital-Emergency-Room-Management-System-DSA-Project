package gui;

import Config.Config;
import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {

    public MainGUI() {
        setTitle("Hospital Emergency Room Management System");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Start maximized

        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Main container
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255));

        // Header Panel
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Create tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.setForeground(new Color(41, 128, 185));

        // Add module tabs with icons
        tabbedPane.addTab("  👤 Patient Records  ", new PatientModule());
        tabbedPane.addTab("  🚨 Emergency Triage  ", new TriageModule());
        tabbedPane.addTab("  🏥 Doctor Routing  ", new RoutingModule());

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = createFooterPanel();
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel();
        header.setLayout(new BorderLayout());
        header.setBackground(new Color(41, 128, 185));
        header.setPreferredSize(new Dimension(1400, 90));
        header.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        // Left side - Title
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(new Color(41, 128, 185));

        JLabel title = new JLabel("🏥 Hospital ER Management System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Advanced Patient Care & Resource Management");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitle.setForeground(new Color(236, 240, 241));

        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(8));
        titlePanel.add(subtitle);

        header.add(titlePanel, BorderLayout.WEST);

        // Right side - Admin info
        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.Y_AXIS));
        adminPanel.setBackground(new Color(41, 128, 185));

        JLabel adminLabel = new JLabel("👤 Admin Panel");
        adminLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        adminLabel.setForeground(Color.WHITE);
        adminLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setBackground(new Color(231, 76, 60));
        btnLogout.setBorderPainted(false);
        btnLogout.setFocusPainted(false);
        btnLogout.setPreferredSize(new Dimension(100, 30));
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogout.setAlignmentX(Component.RIGHT_ALIGNMENT);

        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new LoginScreen().setVisible(true);
            }
        });

        adminPanel.add(adminLabel);
        adminPanel.add(Box.createVerticalStrut(5));
        adminPanel.add(btnLogout);

        header.add(adminPanel, BorderLayout.EAST);

        return header;
    }

    private JPanel createFooterPanel() {
        JPanel footer = new JPanel();
        footer.setBackground(new Color(52, 73, 94));
        footer.setPreferredSize(new Dimension(1400, 45));

        JLabel footerText = new JLabel("© 2025 Hospital ER System | DSA Project | All Rights Reserved");
        footerText.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        footerText.setForeground(Color.WHITE);

        footer.add(footerText);

        return footer;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Show login first
            new LoginScreen().setVisible(true);
        });
    }
}