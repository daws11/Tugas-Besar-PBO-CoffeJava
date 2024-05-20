package view;

import controller.DoctorController;
import model.Doctor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LoginDoctorView extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;
    private DoctorController doctorController;

    public LoginDoctorView() {
        this.doctorController = new DoctorController();

        setTitle("Doctor Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 50, 100, 25);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 50, 150, 25);
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 25);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 80, 25);
        panel.add(loginButton);

        statusLabel = new JLabel();
        statusLabel.setBounds(50, 200, 300, 25);
        panel.add(statusLabel);

        add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Doctor doctor = doctorController.login(email, password);
            if (doctor != null) {
                statusLabel.setText("Login successful for: " + doctor.getFirstName() + " " + doctor.getLastName());
                // You can navigate to the doctor's home page or dashboard here
                new HomeView(doctor).setVisible(true);
                dispose();
            } else {
                statusLabel.setText("Login failed. Incorrect email or password.");
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            statusLabel.setText("An error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginDoctorView().setVisible(true);
            }
        });
    }
}
