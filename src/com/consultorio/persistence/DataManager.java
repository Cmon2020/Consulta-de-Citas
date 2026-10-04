/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.consultorio.persistence;

import com.consultorio.model.Cita;
import com.consultorio.model.Doctor;
import com.consultorio.model.Paciente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author chrisjonathanmonico
 */
public class DataManager {
    
    private final String folderPath = "db/";
    
    public DataManager() { 
        File directory = new File(folderPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
    
    public void guardarDoctores(List<Doctor> doctores) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(folderPath + "doctors.csv"))) {
            for (Doctor d : doctores) {
                bw.write(d.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar doctores: " + e.getMessage());
        }
    }
    
    public List<Doctor> cargarDoctores() {
        List<Doctor> doctores = new ArrayList<>();
        File file = new File(folderPath + "doctors.csv");
        if (!file.exists()) return doctores;
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    doctores.add(new Doctor(data[0], data[1], data[2]));
                    
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar doctores: " +e.getMessage());
        }
        return doctores;
    }
    public void guardarPacientes(List<Paciente> pacientes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(folderPath + "patients.csv"))) {           
            for (Paciente p : pacientes) {
               bw.write(p.toCSV());
               bw.newLine();
           } 
        }catch (IOException e) {
            System.err.println("Error al guradar pacientes: " + e.getMessage());
        }
    }
    public List<Paciente> cargarPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        File file = new File(folderPath + "patients.csv");
        if (!file.exists()) return pacientes;
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    pacientes.add(new Paciente(data[0], data[1]));
                }
            }
        }catch (IOException e) {
            System.err.println("Error al cargar pacientes: " + e.getMessage());
        }
        return pacientes;
    }
    public void guardarCitas(List<Cita> citas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(folderPath + "appointments.csv"))) {
            for (Cita c : citas) {
                bw.write(c.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erroe al guardar citas" + e.getMessage());
        }
    }
         
    public List<Cita> cargarCitas(List<Doctor> doctores, List<Paciente> pacientes) {
        List<Cita> citas = new ArrayList<>();
        File file = new File(folderPath + "appointments.csv");
        if (!file.exists()) return citas;
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String id = data[0];
                    String fechaHora = data[1];
                    String motivo = data [2];
                    String doctorId = data[3];
                    String pacienteId = data[4];
                    
                    Doctor doc = doctores.stream()
                            .filter(d -> d.getId().equals(doctorId))
                            .findFirst().orElse(null);
                    
                    Paciente pac = pacientes.stream()
                            .filter(p -> p.getId().equals(pacienteId))
                            .findFirst().orElse(null);
                    
                    if (doc != null && pac != null) {
                        citas.add(new Cita(id, fechaHora, motivo, doc, pac));
                    }    
                }
            }
        }catch (IOException e) {
            System.err.println("Error al cargar citas: " + e.getMessage());
        }
        return citas;
    }
}    