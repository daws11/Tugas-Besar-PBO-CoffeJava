/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DaoInterface;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import model.Admin;
import model.Doctor;

/**
 *
 * @author kevin
 */
public interface IDaoAdmin {
    public Admin loginAdmin(String email, String password) throws SQLException, NoSuchAlgorithmException;
    public boolean addAdmin(Admin admin) throws SQLException;
    public boolean editAdmin(int adminId, String firstName, String lastName, String email, String phoneNumber) throws SQLException;
    public boolean deleteAdmin(int adminId) throws SQLException;
    public boolean addDoctor(Doctor doctor) throws SQLException;
    public boolean deleteDoctor(int doctorId) throws SQLException;
    public boolean updateDoctor(Doctor doctor) throws SQLException;
    public List<Doctor> getAllDoctors() throws SQLException;
}
