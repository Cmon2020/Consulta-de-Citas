/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.consultorio.model;

/**
 *
 * @author chrisjonathanmonico
 */

public abstract class Persona {
    private String id;
    private String nombreCompleto;
    
    public Persona(String id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }
    
    public String getId() { return id; }
    public String getNombreCompleto() {return nombreCompleto; }

    public abstract String getTipo();
}
