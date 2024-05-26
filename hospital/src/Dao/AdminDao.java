/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import database.DataBaseConnection;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Admin;
import util.PasswordUtil;

/**
 *
 * @author kevin
 */
public class AdminDao {

    public Admin loginAdmin(String email, String password) throws SQLException, NoSuchAlgorithmException {
        String query = "SELECT * FROM admins WHERE email = ?";
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                String salt = resultSet.getString("salt");
                if (storedPassword.equals(PasswordUtil.hashPassword(password, salt))) {
                    int adminId = resultSet.getInt("AdminId");
                    String firstName = resultSet.getString("FirstName");
                    String LastName = resultSet.getString("LastName");

                    String phoneNumber = resultSet.getString("PhoneNumber");

                    return new Admin(adminId, firstName, LastName, email, phoneNumber, password, salt);
                } else {
                    System.out.println("wrong password");
                }
            } else {
                System.out.println("email not found");
            }
        }catch(SQLException|NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public boolean addAdmin(Admin admin){
         String query = "INSERT INTO patients (FirstName, LastName, Email, PhoneNumber, Password, Salt) VALUES (?, ?, ?, ?, ?, ?)"; 
         try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, admin.getPhoneNumber());
            statement.setString(5, admin.getPassword());
            statement.setString(6, admin.getSalt());
            
            

            return statement.executeUpdate() > 0;
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    //add doctor
    //delete doctors
    //edit doctor
    

}
