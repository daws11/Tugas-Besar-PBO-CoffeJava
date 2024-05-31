/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Dao.MedicalCheckUpDao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.MedicalCheckUp;

/**
 *
 * @author Admin
 */
public class MedicalCheckUpController {
    private MedicalCheckUpDao medicaldao;
    public MedicalCheckUpController() {
        this.medicaldao = new MedicalCheckUpDao();
    }
    
    public void regis(String dateString, String note) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        MedicalCheckUp medicalModel = new MedicalCheckUp(date, note);
        this.medicaldao.createMedical(medicalModel);
    }
}
