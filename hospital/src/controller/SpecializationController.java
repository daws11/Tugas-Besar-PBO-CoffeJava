
package controller;

import Dao.SpecializationDao;
import DaoInterface.IDaoSpecialization;
import java.util.List;
import javax.swing.JOptionPane;
import model.Specialization;
import model.Tbl_Model_Specialization;
import view.SpecializationGUI;

/**
 *
 * @author rinda
 */
public class SpecializationController {
    SpecializationGUI frame_specialization;
    IDaoSpecialization implement_Specialization;
    List<Specialization> list_Specializations;
    SpecializationDao specializationDao;
    

    public SpecializationController(SpecializationGUI frame_specialization) {
        this.frame_specialization = frame_specialization;
        implement_Specialization = new SpecializationDao();
        list_Specializations = implement_Specialization.getSpecializations();
        
    }
    public SpecializationController(){
        this.specializationDao = new SpecializationDao();
    }
    
    //Tombol Reset
    public void reset(){
        frame_specialization.getTxtSpecializationId().setText("");
        frame_specialization.getTxtName().setText("");
        frame_specialization.getTxtDescription().setText("");
    }
    
    //Tampil Data Ke Tabel
    public void isiTable(){
        list_Specializations = implement_Specialization.getSpecializations();
        Tbl_Model_Specialization tabelModel_Specialization = new Tbl_Model_Specialization(list_Specializations);
        frame_specialization.getTabelData().setModel(tabelModel_Specialization);
    }
    
    //Menampilkan Data Ke Form Ketika Data DI Klik
    public void isiField(int row){
        frame_specialization.getTxtSpecializationId().setText(list_Specializations.get(row).getSpecializationId().toString());
        frame_specialization.getTxtName().setText(list_Specializations.get(row).getName());
        frame_specialization.getTxtDescription().setText(list_Specializations.get(row).getDescription());     
    }
    
    //Insert Data Dari Form Ke Database
    public void insert(){
        if(!frame_specialization.getTxtName().getText().trim().isEmpty()&& !frame_specialization.getTxtDescription().getText().trim().isEmpty()){
            Specialization specialization = new Specialization();
            specialization.setName(frame_specialization.getTxtName().getText());
            specialization.setDescription(frame_specialization.getTxtDescription().getText());
            
            implement_Specialization.insert(specialization);
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan");
        } else {
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong");
        }
    }
    
    //Update Data Dari Tabel Ke Database
    public void update(){
        if(!frame_specialization.getTxtSpecializationId().getText().trim().isEmpty()){
            Specialization specialization = new Specialization();
            specialization.setName(frame_specialization.getTxtName().getText());
            specialization.setDescription(frame_specialization.getTxtDescription().getText());
            specialization.setSpecializationId(Integer.parseInt(frame_specialization.getTxtSpecializationId().getText()));
            
            implement_Specialization.update(specialization);
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Update");
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Data Yang Akan Di Update");
        }
    }
    
    //Hapus Data Pada Tabel
    public void delete(){
         if(!frame_specialization.getTxtSpecializationId().getText().trim().isEmpty()){
            Specialization specialization = new Specialization();
            int specializationId = Integer.parseInt(frame_specialization.getTxtSpecializationId().getText());
            
            implement_Specialization.delete(specializationId);
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Data Yang Akan Di Hapus");
        }
    }
    
    public Specialization getSpecializationById(int id){
        return specializationDao.getSpecializationbyId(id);
    }
    
}
    
