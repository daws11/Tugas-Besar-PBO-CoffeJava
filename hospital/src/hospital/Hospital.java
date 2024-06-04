/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospital;

import Dao.AdminDao;
import View.WelcomePage;
import controller.AdminController;
import controller.DoctorController;
import java.sql.Connection;
import java.sql.DriverManager;

import database.DataBaseConnection;
import java.sql.SQLException;
import controller.PatientController;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Admin;
import model.Doctor;
import model.Patient;

import util.PasswordUtil;
import view.SpecializationGUI;


/**
 *
 * @author kevin
 */
public class Hospital {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.setLocationRelativeTo(null);
        welcomePage.setVisible(true);
        
        
        
    }
    
}
