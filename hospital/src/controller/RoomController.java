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

    public void editRoom(String roomname, String roomfloor, String roomnumber, String oldRoom) {
        roomDao.editData(roomname, roomfloor, roomnumber, oldRoom);
    }

    public void addRoom(String roomname, String roomfloor, String roomnumber) {
        roomDao.addData(roomname, roomfloor, roomnumber);
    }
}

    

