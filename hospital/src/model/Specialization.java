/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kevin
 */
public class Specialization {
    private Integer specializationId;
    private String name;
    private String description;

    
    public Integer getSpecializationId() {
        return specializationId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setSpecializationId(Integer specializationId) {
        this.specializationId = specializationId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
