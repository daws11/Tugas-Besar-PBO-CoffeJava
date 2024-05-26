/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kevin
 */
public class Admin extends User {
    private int adminId;

    public Admin(int adminId, String firstName, String lastName, String email, String phoneNumber, String passwrod, String salt) {
        super(firstName, lastName, email, phoneNumber, passwrod, salt);
        this.adminId = adminId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    
}
