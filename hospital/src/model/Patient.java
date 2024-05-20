/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class Patient extends User {
    private int patientId;
    private String address;
    private LocalDate birtDate;
    private String gender;
    private String bloodType;
    private ArrayList<MedicalCheckUp> medicalCheckUps = new ArrayList<MedicalCheckUp>();

    public Patient(int patientId, String address, LocalDate birtDate, String gender, String bloodType, String firstName, String lastName, String email, String phoneNumber, String passwrod, String salt) {
        super(firstName, lastName, email, phoneNumber, passwrod, salt);
        this.patientId = patientId;
        this.address = address;
        this.birtDate = birtDate;
        this.gender = gender;
        this.bloodType = bloodType;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getBirtDate() {
        return birtDate;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirtDate(LocalDate birtDate) {
        this.birtDate = birtDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
    
    
    
}
