/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalTime;
import java.time.LocalDate;

/**
 *
 * @author adibf
 */
public class Appointment {
    private int appointmentId;
    private LocalTime start;
    private LocalTime end;
    private int status;
    private int isCompleted;
    private int capacity;
    private int roomId;
    private int doctorId;
    private LocalDate date;

    public Appointment(int appointmentId, LocalTime start, LocalTime end, int status, int isCompleted, int capacity, int roomId, int doctorId, LocalDate date) {
        this.appointmentId = appointmentId;
        this.start = start;
        this.end = end;
        this.status = status;
        this.isCompleted = isCompleted;
        this.capacity = capacity;
        this.roomId = roomId;
        this.doctorId = doctorId;
        this.date = date;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(int isCompleted) {
        this.isCompleted = isCompleted;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    
    
}