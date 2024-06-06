/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import Dao.PatientDao;
import model.Patient;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import util.PasswordUtil;
/**
 *
 * @author kevin
 */
public class PatientController {
     private PatientDao patientDao;
    public PatientController(){
        this.patientDao = new PatientDao();
    }
    
    public boolean register(String firstName, String lastName, String email, String phoneNumber, String password, String address, LocalDate birthDate, String gender, String bloodType) throws SQLException, NoSuchAlgorithmException {
        String salt = PasswordUtil.getSalt();
        String hashedPassword = PasswordUtil.hashPassword(password, salt);
        Patient patient = new Patient(0, address, birthDate, gender, bloodType, firstName, lastName, email, phoneNumber, hashedPassword, salt);
        return patientDao.registerPatient(patient);
    }
    public Patient login(String email, String password) throws SQLException, NoSuchAlgorithmException {
        return patientDao.loginPatient(email, password);
    }
     public boolean deletePatient(int patientId) throws SQLException {
        return patientDao.deletePatient(patientId);
    }

    public boolean updatePatient(int patientId, String firstName, String lastName, String email, String phoneNumber, String address, LocalDate birthDate, String gender, String bloodType) throws SQLException {
        Patient patient = new Patient(patientId,address,birthDate,gender,bloodType,firstName,lastName,email,phoneNumber,null,null);
        return patientDao.updatePatient(patient);
    }

    public Patient getPatientById(int patientId) throws SQLException {
        return patientDao.getPatientById(patientId);
    }

    public List<Patient> getAllPatients() throws SQLException {
        return patientDao.getAllPatients();
    }

    
}
