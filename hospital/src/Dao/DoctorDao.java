package Dao;

import DaoInterface.IDaoDoctor;
import database.DataBaseConnection;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import model.Doctor;
import model.Patient;
import model.Specialization;
import util.PasswordUtil;
import Dao.SpecializationDao;
import controller.SpecializationController;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Appoiment;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author kevin
 */
public class DoctorDao implements IDaoDoctor {

    @Override
    public Doctor loginDoctor(String email, String password) throws SQLException, NoSuchAlgorithmException {
        String query = "SELECT * FROM doctors WHERE email = ?";

        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                String salt = resultSet.getString("salt");

                if (storedPassword.equals(PasswordUtil.hashPassword(password, salt))) {
                    int doctorId = resultSet.getInt("doctorId");
                    String address = resultSet.getString("Address");
                    LocalDate birthDate = resultSet.getDate("birthDate").toLocalDate();
                    String specializationId = resultSet.getString("specializationId");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    int SpecializationId = resultSet.getInt("SpecializationId");
                    SpecializationController specializationController = new SpecializationController();
                    Specialization specialization = specializationController.getSpecializationById(SpecializationId);

                    return new Doctor(doctorId, address, birthDate, specialization, firstName, lastName, email, phoneNumber, password, salt);
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
    
    @Override
    public ResultSet viewAllPatients() {
        String query = "SELECT * FROM patients";
        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } 
    }
    
    @Override
    public ResultSet viewDetailPatient(String name) {
        String query = "SELECT * FROM patients WHERE FirstName='"+ name +"'";
        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    } 
    
    @Override
    public ResultSet viewPatientWithDoctor(int id) {
        String query = "SELECT patients.FirstName, patients.PatientId FROM appoimentpatients JOIN patients ON patients.PatientId = appoimentpatients.PatientId JOIN appoiments ON appoiments.AppoimentId = appoimentpatients.AppoimentId JOIN doctors ON appoiments.DoctorId = doctors.DoctorId WHERE doctors.DoctorId = '"+ id +"'";
        try {
            Connection connection = DataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public  List<Appoiment> viewAppointmentListDoctor(int doctorId) {
        List<Appoiment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appoiments WHERE doctorId = ?";
        
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, doctorId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int appointmentId = resultSet.getInt("AppoimentId");
                    LocalTime start = resultSet.getTime("TimeStart").toLocalTime();
                    LocalTime end = resultSet.getTime("TimeEnd").toLocalTime();
                    int status = resultSet.getInt("Status");
                    int isCompleted = resultSet.getInt("IsCompleted");
                    int capacity = resultSet.getInt("Sapacity");
                    int roomId = resultSet.getInt("RoomId");
                    LocalDate date = resultSet.getDate("Date").toLocalDate();

                    Appoiment appointment = new Appoiment(appointmentId, start, end, status, isCompleted, capacity, roomId, doctorId, date);
                    appointments.add(appointment);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return appointments;
    }
    
    public List<Patient> getAppoimPatients (int AppoimentId){
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT p.* FROM patients p JOIN appoimentpatients ap ON p.patientId = ap.patientId WHERE ap.AppoimentId = ?";

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, AppoimentId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int patientId = resultSet.getInt("patientId");
                String address = resultSet.getString("address");
                LocalDate birthDate = resultSet.getDate("birthDate").toLocalDate();
                String gender = resultSet.getString("GenderName");
                String bloodType = resultSet.getString("bloodType");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phoneNumber");
                String password = resultSet.getString("password");
                String salt = resultSet.getString("salt");

                Patient patient = new Patient(patientId, address, birthDate, gender, bloodType, firstName, lastName, email, phoneNumber, password, salt);
                patients.add(patient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

}
