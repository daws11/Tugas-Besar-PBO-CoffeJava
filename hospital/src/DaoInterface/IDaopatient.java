/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DaoInterface;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import model.Patient;

/**
 *
 * @author kevin
 */
public interface IDaopatient {
    public boolean registerPatient(Patient patient)throws SQLException, NoSuchAlgorithmException;
    public Patient loginPatient(String email, String password) throws SQLException, NoSuchAlgorithmException;
}
