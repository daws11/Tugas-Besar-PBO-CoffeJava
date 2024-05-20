package Dao;

import com.sun.jdi.connect.spi.Connection;

import model.Specialization;

import database.DataBaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kevin
 */
public class SpecializationDao {
    public static Specialization getSpecializationById(String specializationId) throws SQLException {
        String query = "SELECT * FROM specializations WHERE specializationId = ?";
        
        try (java.sql.Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, specializationId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    return new Specialization(specializationId, name, description);
                }
            }
        }
        
        return null; 
    }
}
