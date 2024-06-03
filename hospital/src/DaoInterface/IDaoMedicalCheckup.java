/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DaoInterface;

/**
 *
 * @author Admin
 */
public interface IDaoMedicalCheckup {
    void createMcu(String date, String note, String result, int doctor_id, int patient_id);
}
