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
    private String specializationId;
    private String name;
    private String description;

    public Specialization(String specializationId, String name, String description) {
        this.specializationId = specializationId;
        this.name = name;
        this.description = description;
    }

    public String getSpecializationId() {
        return specializationId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setSpecializationId(String specializationId) {
        this.specializationId = specializationId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
