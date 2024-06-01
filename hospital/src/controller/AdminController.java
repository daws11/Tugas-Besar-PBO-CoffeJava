/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Dao.AdminDao;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import model.Admin;
import model.Doctor;

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
    
}
