/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Dao.AdminDao;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
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
    private PasswordUtil passwordUtil;
    public AdminController(){
        this.adminDao = new AdminDao();
        PasswordUtil passwordUtil = new PasswordUtil();
    }
    public Admin login(String email, String Password) throws SQLException, NoSuchAlgorithmException{
        return this.adminDao.loginAdmin(email, Password);
    }
    
    public boolean addAdmin(String firstName, String lastName, String email, String phoneNumber, String password)throws SQLException, NoSuchAlgorithmException{
        
        String salt = PasswordUtil.getSalt();
        String hashedPassword = PasswordUtil.hashPassword(password, salt);
        Admin admin =  new Admin(0, firstName, lastName, email, phoneNumber, hashedPassword, salt);
        return this.adminDao.addAdmin(admin);
    }
    public boolean editAdmin(int adminId, String firstName, String lastName, String email, String phoneNumber) throws SQLException {
        return this.adminDao.editAdmin(adminId, firstName, lastName, email, phoneNumber);
    }
    public boolean deleteAdmin(int adminid) throws SQLException{
        return  this.adminDao.deleteAdmin(adminid);
    }
   public boolean addDoctor(String firstName, String lastName, int specializationId, String address, LocalDate birthDate, String phoneNumber, String email, String password) throws SQLException, NoSuchAlgorithmException {
       String salt = PasswordUtil.getSalt();
        String hashedPassword = PasswordUtil.hashPassword(password, salt);
        SpecializationController specializationController = new SpecializationController();
       Specialization specialization = specializationController.getSpecializationById(specializationId);
        Doctor doctor = new Doctor(0, address, birthDate, specialization, firstName, lastName, email, phoneNumber, hashedPassword, salt);
        return this.adminDao.addDoctor(doctor);
    }
    public boolean deleteDoctor(int doctorId)throws SQLException{
        return this.adminDao.deleteDoctor(doctorId);
    }
      public boolean updateDoctor(int doctorId, String firstName, String lastName, int specializationId, String address, LocalDate birthDate, String phoneNumber, String email) throws SQLException {
         Doctor existigdoctor = this.adminDao.getDoctorById(doctorId);
         if(existigdoctor.getEmail()==email){
             email =null;
         }
          if (existigdoctor.getPhoneNumber()==phoneNumber) {
              phoneNumber = null;
          }
          SpecializationController specializationController = new SpecializationController();
          Specialization specialization = specializationController.getSpecializationById(specializationId);
        Doctor doctor = new Doctor(doctorId, address, birthDate, specialization, firstName, lastName, email, phoneNumber, null, null);
        return this.adminDao.updateDoctor(doctor);
    }
      public List<Doctor> getAllDoctors() throws SQLException {
        return this.adminDao.getAllDoctors();
    }

    public Doctor getDoctorById(int doctorId) throws SQLException {
        return this.adminDao.getDoctorById(doctorId);
    }
    
    
}
