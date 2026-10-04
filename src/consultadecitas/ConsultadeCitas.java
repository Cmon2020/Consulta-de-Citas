/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package consultadecitas;

import com.consultorio.model.Cita;
import com.consultorio.model.Doctor;
import com.consultorio.model.Paciente;
import com.consultorio.service.ConsultorioService;
import java.util.Scanner;

/**
 *
 * @author chrisjonathanmonico
 */
public class ConsultadeCitas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConsultorioService service = new ConsultorioService();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        
        do {
            System.out.println("\n--- SISTEMA DE GESTIÓN DE CONSULTORIO MÉDICO ---");
            System.out.println("1. Dar de alta a un doctor");
            System.out.println("2. Dar de alta a un paciente");
            System.out.println("3. Crear una cita");
            System.out.println("4. Ver lista de doctores");
            System.out.println("5. Ver lista de pacientes");
            System.out.println("6. Ver lista de citas");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }
            
            switch (opcion) {
                case 1:
                  System.out.print("Ingrese ID del Doctor: ");
                  String docId = scanner.nextLine();
                  System.out.print("Ingrese Nombre Completo: ");
                  String docNombre = scanner.nextLine();
                  System.out.print("Ingrese Especialidad: ");
                  String especialidad = scanner.nextLine();
                  
                  service.darDeAltaDoctor(new Doctor(docId, docNombre, especialidad));
                  System.out.println("Doctor registrado con éxito.");
                  break;
                  
                case 2: 
                   System.out.print("Ingrese ID del Paciente: ");
                   String pacId = scanner.nextLine();
                   System.out.print("Ingrese Nombre Completo: ");
                   String pacNombre = scanner.nextLine();
                   
                   service.darDeAltaPaciente(new Paciente(pacId, pacNombre));
                   System.out.println("Paciente registrado con éxito.");
                   break;
                   
                case 3:
                    System.out.print("Ingrese ID para la Cita");
                    String citaId = scanner.nextLine();
                    System.out.print("Ingrese Fecha y Hora (ej. 2026-10-15 10:00): ");
                    String fechaHora = scanner.nextLine();
                    System.out.print("Ingrese Motivo de la consulta: ");
                    String motivo = scanner.nextLine();
                    System.out.print("Ingrese ID del Doctor asignado: ");
                    String doctorId = scanner.nextLine();
                    System.out.print("Ingrese ID del Paciente: ");
                    String pacienteId = scanner.nextLine();
                    
                    boolean creada = service.crearCita(citaId, fechaHora, motivo, doctorId, pacienteId);
                    if (creada) {
                        System.out.println("Cita creada y agendada correctamente.");
                    } else {
                        System.out.println("Error: Verifique que el ID del Doctor y del Paciente existan.");
                    }
                    break;
                    
                case 4:
                    System.out.println("\n--- LISTA DE DOCTORES ---");
                    if (service.getDoctores().isEmpty()) {
                        System.out.println("No hay doctores registrados.");
                    } else {
                        for (Doctor d : service.getDoctores()) {
                            System.out.println("ID: " + d.getId() + " | Nombre: " + d.getNombreCompleto() + " | Especialidad: " + d.getEspecialidad());
                        }
                    }
                    break;
                    
                case 5:
                    System.out.println("\n--- LISTA DE PACIENTES ---");
                    if (service.getPacientes().isEmpty()) {
                        System.out.println("No hay pacientes registrados.");
                    } else {
                        for (Paciente p : service.getPacientes()) {
                            System.out.println("ID: " + p.getId() + " | Nombre: " + p.getNombreCompleto());
                        }
                    }
                    break;
                    
                case 6:
                    System.out.println("\n--- LISTA DE CITAS ---");
                    if (service.getCitas().isEmpty()) {
                        System.out.println("No hay citas registradas.");
                    } else {
                        for (Cita c : service.getCitas()) {
                            System.out.println("Cita ID: " + c.getId() + " | Fecha: " + c.getFechaHora() + " | Motivo: " + c.getMotivo() +
                                    " | Doctor: " + c.getDoctor().getNombreCompleto() + " | Paciente: " + c.getPaciente().getNombreCompleto());
                        }
                    }    
                    break;
                    
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
                    
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }        
        } while (opcion != 7);
        
        scanner.close();
    }    
}

                    