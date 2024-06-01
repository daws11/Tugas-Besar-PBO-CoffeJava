/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Dao.AdminDao;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import model.Admin;
import model.Doctor;
import model.Specialization;
import util.PasswordUtil;

/**
 *
 * @author kevin
 */
public class AdminController {
    private AdminDao adminDao;
    public AdminController(){
        this.adminDao = new AdminDao();
    }
    public Admin login(String email, String Password) throws SQLException, NoSuchAlgorithmException{
        return this.adminDao.loginAdmin(email, Password);
    }
    
    public boolean addAdmin(String firstName, String lastName, String email, String phoneNumber, String password)throws SQLException, NoSuchAlgorithmException{
        String salt = PasswordUtil.getSalt();
        String hashedPassword = PasswordUtil.hashPassword(password, salt);
        Admin admin = new Admin(0, firstName, lastName, email, phoneNumber, hashedPassword, salt);
        return this.adminDao.addAdmin(admin);
    }
    
    public boolean addDoctor(String firstName, String lastName, int SpecializationId, String address, LocalDate birDate, String phoneNumber, String email, String password)throws SQLException, NoSuchAlgorithmException{
        SpecializationController specializationController = new SpecializationController();
        Specialization speecialization = specializationController.getSpecializationById(SpecializationId);
        String salt = PasswordUtil.getSalt();
        String hashedPassword = PasswordUtil.hashPassword(password, salt);
        Doctor doctor =  new Doctor(1, address, birDate, speecialization, firstName, lastName, email, phoneNumber, hashedPassword, salt);
        return this.adminDao.addDoctor(doctor);
    }
}
