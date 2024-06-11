/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DaoInterface;

/**
 *
 * @author LENOVO
 */
public interface IDaoRoom {
    public void editData(String roomname, String roomfloor, String roomnumber, String oldRoom);
    public void addData(String roomname, String roomfloor, String roomnumber);

}
