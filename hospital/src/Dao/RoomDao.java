/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import database.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author LENOVO
 */
public class RoomDao {
        public boolean addRoom(String roomName, int roomFloor, int roomNumber) throws SQLException {
        String query = "INSERT INTO rooms (RoomName, RoomFloor, RoomNumber) VALUES (?, ?, ?)";
        try (Connection connection = DataBaseConnection.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, roomName);
            statement.setInt(2, roomFloor);
            statement.setInt(3, roomNumber);
            return statement.executeUpdate() > 0;
        }
    }
        
            public boolean updateRoom(String oldRoomName, String newRoomName, int newRoomFloor, int newRoomNumber) throws SQLException {
        String query = "UPDATE rooms SET RoomName = ?, RoomFloor = ?, RoomNumber = ? WHERE RoomName = ?";
        try (Connection connection = DataBaseConnection.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newRoomName);
            statement.setInt(2, newRoomFloor);
            statement.setInt(3, newRoomNumber);
            statement.setString(4, oldRoomName);
            return statement.executeUpdate() > 0;
        }
    }
        public boolean deleteRoom(String roomName) throws SQLException {
        String query = "DELETE FROM rooms WHERE RoomName = ?";
        try (Connection connection = DataBaseConnection.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, roomName);
            return statement.executeUpdate() > 0;
        }
    }
        
}
