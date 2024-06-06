package Dao;

import DaoInterface.IDaoSpecialization;


import model.Specialization;

import database.DataBaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpecializationDao implements IDaoSpecialization{
   Connection connection;
    private final String insert = "INSERT INTO specializations (Name, Description) VALUES (?,?);";
    private final String update = "UPDATE specializations set Name=?, Description=?, WHERE SpecializationId=?; ";
    private final String delete = "DELETE FROM specializations where SpecializationId=?; ";
    private final String select = "SELECT * FROM specializations;";
    private final String selectbyId = "SELECT * FROM specializations WHERE SpecializationId=?";
    
    public SpecializationDao() {
        connection = (Connection) DataBaseConnection.getConnection();
    }

    
    public void insert(Specialization specialization) {
        
        try {
            PreparedStatement statement = connection.prepareStatement(insert);
            statement.setString(1, specialization.getName());
            statement.setString(2, specialization.getDescription());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

   
    public void update(Specialization specialization) {
       try {
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setString(1, specialization.getName());
            statement.setString(2, specialization.getDescription());
            statement.setInt(3, specialization.getSpecializationId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

   
    public void delete(int specializationId) {
       try {
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setInt(1, specializationId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
    public List<Specialization> getSpecializations() {
      List<Specialization> list_Specializations = null;
        try {
            list_Specializations = new ArrayList<Specialization>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()) {                
                Specialization specialization = new Specialization();
                specialization.setSpecializationId(resultSet.getInt("SpecializationId"));
                specialization.setName(resultSet.getString("Name"));
                specialization.setDescription(resultSet.getString("Description"));
                list_Specializations.add(specialization);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list_Specializations;
    }
    
    public Specialization getSpecializationbyId(int specializationId) {
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(selectbyId);
        preparedStatement.setInt(1, specializationId);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            int SpecializationId = resultSet.getInt("SpecializationId");
            String name = resultSet.getString("Name");
            String description = resultSet.getString("Description");
            Specialization specialization = new Specialization();
            specialization.setSpecializationId(SpecializationId);
            specialization.setName(name);
            specialization.setDescription(description);
            
            
            preparedStatement.close();
            resultSet.close();
            
            return specialization;
        } else {
         
            preparedStatement.close();
            resultSet.close();
            
            return null; 
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return null; 
    }
}

    
}
