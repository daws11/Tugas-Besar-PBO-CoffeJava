/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospital;

import Dao.AdminDao;
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
        DoctorController doctorController = new DoctorController();
        AdminController adminController = new AdminController();
        PatientController patientController  = new PatientController();
        try {
            LocalDate birthDate = LocalDate.parse("2020-10-10");
            boolean succ = patientController.deletePatient(22);
            System.out.println("delete "+succ);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
       
                
        
        //PatientController patientController = new PatientController();
        //DoctorController doctorController = new DoctorController();

        //RegisterPatientView registerPatientView =new RegisterPatientView(patientController);
        //LoginPatientView loginPatientView = new LoginPatientView(patientController);
        //loginPatientView.setVisible(true);
        //registerPatientView.setVisible(true);
        
        //LoginDoctorView loginDoctorView =  new LoginDoctorView();
        //loginDoctorView.setVisible(true);
        //String password = "y8YKzJJBkmmm4H3ctRkwGRnIxgI4znjVAe/C6y/7MaW+WwXu9IFhtn40LvHQmiZqp2/Hnm7cvturmxtfS/7reA==";
        //String salt = "IqBt6cBEpPV0WMG3BI5nvg==";
        //String hashPassword = PasswordUtil.hashPassword(password, salt);
        
        //System.out.println(salt);
        //System.out.println(hashPassword);
        
        //System.out.println(PasswordUtil.comparePassword(password, hashPassword, salt));
        
        
        
    }
    
}
