/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.SQLException;
import DaoInterface.IDaoMyAppointment;
import database.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Doctor;
import model.Room;
import model.Appointment;
import model.MyAppointment;
import model.Patient;

/**
 *
 * @author adibf
 */
public class MyAppointmentDao implements IDaoMyAppointment{

    @Override
    public MyAppointment seeMyAppointment(int patientId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Patient getPatientData(int patientId) throws SQLException {
        String sql = "SELECT * FROM patients WHERE PatientId = ?";
        
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {
            
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while retrieving all appointments: " + e.getMessage());
        }
        return null;
    }
    
}
