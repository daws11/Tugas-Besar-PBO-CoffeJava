/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DaoInterface;

import java.sql.SQLException;
import model.Doctor;
import model.Room;
import model.Patient;
import model.Appointment;
import java.util.List;

/**
 *
 * @author adibf
 */
public interface IDaoDoctorAppointment {
    
    public Appointment get(int appointmentId) throws SQLException;
    List<Appointment> searchDoctor(Appointment appointment) throws SQLException;
    List<Appointment> filterSpecialization(Appointment appointment) throws SQLException;
    public boolean registerAppointment(int patientId) throws SQLException;
    
}
