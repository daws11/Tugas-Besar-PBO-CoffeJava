/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import DaoInterface.IDaoMedicalCheckUp;
import database.DataBaseConnection;
import model.MedicalCheckUp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.ZoneId;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class MedicalCheckUpDao implements IDaoMedicalCheckUp {
    
    
    @Override
    public void createMedical(MedicalCheckUp medical) {
        String query = "INSERT INTO medicalcheckup (date, noteMedicalCheckUp) VALUES (?, ?)";
        
        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, Date.valueOf(medical.getDate()));
            statement.setString(2, medical.getNote());
            statement.execute();
        } catch (Exception e) {
            
        }
    }
    
}
