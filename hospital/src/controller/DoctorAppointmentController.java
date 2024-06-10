/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.util.List;
import model.Appointment;
import model.MyAppointment;
import Dao.AppointmentDao;
import Dao.DoctorAppointmentDao;
import View.DoctorAppointmentFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author adibf
 */
public class DoctorAppointmentController {
    
    DoctorAppointmentFrame frame;
    AppointmentDao appointmentDao = new AppointmentDao();
    DoctorAppointmentDao doctorAppointmentDao = new DoctorAppointmentDao();
    
    public DoctorAppointmentController(DoctorAppointmentFrame frame) {
        this.frame = frame;
    }
    
    public List<Appointment> getAllAppointment() throws SQLException{
        return this.appointmentDao.printAppointment();
    }
    
    public void register(int patientId) throws SQLException{
        int selectedAppointment = frame.getTableAppointment().getSelectedRow();
    if (selectedAppointment != -1){
    int haveId = (int)frame.getTableAppointment().getValueAt(selectedAppointment, 0);
    boolean checkPatient = doctorAppointmentDao.notRegistered(haveId, patientId);
    boolean checkCap = doctorAppointmentDao.isNotFull(haveId);
    if (!checkPatient){
        JOptionPane.showMessageDialog(null, "You have registered for this appointment");
        return;
    
    }
    
    if (!checkCap){
        JOptionPane.showMessageDialog(null, "Appointment full");
        return;
    }
    
    MyAppointment myAppointment = new MyAppointment(haveId, patientId);
    doctorAppointmentDao.addMyAppointment(myAppointment);
    
        JOptionPane.showMessageDialog(null, "Appointment registered successfully");
    } else {
        JOptionPane.showMessageDialog(null, "Please select appointment from appointment table");
    }
}
    
}
