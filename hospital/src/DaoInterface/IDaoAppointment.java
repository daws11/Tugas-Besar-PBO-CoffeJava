/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DaoInterface;

import java.sql.SQLException;
import model.Doctor;
import model.Room;
import model.Appointment;
import java.util.List;

/**
 *
 * @author adibf
 */
public interface IDaoAppointment {
    
    void addAppointment(Appointment appointment) throws SQLException;
    void deleteAppointment(int appointmentId) throws SQLException;
    void updateAppointment(Appointment appointment) throws SQLException;
    List<Appointment> printAppointment() throws SQLException;
    List<Doctor> printDoctors() throws SQLException;
    
}
