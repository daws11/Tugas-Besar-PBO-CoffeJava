/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Dao.MedicalCheckupDao;

/**
 *
 * @author Admin
 */
public class MedicalCheckupController {
    private MedicalCheckupDao medicalDao;
    public MedicalCheckupController() {
        medicalDao = new MedicalCheckupDao();
    }
    public void createMcu(String date, String note, String result, int doctor_id, int patient_id) {
        this.medicalDao.createMcu(date, note, result, doctor_id, patient_id);
    }
}
