/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author kevin
 */
public class DataBaseConnection {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/hospitalpbo"; 
    private static final String user = "root";
    private static final String password = "root123";
    
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            
        } catch (Exception e) {
            System.err.println("Failed to connect to the database.");
        }
        return connection;
    }
}
