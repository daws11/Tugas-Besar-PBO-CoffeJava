/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Dao.DoctorDao;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import model.Doctor;

/**
 *
 * @author kevin
 */
public class DoctorController {
    private DoctorDao doctorDao;
    public DoctorController(){
        this.doctorDao = new DoctorDao();
    }
    public Doctor login(String email, String Password) throws SQLException, NoSuchAlgorithmException{
        return this.doctorDao.loginDoctor(email, Password);
    }
}
