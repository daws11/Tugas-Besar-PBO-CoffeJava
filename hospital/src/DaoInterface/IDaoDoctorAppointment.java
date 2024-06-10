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
import model.MyAppointment;
import java.util.List;
import java.util.Map;

/**
 *
 * @author adibf
 */
public interface IDaoDoctorAppointment {
    
    public Appointment getAppointmentById(int appointmentId) throws SQLException;
    public boolean addMyAppointment(MyAppointment myappointment) throws SQLException;
    public List<Map<String, Object>> getDetailedAppointments() throws SQLException;
    public boolean notRegistered(int appointmentId, int patientId) throws SQLException;
    public boolean isNotFull(int appointmentId) throws SQLException;
    
}
