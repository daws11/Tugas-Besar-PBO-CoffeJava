package view;

import View.LoginPatientView;
import controller.PatientController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.*;

public class RegisterPatientView extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField phoneNumberField;
    private JPasswordField passwordField;
    private JTextField addressField;
    private JTextField birthDateField;
    private JTextField genderField;
    private JTextField bloodTypeField;
    private JButton registerButton;
    private JButton backButton;
    private PatientController patientController;

    public RegisterPatientView(PatientController patientController) {
        this.patientController = patientController;

        setTitle("Register");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(10, 20, 80, 25);
        panel.add(firstNameLabel);

        firstNameField = new JTextField(20);
        firstNameField.setBounds(150, 20, 165, 25);
        panel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(10, 50, 80, 25);
        panel.add(lastNameLabel);

        lastNameField = new JTextField(20);
        lastNameField.setBounds(150, 50, 165, 25);
        panel.add(lastNameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 80, 80, 25);
        panel.add(emailLabel);

        emailField = new JTextField(20);
        emailField.setBounds(150, 80, 165, 25);
        panel.add(emailField);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(10, 110, 100, 25);
        panel.add(phoneNumberLabel);

        phoneNumberField = new JTextField(20);
        phoneNumberField.setBounds(150, 110, 165, 25);
        panel.add(phoneNumberField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 140, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(150, 140, 165, 25);
        panel.add(passwordField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(10, 170, 80, 25);
        panel.add(addressLabel);

        addressField = new JTextField(20);
        addressField.setBounds(150, 170, 165, 25);
        panel.add(addressField);

        JLabel birthDateLabel = new JLabel("Birth Date (YYYY-MM-DD):");
        birthDateLabel.setBounds(10, 200, 150, 25);
        panel.add(birthDateLabel);

        birthDateField = new JTextField(20);
        birthDateField.setBounds(150, 200, 165, 25);
        panel.add(birthDateField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(10, 230, 80, 25);
        panel.add(genderLabel);

        genderField = new JTextField(20);
        genderField.setBounds(150, 230, 165, 25);
        panel.add(genderField);

        JLabel bloodTypeLabel = new JLabel("Blood Type:");
        bloodTypeLabel.setBounds(10, 260, 80, 25);
        panel.add(bloodTypeLabel);

        bloodTypeField = new JTextField(20);
        bloodTypeField.setBounds(150, 260, 165, 25);
        panel.add(bloodTypeField);

        registerButton = new JButton("Register");
        registerButton.setBounds(10, 290, 100, 25);
        panel.add(registerButton);

        backButton = new JButton("Back");
        backButton.setBounds(150, 290, 100, 25);
        panel.add(backButton);

        add(panel);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean success = patientController.register(
                            firstNameField.getText(),
                            lastNameField.getText(),
                            emailField.getText(),
                            phoneNumberField.getText(),
                            new String(passwordField.getPassword()),
                            addressField.getText(),
                            LocalDate.parse(birthDateField.getText()),
                            genderField.getText(),
                            bloodTypeField.getText()
                    );

                    if (success) {
                        JOptionPane.showMessageDialog(null, "Registration successful!");
                        new LoginPatientView(patientController).setVisible(true);
                        dispose();
                    } 
                } catch (SQLException   ex) {
                    String errorMessage = ex.getMessage();
                    if (errorMessage.contains(emailField.getText()) ) {
                        JOptionPane.showMessageDialog(null, "Email sudah digunakan oleh pengguna lain.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                    } else if (errorMessage.contains(phoneNumberField.getText()) ) {
                        JOptionPane.showMessageDialog(null, "Nomor telepon sudah digunakan oleh pengguna lain.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage(), "Registration Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NoSuchAlgorithmException exc){
                     JOptionPane.showMessageDialog(null, "An error occurred: " + exc.getMessage(), "Registration Error", JOptionPane.ERROR_MESSAGE);
                    
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPatientView(patientController).setVisible(true);
                dispose();
            }
        });
    }
}
