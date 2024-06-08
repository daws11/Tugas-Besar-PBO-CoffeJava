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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Doctor;
import model.Room;
import model.Appointment;
import model.MyAppointment;
import model.Patient;

/**
 *
 * @author adibf
 */
public class MyAppointmentDao implements IDaoMyAppointment {

    @Override
    public List<Map<String, Object>> seeMyAppointment(int patientId) throws SQLException {
        String sql = "SELECT " +
                 "a.AppoimentId, " +
                 "a.Date, " +
                 "a.TimeStart AS Start, " +
                 "a.TimeEnd AS End, " +
                 "d.DoctorId, " +
                 "CONCAT(d.firstName, ' ', d.lastName) AS DoctorName, " +
                 "s.Name AS Specialization, " +
                 "CONCAT('Floor ', r.RoomFloor, ' Number ', r.RoomNumber) AS Room " +
                 "FROM appoiments a " +
                 "JOIN doctors d ON a.DoctorId = d.DoctorId " +
                 "JOIN specializations s ON d.SpecializationId = s.SpecializationId " +
                 "JOIN rooms r ON a.RoomId = r.RoomId " +
                 "JOIN appoimentpatients ap ON a.AppoimentId = ap.AppoimentId " +
                 "WHERE ap.PatientId = ? " +
                 "AND a.Date >= CURDATE() " +
                 "ORDER BY a.Date ASC;";

    List<Map<String, Object>> appointmentList = new ArrayList<>();

    try (Connection connection = DataBaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, patientId);
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> appointmentData = new HashMap<>();
                appointmentData.put("AppointmentId", rs.getInt("AppoimentId"));
                appointmentData.put("Date", rs.getDate("Date"));
                appointmentData.put("Start", rs.getTime("Start"));
                appointmentData.put("End", rs.getTime("End"));
                appointmentData.put("DoctorId", rs.getInt("DoctorId"));
                appointmentData.put("DoctorName", rs.getString("DoctorName"));
                appointmentData.put("Specialization", rs.getString("Specialization"));
                appointmentData.put("Room", rs.getString("Room"));
                appointmentList.add(appointmentData);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new SQLException("Error while retrieving my appointments: " + e.getMessage());
    }

    return appointmentList;
    }

}
