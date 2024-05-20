package Dao;

import DaoInterface.IDaoDoctor;
import database.DataBaseConnection;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import model.Doctor;
import model.Patient;
import model.Specialization;
import util.PasswordUtil;
import Dao.SpecializationDao;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author kevin
 */
public class DoctorDao implements IDaoDoctor {

    @Override
    public Doctor loginDoctor(String email, String password) throws SQLException, NoSuchAlgorithmException {
        String query = "SELECT * FROM doctors WHERE email = ?";
        
        try (Connection connection = DataBaseConnection.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                String salt = resultSet.getString("salt");
                
                if (storedPassword.equals(PasswordUtil.hashPassword(password, salt))) {
                    int doctorId = resultSet.getInt("doctorId");
                    String address = resultSet.getString("Address");
                    LocalDate birthDate = resultSet.getDate("birthDate").toLocalDate();
                    String specializationId = resultSet.getString("specializationId");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String phoneNumber = resultSet.getString("phoneNumber");

                    Specialization specialization = SpecializationDao.getSpecializationById(specializationId);
                    System.out.println(specialization.getName());
                    System.out.println(specialization.getDescription());
                    System.out.println(specialization.getSpecializationId());
                    
                    return new Doctor(doctorId,address,birthDate,specialization,firstName,lastName,email,phoneNumber,password,salt);
                } else {
                    System.out.println("Invalid password");
                   
                }
            } else {
                System.out.println("Doctor not found with email: " + email);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            }
        
        return null; 
    }

}
