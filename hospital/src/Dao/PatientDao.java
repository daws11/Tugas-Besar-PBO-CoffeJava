/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import model.Patient;
import model.User;
import util.PasswordUtil;
import database.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.NoSuchAlgorithmException;
import DaoInterface.IDaopatient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class PatientDao implements IDaopatient {
    
    @Override
    public boolean registerPatient(Patient patient)  throws SQLException  {
        String query = "INSERT INTO patients (FirstName, LastName, Email, PhoneNumber, Password, Salt, Address, BirthDate, GenderName, bloodType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        boolean failled = false;
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setString(3, patient.getEmail());
            statement.setString(4, patient.getPhoneNumber());
            statement.setString(5, patient.getPassword());
            statement.setString(6, patient.getSalt());
            statement.setString(7, patient.getAddress());
            statement.setDate(8, java.sql.Date.valueOf(patient.getBirtDate()));
            statement.setString(9, patient.getGender());
            statement.setString(10, patient.getBloodType());
            

            return statement.executeUpdate() > 0;
        } 
    }

    public Patient loginPatient(String email, String password) throws SQLException, NoSuchAlgorithmException {
        String query = "SELECT * FROM patients WHERE email = ?";
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("Password");
                String salt = resultSet.getString("Salt");

                if (storedPassword.equals(PasswordUtil.hashPassword(password, salt))) {
                    return new Patient(
                            resultSet.getInt("PatientId"),
                            resultSet.getString("Address"),
                            resultSet.getDate("BirthDate").toLocalDate(),
                            resultSet.getString("GenderName"),
                            resultSet.getString("bloodType"),
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName"),
                            email,
                            resultSet.getString("PhoneNumber"),
                            storedPassword,
                            salt
                    );
                } else {
                     JOptionPane.showMessageDialog(null, "wrong password");
                }
            }else{
                 JOptionPane.showMessageDialog(null, "email not found");
            }
            connection.close();
        } 
        return null;
    }
    public boolean deletePatient(int patientId) throws SQLException {
        String query = "DELETE FROM patients WHERE PatientId = ?";

        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, patientId);
            return statement.executeUpdate() > 0;
        }
    }

    public boolean updatePatient(Patient patient) throws SQLException {
        String query = "UPDATE patients SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ?, Address = ?, BirthDate = ?, GenderName = ?, bloodType = ? WHERE PatientId = ?";

        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setString(3, patient.getEmail());
            statement.setString(4, patient.getPhoneNumber());
            
            statement.setString(5, patient.getAddress());
            statement.setDate(6, java.sql.Date.valueOf(patient.getBirtDate()));
            statement.setString(7, patient.getGender());
            statement.setString(8, patient.getBloodType());
            statement.setInt(9, patient.getPatientId());

            return statement.executeUpdate() > 0;
        }
    }

    public Patient getPatientById(int patientId) throws SQLException {
        String query = "SELECT * FROM patients WHERE PatientId = ?";

        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, patientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String email = resultSet.getString("Email");
                    String phoneNumber = resultSet.getString("PhoneNumber");
                    String password = resultSet.getString("Password");
                    String salt = resultSet.getString("Salt");
                    String address = resultSet.getString("Address");
                    LocalDate birthDate = resultSet.getDate("BirthDate").toLocalDate();
                    String gender = resultSet.getString("GenderName");
                    String bloodType = resultSet.getString("bloodType");

                    return new Patient(patientId,address,birthDate,gender,bloodType,firstName,lastName,email,phoneNumber,password,salt);
                }
            }
        }

        return null;
    }

    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients";

        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int patientId = resultSet.getInt("PatientId");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String password = resultSet.getString("Password");
                String salt = resultSet.getString("Salt");
                String address = resultSet.getString("Address");
                LocalDate birthDate = resultSet.getDate("BirthDate").toLocalDate();
                String gender = resultSet.getString("GenderName");
                String bloodType = resultSet.getString("bloodType");

                Patient patient = new Patient(patientId,address,birthDate,gender,bloodType,firstName,lastName,email,phoneNumber,password,salt);
                patients.add(patient);
            }
        }

        return patients;
    }
}

