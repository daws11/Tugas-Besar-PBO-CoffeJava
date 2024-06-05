/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DaoInterface;

import java.sql.SQLException;
import model.Doctor;
import model.Room;
import model.Appointment;
import model.MyAppointment;
import model.Patient;

/**
 *
 * @author adibf
 */
public interface IDaoMyAppointment {
    
    public MyAppointment seeMyAppointment(int patientId) throws SQLException;
    public Patient getPatientData(int patientId) throws SQLException;
    
}
