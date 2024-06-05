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
import View.*;
import java.sql.SQLException;

/**
 *
 * @author adibf
 */
public class AppointmentController {

    AppointmentDao dao = new AppointmentDao();
    List<Doctor> listDoctor;
    AppointmentView frameAppointment;
    List<Appointment> listAppointments;
    

    public AppointmentController(AppointmentView frameAppointment) {
        this.frameAppointment = frameAppointment;
    }

    public void add(LocalTime start, LocalTime end, int status, int isDone, int cap, int room, int docId, LocalDate date) throws SQLException {

        if (!start.isBefore(end)) {
            JOptionPane.showMessageDialog(null, "Invalid Input");
            return;
        }
        Appointment appointment = new Appointment(0, start, end, status, isDone, cap, room, docId, date);
        dao.addAppointment(appointment);
        JOptionPane.showMessageDialog(null, "Appointment added successfully");
    }

    public void delete() throws SQLException {
        int selectedAppointment = frameAppointment.getTableSchedule().getSelectedRow();
        if (selectedAppointment != -1) {
            int haveId = (int) frameAppointment.getTableSchedule().getValueAt(selectedAppointment, 0);
            dao.deleteAppointment(haveId);
            JOptionPane.showMessageDialog(null, "Appointment deleted successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Please select appointment from appointment table");
        }
    }

    public void update(LocalTime start, LocalTime end, int status, int isDone, int cap, int room, int docId, LocalDate date, int appointmentId) throws SQLException {
            if (!start.isBefore(end)) {
            JOptionPane.showMessageDialog(null, "Invalid Input");
            return;
            }
            Appointment appointment = new Appointment(0, start, end, status, isDone, cap, room, docId, date);
            dao.updateAppointment(appointment);
            JOptionPane.showMessageDialog(null, "Appointment updated successfully");
    }

    public List<Appointment> getAllAppointment() throws SQLException{
        return this.dao.printAppointment();
    }
    
    public Appointment getAppointment(int appointmentId) throws SQLException{
        return this.dao.getAppointmentById(appointmentId);
    }
}
