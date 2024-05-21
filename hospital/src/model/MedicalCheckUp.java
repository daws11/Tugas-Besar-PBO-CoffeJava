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
    
    public MedicalCheckUp(LocalDate date, String note) {
        this.date = date;
        this.noteMedicalCheckUp = note;
    }
    
    public LocalDate getDate() {
        return this.date;
    }
    
    public String getNote() {
        return this.noteMedicalCheckUp;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public void setNote(String note) {
        this.noteMedicalCheckUp = note;
    }
    
}
