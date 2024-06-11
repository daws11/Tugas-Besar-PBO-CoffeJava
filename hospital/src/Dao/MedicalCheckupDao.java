/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import java.sql.*;
import database.DataBaseConnection;
/**
 *
 * @author Admin
 */
public class MedicalCheckupDao {
     public void createMcu(String date, String note, String result, int doctor_id, int patient_id, int appoimentId) {
         System.out.println("anjay" + appoimentId);
        String query = "INSERT INTO medicalcheckups (date, NoteMedicalChekup, Result, DoctorId, PatientId, AppoimentId) VALUES (?, ?, ?, ?, ?,?)";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, date);
            statement.setString(2, note);
            statement.setString(3, result);
            statement.setInt(4, doctor_id);
            statement.setInt(5, patient_id);
            statement.setInt(6, appoimentId);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
