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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Patient;

/**
 *
 * @author adibf
 */
public class MyPracticesDao implements IDaoMyPractices{

    @Override
    public List<Map<String, Object>> listMyPractices(int doctorId) throws SQLException {
        String sql = "SELECT " +
                "a.AppoimentId, " +
                "a.Date, " +
                "a.TimeStart AS Start, " +
                "a.TimeEnd AS End, " +
                "CONCAT('Floor ', r.RoomFloor, ' Number ', r.RoomNumber) AS Room " +
                "FROM appoiments a " +
                "JOIN rooms r ON a.RoomId = r.RoomId " +
                "AND a.Date >= CURDATE() " +
                "WHERE a.DoctorId = ? AND a.IsCompleted = 0 "
                + "ORDER BY a.Date ASC;";

        List<Map<String, Object>> appointmentList = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, doctorId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> appointmentData = new HashMap<>();
                    appointmentData.put("AppointmentId", rs.getInt("AppoimentId"));
                    appointmentData.put("Date", rs.getDate("Date"));
                    appointmentData.put("Start", rs.getTime("Start"));
                    appointmentData.put("End", rs.getTime("End"));
                    appointmentData.put("Room", rs.getString("Room"));
                    appointmentList.add(appointmentData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while fetching detailed appointments: " + e.getMessage());
        }

        return appointmentList;
    }

    @Override
    public List<Map<String, Object>> seeMyPatient(int appointmentId) throws SQLException {
        List<Map<String, Object>> patientIds = new ArrayList<>();
    String sql = "SELECT "
            + "a.PatientId, "
            + "CONCAT (p.FirstName, ' ', p.LastName) AS FullName "
            + "FROM appoimentpatients a "
            + "JOIN patients p ON a.PatientId = p.PatientId "
            + "WHERE appoimentId = ?;";

        try (Connection connection = DataBaseConnection.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, appointmentId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                Map<String, Object> listPatient = new HashMap<>();
                listPatient.put("PatientId", rs.getInt("PatientId"));
                listPatient.put("PatientName", rs.getString("FullName"));
                patientIds.add(listPatient);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while retrieving patient IDs: " + e.getMessage());
        }

        return patientIds;
    }

    @Override
    public List<Map<String, Object>> getPatientData(int patientId) throws SQLException {
        String sql = "SELECT " +
                     "PatientId, FirstName, LastName, GenderName, BirthDate, Address, PhoneNumber, Email, BloodType " +
                     "FROM patients " +
                     "WHERE PatientId = ?";

        List<Map<String, Object>> patientDataList = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, patientId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Map<String, Object> patientData = new HashMap<>();
                    patientData.put("PatientId", rs.getInt("PatientId"));
                    patientData.put("FirstName", rs.getString("FirstName"));
                    patientData.put("LastName", rs.getString("LastName"));
                    patientData.put("GenderName", rs.getString("GenderName"));
                    patientData.put("BirthDate", rs.getDate("BirthDate"));
                    patientData.put("Address", rs.getString("Address"));
                    patientData.put("PhoneNumber", rs.getString("PhoneNumber"));
                    patientData.put("Email", rs.getString("Email"));
                    patientData.put("BloodType", rs.getString("BloodType"));
                    patientDataList.add(patientData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while retrieving patient details: " + e.getMessage());
        }

        return patientDataList;
    }
    
}
