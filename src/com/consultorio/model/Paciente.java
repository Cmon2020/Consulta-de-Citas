/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.consultorio.model;

/**
 *
 * @author chrisjonathanmonico
 */
public class Paciente extends Persona {
    
    public Paciente(String id, String nombreCompleto) {
        super(id, nombreCompleto);
    }
    
    @Override 
    public String getTipo() {
        return "Paciente";
    }
    
    public String toCSV() {
        return getId() + "," + getNombreCompleto();
    }
    
}
