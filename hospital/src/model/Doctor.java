/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author kevin
 */
public class Doctor extends User {
    public int doctorId;
    public String Address;
    private LocalDate birtDate;
    private Specialization specialization;

    public Doctor(int doctorId, String Address, LocalDate birtDate, Specialization specialization, String firstName, String lastName, String email, String phoneNumber, String passwrod, String salt) {
        super(firstName, lastName, email, phoneNumber, passwrod, salt);
        this.doctorId = doctorId;
        this.Address = Address;
        this.birtDate = birtDate;
        this.specialization = specialization;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getAddress() {
        return Address;
    }

    public LocalDate getBirtDate() {
        return birtDate;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setBirtDate(LocalDate birtDate) {
        this.birtDate = birtDate;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
    
    
}
