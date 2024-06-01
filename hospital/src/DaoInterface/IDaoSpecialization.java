/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DaoInterface;

import java.util.List;
import model.Specialization;

/**
 *
 * @author rinda
 */
public interface IDaoSpecialization {
    public void insert(Specialization specialization);
    
    public void update(Specialization specialization);
    
    public void delete(int specializationId);
    
    public List<Specialization> getSpecializations();

   
    
}
