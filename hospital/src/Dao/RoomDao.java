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
import DaoInterface.IDaoRoom;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class RoomDao implements IDaoRoom{

    public void editData(String roomname, String roomfloor, String roomnumber, String oldRoom) {
           try (Connection conn = DataBaseConnection.getConnection()) {
               String sql = "UPDATE rooms SET RoomName = ?, RoomFloor = ?, RoomNumber = ? WHERE RoomName = ?";
               PreparedStatement stmt = conn.prepareStatement(sql);
               stmt.setString(1, roomname);
               stmt.setString(2, roomfloor);
               stmt.setString(3, roomnumber);
               stmt.setString(4, oldRoom);
               int rowsUpdated = stmt.executeUpdate();
               if (rowsUpdated > 0) {
                   JOptionPane.showMessageDialog(null, "Room details updated successfully!");
                   stmt.close();
               } else {
                   JOptionPane.showMessageDialog(null, "Room not found. No updates made.");
               }
           } catch (SQLException e) {
               JOptionPane.showMessageDialog(null, "Error updating room details: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
           }
       }

       public void addData(String roomname, String roomfloor, String roomnumber) {
           try (Connection conn = DataBaseConnection.getConnection()) {
               String sql = "INSERT INTO rooms (RoomName, RoomFloor, RoomNumber) VALUES (?, ?, ?)";
               PreparedStatement stmt = conn.prepareStatement(sql);
               stmt.setString(1, roomname);
               stmt.setString(2, roomfloor);
               stmt.setString(3, roomnumber);
               int rowsInserted = stmt.executeUpdate();
               if (rowsInserted > 0) {
                   JOptionPane.showMessageDialog(null, "Room added successfully!");
               } else {
                   JOptionPane.showMessageDialog(null, "Failed to add room.");
               }
           } catch (SQLException e) {
               JOptionPane.showMessageDialog(null, "Error adding room: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
           }
       }
        
}
