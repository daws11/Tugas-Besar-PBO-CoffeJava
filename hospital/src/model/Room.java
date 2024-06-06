/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kevin
 */
public class Room {
    private int RoomId;
    private String RoomName;
    private int RoomFloor;
    private int RoomNumber;

    public int getRoomId() {
        return RoomId;
    }

    public void setRoomId(int RoomId) {
        this.RoomId = RoomId;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String RoomName) {
        this.RoomName = RoomName;
    }

    public int getRoomFloor() {
        return RoomFloor;
    }

    public void setRoomFloor(int RoomFloor) {
        this.RoomFloor = RoomFloor;
    }

    public int getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(int RoomNumber) {
        this.RoomNumber = RoomNumber;
    }

    public Room(int RoomId, String RoomName, int RoomFloor, int RoomNumber) {
        this.RoomId = RoomId;
        this.RoomName = RoomName;
        this.RoomFloor = RoomFloor;
        this.RoomNumber = RoomNumber;
    }
}
