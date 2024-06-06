/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rinda
 */
public class Tbl_Model_Specialization extends AbstractTableModel{
    
    List<Specialization> list_specialization;

    public Tbl_Model_Specialization(List<Specialization> list_specialization) {
        this.list_specialization = list_specialization;
    }
    
    @Override
    public int getRowCount() {
        return list_specialization.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    
      public String getColumnName(int column) {
        switch (column){
            case 0:
                return "SpecializationID";
            case 1:
                return "Name";
            case 2:
                return "Description";
            default:
                return null;
        }
      }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch (columnIndex){
            case 0:
                return list_specialization.get(rowIndex).getSpecializationId();
            case 1:
                return list_specialization.get(rowIndex).getName();
            case 2:
                return list_specialization.get(rowIndex).getDescription();
            
            default:
             return null;
        }
    }
    
}
