/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import DaoInterface.IDaoAppointment;
import database.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.Appointment;
import model.Doctor;

/**
 *
 * @author adibf
 */
public class AppointmentDao implements IDaoAppointment {

    @Override
    public boolean addAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO appoiments (TimeStart, TimeEnd, Status, IsCompleted, Capacity, RoomId, DoctorId, Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setTime(1, java.sql.Time.valueOf(appointment.getStart()));
            statement.setTime(2, java.sql.Time.valueOf(appointment.getEnd()));
            statement.setInt(3, appointment.getStatus());
            statement.setInt(4, appointment.getIsCompleted());
            statement.setInt(5, appointment.getCapacity());
            statement.setInt(6, appointment.getRoomId());
            statement.setInt(7, appointment.getDoctorId());
            statement.setDate(8, java.sql.Date.valueOf(appointment.getDate()));

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while adding appointment " + e.getMessage());

        }

    }

    @Override
    public boolean deleteAppointment(int appointmentId) throws SQLException {
        String sql = "DELETE FROM appoiments WHERE AppoimentId = ?";

        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, appointmentId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while deleting appointment: " + e.getMessage());

        }
    }

    @Override
    public boolean updateAppointment(Appointment appointment) throws SQLException {
         String sql = "UPDATE appoiments SET TimeStart = ?, TimeEnd = ?, Status = ?, IsCompleted = ?, Capacity = ?, RoomId = ?, DoctorId = ?, Date = ? WHERE AppointmentId = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setTime(1, java.sql.Time.valueOf(appointment.getStart()));
            statement.setTime(2, java.sql.Time.valueOf(appointment.getEnd()));
            statement.setInt(3, appointment.getStatus());
            statement.setInt(4, appointment.getIsCompleted());
            statement.setInt(5, appointment.getCapacity());
            statement.setInt(6, appointment.getRoomId());
            statement.setInt(7, appointment.getDoctorId());
            statement.setDate(8, java.sql.Date.valueOf(appointment.getDate()));
            statement.setInt(9, appointment.getAppointmentId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while updating appointment " + e.getMessage());
        }
    }

    @Override
    public List<Appointment> printAppointment() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appoiments;";

        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                int chooseId = rs.getInt("AppoimentId");
                LocalTime start = rs.getTime("TimeStart").toLocalTime();
                LocalTime end = rs.getTime("TimeEnd").toLocalTime();
                int status = rs.getInt("Status");
                int done = rs.getByte("IsCompleted");
                int capacity = rs.getInt("Capacity");
                int room = rs.getInt("RoomId");
                int docId = rs.getInt("DoctorId");
                LocalDate date = rs.getDate("Date").toLocalDate();

                Appointment ap = new Appointment(chooseId, start, end, status, done, capacity, room, docId, date);
                appointments.add(ap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while retrieving all appointments: " + e.getMessage());
        }
        return appointments;
    }

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
}
