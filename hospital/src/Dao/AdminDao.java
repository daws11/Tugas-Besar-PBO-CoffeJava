/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import controller.SpecializationController;
import database.DataBaseConnection;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Admin;
import model.Doctor;
import model.Specialization;
import util.PasswordUtil;

/**
 *
 * @author kevin
 */
public class AdminDao {

    public Admin loginAdmin(String email, String password) throws SQLException, NoSuchAlgorithmException {
        String query = "SELECT * FROM admins WHERE email = ?";
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                String salt = resultSet.getString("salt");
                if (storedPassword.equals(PasswordUtil.hashPassword(password, salt))) {
                    int adminId = resultSet.getInt("AdminId");
                    String firstName = resultSet.getString("FirstName");
                    String LastName = resultSet.getString("LastName");

                    String phoneNumber = resultSet.getString("PhoneNumber");

                    return new Admin(adminId, firstName, LastName, email, phoneNumber, password, salt);
                } else {
                    JOptionPane.showMessageDialog(null, "wrong password");
                    
                }
            } else {
                JOptionPane.showMessageDialog(null, "email not found");
            }
            
            connection.close();
        } 
        return null;
    }
    
    public boolean addAdmin(Admin admin) throws SQLException{
         String query = "INSERT INTO admins (FirstName, LastName, Email, PhoneNumber, Password, Salt) VALUES (?, ?, ?, ?, ?, ?)"; 
         try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, admin.getPhoneNumber());
            statement.setString(5, admin.getPassword());
            statement.setString(6, admin.getSalt());
            
            

            return statement.executeUpdate() > 0;
        }  
        
    }
    
    public boolean editAdmin(int adminId, String firstName, String lastName, String email, String phoneNumber) throws SQLException {
        String query = "UPDATE admins SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ? WHERE AdminId = ?";
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, phoneNumber);
            
            statement.setInt(5, adminId);
            return statement.executeUpdate() > 0;
        }
    }

    // Delete an admin
    public boolean deleteAdmin(int adminId) throws SQLException {
        String query = "DELETE FROM admins WHERE AdminId = ?";
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, adminId);
            return statement.executeUpdate() > 0;
        }
    }
    
    public boolean addDoctor(Doctor doctor) throws SQLException {
        String query = "INSERT INTO doctors (FirstName, LastName, SpecializationId, Address, BirthDate,PhoneNumber,Email,Password,Salt) VALUES (?, ?, ?, ?, ?, ?,?,?,?)"; 
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, doctor.getFirstName());
            statement.setString(2, doctor.getLastName());
            statement.setInt(3, doctor.getSpecialization().getSpecializationId());
            statement.setString(4, doctor.getAddress());
            statement.setDate(5, java.sql.Date.valueOf(doctor.getBirtDate()));
            statement.setString(6, doctor.getPhoneNumber());
            statement.setString(7, doctor.getEmail());
            statement.setString(8, doctor.getPassword());
            statement.setString(9, doctor.getSalt());
             return statement.executeUpdate() > 0;
        }
    }
    
    
    //delete doctors
    public boolean deleteDoctor(int doctorId) throws SQLException {
    String query = "DELETE FROM doctors WHERE DoctorId = ?";
    try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, doctorId);
        return statement.executeUpdate() > 0;
    }
}

    //edit doctor
    
    public boolean updateDoctor(Doctor doctor) throws SQLException {
    String query = "UPDATE doctors SET FirstName = ?, LastName = ?, Address = ?, BirthDate = ?, PhoneNumber = ?, Email = ?,SpecializationId = ? WHERE DoctorId = ?";
    try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, doctor.getFirstName());
        statement.setString(2, doctor.getLastName());
        statement.setString(3, doctor.getAddress());
        statement.setDate(4, java.sql.Date.valueOf(doctor.getBirtDate()));
        statement.setString(5, doctor.getPhoneNumber());
        statement.setString(6, doctor.getEmail());
        statement.setInt(7, doctor.getSpecialization().getSpecializationId());
        statement.setInt(8, doctor.getDoctorId());
        return statement.executeUpdate() > 0;
    }
}
    //getalldoctor
    public List<Doctor> getAllDoctors() throws SQLException {
     SpecializationController specializationController = new SpecializationController();
    List<Doctor> doctors = new ArrayList<>();
    String query = "SELECT * FROM doctors";
    try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
            int doctorId = resultSet.getInt("DoctorId");
            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");
            int specializationId = resultSet.getInt("SpecializationId");
            String address = resultSet.getString("Address");
            LocalDate birthDate = resultSet.getDate("BirthDate").toLocalDate();
            String phoneNumber = resultSet.getString("PhoneNumber");
            String email = resultSet.getString("Email");
            String password = resultSet.getString("Password");
            String salt = resultSet.getString("Salt");

            Specialization specialization = specializationController.getSpecializationById(specializationId);

            Doctor doctor = new Doctor(doctorId, address, birthDate, specialization, firstName, lastName, email, phoneNumber, password, salt);
            doctors.add(doctor);
        }
    }
    return doctors;
}

    
    //getdoctorbyid
    public Doctor getDoctorById(int doctorId) throws SQLException {
        SpecializationController specializationController = new SpecializationController();
    String query = "SELECT * FROM doctors WHERE DoctorId = ?";
    try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, doctorId);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                int specializationId = resultSet.getInt("SpecializationId");
                String address = resultSet.getString("Address");
                LocalDate birthDate = resultSet.getDate("BirthDate").toLocalDate();
                String phoneNumber = resultSet.getString("PhoneNumber");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                String salt = resultSet.getString("Salt");

                Specialization specialization = specializationController.getSpecializationById(specializationId);

                return new Doctor(doctorId, address, birthDate, specialization, firstName, lastName, email, phoneNumber, password, salt);
            }
        }
    }
    return null;
}
    

}
