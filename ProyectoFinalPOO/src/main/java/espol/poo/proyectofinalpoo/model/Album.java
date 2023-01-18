package espol.poo.proyectofinalpoo.model;

import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDate;

public class Album extends Imagen implements Serializable, Comparable<Album>{    
    private String nombre;
    private ArrayList<Fotografia> fotografias;     
    
    public Album(String nombre, String descripcion, LocalDate fecha){
        super(descripcion, fecha);
        this.nombre = nombre;
        this.fotografias = new ArrayList<>();
    }

    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }    

    public ArrayList<Fotografia> getFotografias(){
        return fotografias;
    }
    
    public void setFotografias(Fotografia fotografia){
        this.fotografias = fotografias;
    }
      
    public void agregarFoto(Fotografia ft){
        fotografias.add(ft);
    }

    public void agregarPersona( Persona p ){
      super.agregarPersona(p);
      System.out.println("Se añadió una persona al album");
    }    
    
    public String toString(){
      return "Nombre del Álbum: "+getNombre()+"\nDescripción: "+super.getDescripcion()+"\nFecha de creación: "+super.getFecha();
    }

    public int compareTo(Album al){      
      return 0;
    }     
}