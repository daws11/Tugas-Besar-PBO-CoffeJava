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

/**
 *
 * @author kevin
 */
public class PatientDao implements IDaopatient {
    
    @Override
    public boolean registerPatient(Patient patient)  throws SQLException  {
        String query = "INSERT INTO patients (FirstName, LastName, Email, PhoneNumber, Password, Salt, Address, BirthDate, GenderName, bloodType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
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
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
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
                }
            }
        } catch(SQLException|NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
