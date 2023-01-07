package poo.espol.proyectofinalpoo.model;

import java.util.ArrayList;
import java.io.Serializable;

public class Album extends Imagen implements Serializable, Comparable<Album>{
    private ArrayList<Fotografia> fotografias;
    private String nombre;         
    
    public Album(String nombre, String descripcion, String fecha, String lugar, ArrayList<Persona>personas){      
      super(descripcion, lugar, fecha, personas); 
      this.nombre = nombre;
        
    }
    
    public Album(String nombre, String descripcion, String fecha, String lugar, ArrayList<Persona>personas, ArrayList<Fotografia> fotografias){
        super(descripcion, lugar, fecha, personas);
        this.nombre = nombre;
        this.fotografias = fotografias;
    }

    public String getNombre(){
      return nombre;
    }
  
    public void agreagrFoto( Fotografia foto ){
      fotografias.add(foto);
    }
    
    public void agregarPersona( Persona p ){
      super.agregarPersona(p);
      System.out.println("Se añadió una persona al album");
    }

    public void mostrarLugares(){
      
    }

    public void mostrarPersonas(){
      
    }
    
    public void activarSlideshow(){
      //Se activará modo Slideshow
    }
    
    public void mostrarFotos(){
      for(Fotografia ft : fotografias){
        //Se motrará cada foto        
      }
    }

    //Muestra la informacion del album
    public String toString(){
      return "Nombre: "+nombre+"\nLugar: "+getLugar()+"Fecha: "+getFecha();
    }

    public int compareTo(Album al){      
      return 0;
    } 
    
}