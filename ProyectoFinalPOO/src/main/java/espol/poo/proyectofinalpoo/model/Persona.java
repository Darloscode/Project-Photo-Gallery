package espol.poo.proyectofinalpoo.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Persona implements Serializable{    
    private String nombre;
    private String apellido;
    private int edad; 
       
    public Persona(String nombre, String apellido, int edad){        
        this.nombre = nombre; 
        this.apellido = apellido;
        this.edad = edad;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
    
    public int getEdad(){
        return edad;
    }

    @Override
    public String toString() {        
        return "Persona: " + "\nCedula : "  + nombre + "\nApellido : "+ apellido + "\nEdad : " + edad;
    }  
}