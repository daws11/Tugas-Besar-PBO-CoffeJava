/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import DaoInterface.IDaoDoctorAppointment;
import database.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Appointment;
import model.MyAppointment;

/**
 *
 * @author adibf
 */
public class DoctorAppointmentDao implements IDaoDoctorAppointment{

    @Override
    public Appointment getAppointmentById(int appointmentId) throws SQLException {
        String sql = "SELECT * FROM appoiments WHERE AppoimentId = ?;";
        
        Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareCall(sql);
        
        statement.setInt(1, appointmentId);
        ResultSet rs = statement.executeQuery();
        
        if (rs.next()){
            int chooseId = rs.getInt("AppoimentId");
            LocalTime start = rs.getTime("TimeStart").toLocalTime();
            LocalTime end = rs.getTime("TimeEnd").toLocalTime();
            int status = rs.getInt("Status");
            byte done = rs.getByte("IsCompleted");
            int capacity = rs.getInt("Capacity");
            int room = rs.getInt("RoomId");
            int docId = rs.getInt("DoctorId");
            LocalDate date = rs.getDate("Date").toLocalDate();
            
            return new Appointment(chooseId, start, end, status, done, capacity, room, docId, date);
        }
        return null;
    }

    @Override
    public boolean addMyAppointment(MyAppointment myappointment) throws SQLException {
        String sql = "INSERT INTO appoimentpatients (AppoimentId, PatientId) VALUES (?, ?)";
        
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, myappointment.getAppointmentId());
            statement.setInt(2, myappointment.getPatientId());
            
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while adding my appointment " + e.getMessage());

        }
    }

    @Override
    public List<Map<String, Object>> getDetailedAppointments() throws SQLException {
        String sql = "SELECT " +
                     "a.AppoimentId, " +
                     "a.Date, " +
                     "a.TimeStart AS Start, " +
                     "a.TimeEnd AS End, " +
                     "a.Status," +
                     "d.DoctorId, " +
                     "CONCAT(d.firstName, ' ', d.lastName) AS DoctorName, " +
                     "s.Name AS Specialization, " +
                     "CONCAT('Floor ', r.RoomFloor, ' Number ', r.RoomNumber) AS Room " +
                     "FROM appoiments a " +
                     "JOIN doctors d ON a.DoctorId = d.DoctorId " +
                     "JOIN specializations s ON d.SpecializationId = s.SpecializationId " +
                     "JOIN rooms r ON a.RoomId = r.RoomId " +
                     "WHERE a.Date >= CURDATE() " +
                     "ORDER BY a.Date ASC;";

        List<Map<String, Object>> appointmentList = new ArrayList<>();
        
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> appointmentData = new HashMap<>();
                appointmentData.put("AppointmentId", rs.getInt("AppoimentId"));
                appointmentData.put("Date", rs.getDate("Date"));
                appointmentData.put("Start", rs.getTime("Start"));
                appointmentData.put("End", rs.getTime("End"));
                appointmentData.put("Status", rs.getInt("Status"));
                appointmentData.put("DoctorId", rs.getInt("DoctorId"));
                appointmentData.put("DoctorName", rs.getString("DoctorName"));
                appointmentData.put("Specialization", rs.getString("Specialization"));
                appointmentData.put("Room", rs.getString("Room"));
                appointmentList.add(appointmentData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while fetching detailed appointments: " + e.getMessage());
        }
        
        return appointmentList;
    }

    @Override
    public boolean notRegistered(int appointmentId, int patientId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM appoimentpatients WHERE AppoimentId = ? AND PatientId = ?";
        try (Connection connection = DataBaseConnection.getConnection();
                PreparedStatement Statement = connection.prepareStatement(sql)) {
            Statement.setInt(1, appointmentId);
            Statement.setInt(2, patientId);

            try (ResultSet rs = Statement.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count == 0;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error checking registration status", e);
        }
        return true;
    }

    @Override
    public boolean isNotFull(int appointmentId) throws SQLException {
        String getCapacitySql = "SELECT Capacity FROM appoiments WHERE AppoimentId = ?";
        String getPatientNumber = "SELECT COUNT(*) FROM appoimentpatients WHERE AppoimentId = ?";
        
        try (Connection connection = DataBaseConnection.getConnection()) {
            int registeredPatientsCount;
            try (PreparedStatement countPatientsStmt = connection.prepareStatement(getPatientNumber)) {
                countPatientsStmt.setInt(1, appointmentId);
                try (ResultSet rs = countPatientsStmt.executeQuery()) {
                    if (rs.next()) {
                        registeredPatientsCount = rs.getInt(1);
                    } else {
                        throw new SQLException("Error fetching registered patients count");
                    }
                }
            }

            
            int capacity;
            try (PreparedStatement getCapacityStmt = connection.prepareStatement(getCapacitySql)) {
                getCapacityStmt.setInt(1, appointmentId);
                try (ResultSet rs = getCapacityStmt.executeQuery()) {
                    if (rs.next()) {
                        capacity = rs.getInt("Capacity");
                    } else {
                        throw new SQLException("Error fetching appointment capacity");
                    }
                }
            }

            
            return registeredPatientsCount < capacity;
        } catch (SQLException e) {
            throw new SQLException("Error checking appointment capacity", e);
        }
    }
    
}
