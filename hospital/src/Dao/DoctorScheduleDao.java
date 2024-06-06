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
import java.util.List;
import model.Appointment;
import model.Doctor;

/**
 *
 * @author adibf
 */
public class DoctorScheduleDao implements IDaoDoctorAppointment{

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
    public List<Appointment> searchDoctor(Appointment appointment) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean registerAppointment(int patientId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
