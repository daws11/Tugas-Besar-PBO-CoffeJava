/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Dao.AppointmentDao;
import DaoInterface.IDaoAppointment;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import model.Appointment;
import model.Doctor;
import model.Room;
import View.*;
import java.sql.SQLException;

/**
 *
 * @author adibf
 */
public class AppointmentController {

    AppointmentDao dao = new AppointmentDao();
    List<Doctor> listDoctor;
    AppointmentFrame appointmentFrame;
    List<Appointment> listAppointments;
    List<Room> listRoom;
    

    public AppointmentController(AppointmentFrame appointmentFrame) {
        this.appointmentFrame = appointmentFrame;
    }

    public void add(LocalTime start, LocalTime end, int status, int isDone, int cap, int room, int docId, LocalDate date) throws SQLException {

        if (!start.isBefore(end)) {
            JOptionPane.showMessageDialog(null, "Invalid Input");
            return;
        }
        Appointment appointment = new Appointment(0, start, end, status, isDone, cap, room, docId, date);
         try {
            boolean isAdded = dao.addAppointment(appointment);
            if (isAdded) {
                JOptionPane.showMessageDialog(null, "Appointment added successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add appointment");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while adding appointment: " + e.getMessage());
        }
    }

    public void delete() throws SQLException {
        int selectedAppointment = appointmentFrame.getTableSchedule().getSelectedRow();
        if (selectedAppointment != -1) {
            int haveId = (int) appointmentFrame.getTableSchedule().getValueAt(selectedAppointment, 0);
            dao.deleteAppointment(haveId);
            dao.deleteMyAppointment(haveId);
            JOptionPane.showMessageDialog(null, "Appointment deleted successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Please select appointment from appointment table");
        }
    }

    public List<Appointment> getAllAppointment() throws SQLException{
        return this.dao.printAppointment();
    }
    
    public Appointment getAppointment(int appointmentId) throws SQLException{
        return this.dao.getAppointmentById(appointmentId);
    }
    
    public void updateIsCompleted() {
        try {
            boolean isUpdated = dao.updateIsCompleted();
            if (isUpdated) {
                JOptionPane.showMessageDialog(null, "Appointments updated successfully");
            } else {
                JOptionPane.showMessageDialog(null, "No appointments to update");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while updating appointments: " + e.getMessage());
        }
    }
    
    public List<Room> getAllRooms() throws SQLException {
        return this.dao.printRoom();
    }
}
