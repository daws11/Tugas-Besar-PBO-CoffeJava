/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DaoInterface;

import java.sql.SQLException;
import java.util.List;
import model.Doctor;
import model.Patient;
import model.Room;
import model.Appointment;
import model.MyAppointment;

/**
 *
 * @author adibf
 */
public interface IDaoMyPractices {
    
    public List<Appointment> chooseAppointment(int doctorId) throws SQLException;
    public List<Integer> seeMyPatient(int appointmentId) throws  SQLException;
    public Patient getPatientData(int patientId) throws SQLException;
    
}
