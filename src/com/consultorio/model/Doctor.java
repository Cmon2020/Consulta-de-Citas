/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.consultorio.model;

/**
 *
 * @author chrisjonathanmonico
 */
public class Doctor extends Persona {
    private String especialidad;
    
    public Doctor(String id, String nombreCompleto, String especialidad) {
      super(id, nombreCompleto);
      this.especialidad = especialidad;
    }
    public String getEspecialidad() {
        return especialidad;
    }
    
    @Override
    public String getTipo() {
        return "Doctor";
    }
    
    public String toCSV() {
        return getId() + "," + getNombreCompleto() + "," + especialidad;
    }
    
}
