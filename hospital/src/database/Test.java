/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.Connection;
/**
 *
 * @author LENOVO
 */
public class Test {
    public static void main(String[] args) {
        Connection connection = DataBaseConnection.getConnection();
        if (connection != null) {
            System.out.println("Connection established successfully.");
        } else {
            System.out.println("Failed to establish connection.");
        }
    }
}
