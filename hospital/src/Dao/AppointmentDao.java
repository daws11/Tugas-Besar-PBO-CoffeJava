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
public class AppointmentDao implements IDaoAppointment{

    @Override
    public void addAppointment(Appointment appointment) throws SQLException {
        
    }

    @Override
    public void deleteAppointment(int appointmentId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateAppointment(Appointment appointment) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Appointment> printAppointment() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appoiments";
        
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery()){
            
            while(rs.next()){
            int chooseId = rs.getInt("AppoimentId");
            LocalTime start = rs.getTime("TimeStart").toLocalTime();
            LocalTime end = rs.getTime("TimeEnd").toLocalTime();
            int status = rs.getInt("Status");
            byte done = rs.getByte("IsCompleted");
            int capacity = rs.getInt("Capacity");
            int room = rs.getInt("RoomId");
            int docId = rs.getInt("DoctorId");
            LocalDate date = rs.getDate("Date").toLocalDate();
            
            Appointment ap = new Appointment(chooseId, start, end, status, done, capacity, room, docId, date);
            appointments.add(ap);
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("Error while retrieving all appointments: " + e.getMessage());
        }
        return appointments;
    }

    @Override
    public List<Doctor> printDoctors() throws SQLException {
         throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
        /*
        List<Doctor> doc = new ArrayList<>();
        String sql = "SELECT DoctorId, firstName, lastName FROM doctors";
        
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery()){
            
            while(rs.next()){
            int docId = rs.getInt("DoctorId");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            
            Doctor
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("Error while retrieving all doctors: " + e.getMessage());
        }
        return appointments;
    */
    }

    
}
