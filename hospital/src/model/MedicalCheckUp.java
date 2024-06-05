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
public class MedicalCheckUp {
    private int medicalCheckUpId;
    private LocalDate date;
    private String noteMedicalCheckUp;
    private Appointment appoiment;

    public MedicalCheckUp(int medicalCheckUpId, LocalDate date, String noteMedicalCheckUp, Appointment appoiment) {
        this.medicalCheckUpId = medicalCheckUpId;
        this.date = date;
        this.noteMedicalCheckUp = noteMedicalCheckUp;
        this.appoiment = appoiment;
    }

    public int getMedicalCheckUpId() {
        return medicalCheckUpId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getNoteMedicalCheckUp() {
        return noteMedicalCheckUp;
    }

    public Appointment getAppoiment() {
        return appoiment;
    }

    public void setMedicalCheckUpId(int medicalCheckUpId) {
        this.medicalCheckUpId = medicalCheckUpId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setNoteMedicalCheckUp(String noteMedicalCheckUp) {
        this.noteMedicalCheckUp = noteMedicalCheckUp;
    }

    public void setAppoiment(Appointment appoiment) {
        this.appoiment = appoiment;
    }
    
    
}
