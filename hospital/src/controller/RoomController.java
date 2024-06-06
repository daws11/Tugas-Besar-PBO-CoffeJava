/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import Dao.RoomDao;
import java.sql.SQLException;
/**
 *
 * @author LENOVO
 */
public class RoomController {
        private RoomDao roomDao;

    public RoomController() {
        this.roomDao = new RoomDao();
    }

    public boolean addRoom(String roomName, int roomFloor, int roomNumber) throws SQLException {
        return roomDao.addRoom(roomName, roomFloor, roomNumber);
    }

    public boolean updateRoom(String roomName, String newRoomName, int newRoomFloor, int newRoomNumber) throws SQLException {
        return roomDao.updateRoom(roomName, newRoomName, newRoomFloor, newRoomNumber);
    }

    public boolean deleteRoom(String roomName) throws SQLException {
        return roomDao.deleteRoom(roomName);
    }
}
