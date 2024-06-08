/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DaoInterface;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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
    
    public List<Map<String, Object>> seeMyAppointment(int patientId) throws SQLException;
    
}
