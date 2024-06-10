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
import Dao.MyPracticesDao;
import View.MyPracticesFrame;
import java.util.Map;
import javax.swing.JOptionPane;
import model.Patient;

/**
 *
 * @author adibf
 */
public class MyPracticesController {
    
    MyPracticesFrame frame;
    MyPracticesDao myPracticesDao = new MyPracticesDao();
    
    public List<Map<String, Object>> getAllMyPractices(int docId) throws SQLException{
        return this.myPracticesDao.listMyPractices(docId);
    }
    
    public List<Map<String, Object>>  getAllPatients (int appointmentid) throws SQLException{
        return this.myPracticesDao.seeMyPatient(appointmentid);
    }
    
    public List<Map<String, Object>> getDetailPatient (int patientid) throws SQLException{
        return this.myPracticesDao.getPatientData(patientid);
    }
}
