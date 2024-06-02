package Dao;

import DaoInterface.IDaoDoctor;
import database.DataBaseConnection;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import model.Doctor;
import model.Patient;
import model.Specialization;
import util.PasswordUtil;
import Dao.SpecializationDao;
import controller.SpecializationController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author kevin
 */
public class DoctorDao implements IDaoDoctor {

    @Override
    public Doctor loginDoctor(String email, String password) throws SQLException, NoSuchAlgorithmException {
        String query = "SELECT * FROM doctors WHERE email = ?";

        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                String salt = resultSet.getString("salt");

                if (storedPassword.equals(PasswordUtil.hashPassword(password, salt))) {
                    int doctorId = resultSet.getInt("doctorId");
                    String address = resultSet.getString("Address");
                    LocalDate birthDate = resultSet.getDate("birthDate").toLocalDate();
                    String specializationId = resultSet.getString("specializationId");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    int SpecializationId = resultSet.getInt("SpecializationId");
                    SpecializationController specializationController = new SpecializationController();
                    Specialization specialization = specializationController.getSpecializationById(SpecializationId);

                    return new Doctor(doctorId, address, birthDate, specialization, firstName, lastName, email, phoneNumber, password, salt);
                } else {
                    JOptionPane.showMessageDialog(null, "wrong password");
                    
                }
            } else {
                JOptionPane.showMessageDialog(null, "email not found");
            }
            connection.close();
        }
        return null;
    }

}
