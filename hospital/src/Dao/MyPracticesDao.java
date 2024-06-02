/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import DaoInterface.IDaoMyPractices;
import database.DataBaseConnection;
import model.Appointment;
import model.MyAppointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.Patient;

/**
 *
 * @author adibf
 */
public class MyPracticesDao implements IDaoMyPractices{

    @Override
    public List<Appointment> chooseAppointment(int doctorId) throws SQLException {
        String sql = "SELECT * FROM Appointment WHERE DoctorId = ?";
        List<Appointment> appointments = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, doctorId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("AppointmentId");
                LocalTime start = rs.getTime("Start").toLocalTime();
                LocalTime end = rs.getTime("End").toLocalTime();
                int status = rs.getInt("Status");
                byte isCompleted = rs.getByte("IsCompleted");
                int capacity = rs.getInt("Capacity");
                int roomId = rs.getInt("RoomId");
                LocalDate date = rs.getDate("Date").toLocalDate();

                Appointment appointment = new Appointment(appointmentId, start, end, status, isCompleted, capacity, roomId, doctorId, date);
                appointments.add(appointment);
            }
        }
        return appointments;
    }

    @Override
    public List<Integer> seeMyPatient(int appointmentId) throws SQLException {
        List<Integer> patientIds = new ArrayList<>();
    String sql = "SELECT PatientId FROM appoimentspatients WHERE appointmentId = ?";

    try (Connection connection = DataBaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, appointmentId);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            int patientId = rs.getInt("PatientId");
            patientIds.add(patientId);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new SQLException("Error while retrieving patient IDs: " + e.getMessage());
    }

    return patientIds;
    }

    @Override
    public Patient getPatientData(int patientId) throws SQLException {
        Patient patient = null;
        String sql = "SELECT * FROM Patient WHERE patientId = ?";
        
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, patientId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("PatientId");
                String first = rs.getString("firstName");
                String last = rs.getString("lastName");
                String address = rs.getString("Address");
                String PhoneNumber = rs.getString("PhoneNumber");
                String Email = rs.getString("Email");
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while retrieving patient details: " + e.getMessage());
        }
        return patient;
    }
    
}
