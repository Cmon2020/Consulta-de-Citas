/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.consultorio.service;

import com.consultorio.model.Cita;
import com.consultorio.model.Doctor;
import com.consultorio.model.Paciente;
import com.consultorio.persistence.DataManager;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author chrisjonathanmonico
 */
public class ConsultorioService {
    
    private List<Doctor> doctores;
    private List<Paciente> pacientes;
    private List<Cita> citas;
    private DataManager dataManager;
    
    public ConsultorioService() {
        dataManager = new DataManager();
        cargarDatos();
        
    }
  
    private void cargarDatos() {
        doctores = dataManager.cargarDoctores();
        pacientes = dataManager.cargarPacientes();
        citas = dataManager.cargarCitas(doctores, pacientes);        
    }
    
    public void darDeAltaDoctor(Doctor doctor) {
        doctores.add(doctor);
        dataManager.guardarDoctores(doctores);
    }
    public void darDeAltaPaciente(Paciente paciente) {
        pacientes.add(paciente);
        dataManager.guardarPacientes(pacientes);        
    }
    public boolean crearCita(String id, String fechaHora, String motivo, String doctorId, String pacienteId) {
        Doctor doc = buscarDoctorPorId(doctorId);
        Paciente pac = buscarPacientePorId(pacienteId);
        
        if (doc != null && pac != null) {
            Cita nuevaCita = new Cita(id, fechaHora, motivo, doc, pac);
            citas.add(nuevaCita);
            dataManager.guardarCitas(citas);
            return true;
        }
        return false;
    }

    public Doctor buscarDoctorPorId(String id) {
        return doctores.stream()
                .filter(d -> d.getId().equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }
    
    public Paciente buscarPacientePorId(String id) {
        return pacientes.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }
    
    public List<Doctor> getDoctores() { return doctores; }
    public List<Paciente> getPacientes() { return pacientes; }
    public List<Cita> getCitas() { return citas; }
}