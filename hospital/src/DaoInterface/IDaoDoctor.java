/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DaoInterface;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import model.Doctor;
import model.Patient;

/**
 *
 * @author kevin
 */
public interface IDaoDoctor {
    
    public Doctor loginDoctor(String email, String password) throws SQLException, NoSuchAlgorithmException;
}
