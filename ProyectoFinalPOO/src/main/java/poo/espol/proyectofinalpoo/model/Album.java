package poo.espol.proyectofinalpoo.model;

import java.util.ArrayList;

public class Album {
    private ArrayList<Fotografia> fotografias;
    private String nombre; 
    private String descripcion; 
    private String fecha; 
    private String lugar; 
    private ArrayList<Persona> personas;
    
    public Album(String nombre, String descripcion, String fecha, String lugar, ArrayList<Persona>personas){
        this.descripcion = descripcion;
        this.fecha = fecha ;         
        this.lugar = lugar ; 
        this.nombre = nombre ;
        this.personas = personas;
    }
    
    public Album(ArrayList<Fotografia> fotografias, String nombre, String descripcion, String fecha, String lugar, ArrayList<Persona>personas){
        this(nombre, descripcion, fecha, lugar, personas);
        this.fotografias = fotografias;
    }
    
    public void añadirFoto( Fotografia foto ){        
    }
    
    public void añadirPersona( Persona p ){        
    }
    
    public void activarSlideshow(){        
    }
    
    public void mostrarFotos(){        
    }
    
}