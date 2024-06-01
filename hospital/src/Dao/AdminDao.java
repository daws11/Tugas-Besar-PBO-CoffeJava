/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import database.DataBaseConnection;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Admin;
import model.Doctor;
import model.Patient;
import model.Specialization;
import util.PasswordUtil;

/**
 *
 * @author kevin
 */
public class AdminDao {

    public Admin loginAdmin(String email, String password) throws SQLException, NoSuchAlgorithmException {
        String query = "SELECT * FROM admins WHERE email = ?";
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                String salt = resultSet.getString("salt");
                if (storedPassword.equals(PasswordUtil.hashPassword(password, salt))) {
                    int adminId = resultSet.getInt("AdminId");
                    String firstName = resultSet.getString("FirstName");
                    String LastName = resultSet.getString("LastName");

                    String phoneNumber = resultSet.getString("PhoneNumber");

                    return new Admin(adminId, firstName, LastName, email, phoneNumber, password, salt);
                } else {
                    JOptionPane.showMessageDialog(null, "wrong password");

                }
            } else {
                JOptionPane.showMessageDialog(null, "email not found");
            }

            connection.close();
        }
        return null;
    }

    public boolean addAdmin(Admin admin) throws SQLException {
        String query = "INSERT INTO admins (FirstName, LastName, Email, PhoneNumber, Password, Salt) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, admin.getPhoneNumber());
            statement.setString(5, admin.getPassword());
            statement.setString(6, admin.getSalt());
            connection.close();

            return statement.executeUpdate() > 0;
        }

    }

    public boolean addDoctor(Doctor doctor) throws SQLException {

        String query = "INSERT INTO doctors (FirstName, LastName, SpecializationId, Address, BirthDate, PhoneNumber,Email,Password,Salt) VALUES (?, ?, ?, ?, ?, ?,?,?,?)";
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, doctor.getFirstName());
            statement.setString(2, doctor.getLastName());
            statement.setInt(3, doctor.getSpecialization().getSpecializationId());
            statement.setString(4, doctor.getAddress());
            statement.setDate(5, java.sql.Date.valueOf(doctor.getBirtDate()));
            statement.setString(6, doctor.getPhoneNumber());
            statement.setString(7, doctor.getEmail());
            statement.setString(8, doctor.getPassword());
            statement.setString(9, doctor.getSalt());
            connection.close();
            return statement.executeUpdate() > 0;

        }
    }

    public boolean deleteDoctor(int doctorId) throws SQLException {
        String query = "DELETE FROM doctors WHERE DoctorID = ?";
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, doctorId);
            connection.close();

            return statement.executeUpdate() > 0;
        }
    }

    public boolean editDoctor(Doctor doctor) throws SQLException {
        String query = "UPDATE doctors SET FirstName=?, LastName=?, Email=?, PhoneNumber=?, SpecializationId=? WHERE DoctorID=?";
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, doctor.getFirstName());
            statement.setString(2, doctor.getLastName());
            statement.setString(3, doctor.getEmail());
            statement.setString(4, doctor.getPhoneNumber());
            statement.setInt(5, doctor.getSpecialization().getSpecializationId());
            statement.setInt(6, doctor.getDoctorId());
            connection.close();

            return statement.executeUpdate() > 0;
        }
    }

    public List<Doctor> getAllDoctor() throws SQLException {
        SpecializationDao specializationDao = new SpecializationDao();
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors";

        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int doctorId = resultSet.getInt("DoctorID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                int specializationId = resultSet.getInt("SpecializationId");
                String address = resultSet.getString("Address");
                LocalDate birthDate = resultSet.getDate("BirthDate").toLocalDate();
                String phoneNumber = resultSet.getString("PhoneNumber");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                String salt = resultSet.getString("Salt");

                Specialization specialization = specializationDao.getSpecializationbyId(specializationId);

                Doctor doctor = new Doctor(doctorId, address, birthDate, specialization, firstName, lastName, email, phoneNumber, password, salt);
                doctors.add(doctor);
            }
        }
        return doctors;
    }

    public boolean editPatient(Patient patient) throws SQLException {
        String query = "UPDATE patients SET FirstName=?, LastName=?, Email=?, PhoneNumber=?, Address=?, BirthDate=?, GenderName=?, bloodType=? WHERE PatientID=?";

        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setString(3, patient.getEmail());
            statement.setString(4, patient.getPhoneNumber());
            statement.setString(5, patient.getAddress());
            statement.setDate(6, java.sql.Date.valueOf(patient.getBirtDate()));
            statement.setString(7, patient.getGender());
            statement.setString(8, patient.getBloodType());
            statement.setInt(9, patient.getPatientId());

            return statement.executeUpdate() > 0;
        }
    }

    public boolean deletePatient(int patientId) throws SQLException {
        String query = "DELETE FROM patients WHERE PatientID = ?";
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, patientId);

            return statement.executeUpdate() > 0;
        }
    }

    public List<Patient> getAllPatient() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients";

        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int patientId = resultSet.getInt("PatientID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String password = resultSet.getString("Password");
                String salt = resultSet.getString("Salt");
                String address = resultSet.getString("Address");
                LocalDate birthDate = resultSet.getDate("BirthDate").toLocalDate();
                String gender = resultSet.getString("GenderName");
                String bloodType = resultSet.getString("bloodType");

                Patient patient = new Patient(patientId, address, birthDate, gender, bloodType, firstName, lastName, email, phoneNumber, password, salt);
                patients.add(patient);
            }
        }
        return patients;
    }

    public Patient getPatientById(int patientId) throws SQLException {
        Patient patient = null;
        String query = "SELECT * FROM patients WHERE PatientID = ?";

        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, patientId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String email = resultSet.getString("Email");
                    String phoneNumber = resultSet.getString("PhoneNumber");
                    String password = resultSet.getString("Password");
                    String salt = resultSet.getString("Salt");
                    String address = resultSet.getString("Address");
                    LocalDate birthDate = resultSet.getDate("BirthDate").toLocalDate();
                    String gender = resultSet.getString("GenderName");
                    String bloodType = resultSet.getString("bloodType");

                    patient = new Patient(patientId, address, birthDate, gender, bloodType, firstName, lastName, email, phoneNumber, password, salt);
                }
            }
        }
        return patient;
    }

}
