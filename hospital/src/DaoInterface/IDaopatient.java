/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DaoInterface;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import model.Patient;

/**
 *
 * @author kevin
 */
public interface IDaopatient {
    public boolean registerPatient(Patient patient)throws SQLException, NoSuchAlgorithmException;
    public Patient loginPatient(String email, String password) throws SQLException, NoSuchAlgorithmException;
    public boolean deletePatient(int patientId)throws SQLException;
    public boolean updatePatient(Patient patient) throws SQLException;
    public Patient getPatientById(int patientId) throws SQLException;
    public List<Patient> getAllPatients() throws SQLException;
}
