/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import View.LoginPatientView;
import model.Patient;
import controller.PatientController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Doctor;
import model.User;

public class HomeView extends JFrame {
    private JLabel welcomeLabel;
    private JButton logoutButton;
    private User user;

    public HomeView(User user) {
        if(this.user instanceof Patient){
            this.user = (Patient)user;
        }
        if(this.user instanceof Doctor){
            this.user = (Doctor)user;
        }
        

        setTitle("Home");
        setSize(1920, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        welcomeLabel = new JLabel("Welcome, " + user.getEmail());
        welcomeLabel.setBounds(10, 20, 200, 25);
        panel.add(welcomeLabel);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(10, 50, 80, 25);
        panel.add(logoutButton);

        add(panel);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPatientView(new PatientController()).setVisible(true);
                dispose();
            }
        });
    }
}

