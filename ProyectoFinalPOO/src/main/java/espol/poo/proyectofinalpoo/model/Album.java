package espol.poo.proyectofinalpoo.model;

import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDate;

public class Album extends Imagen implements Serializable, Comparable<Album>{
    
    private String nombre;
    private ArrayList<Fotografia> fotografias ; 
    
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }           
    public Album(String nombre, String descripcion, LocalDate fecha){
        super(descripcion, fecha);
        this.nombre = nombre;
        this.fotografias = new ArrayList<>();
    } 
    
    public void setFotografias(Fotografia fotografia){
        this.fotografias = fotografias;
    }
    
    public ArrayList<Fotografia> getFotografias(){
        return fotografias;
    }
    
    public void agregarFoto(Fotografia ft){
        fotografias.add(ft);
    }
    
      
/*    
    public void agreagrFoto( Fotografia foto ){
      fotografias.add(foto);
    }
    */
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
    /*
    public void mostrarFotos(){
      for(Fotografia ft : fotografias){
        //Se motrará cada foto        
      }
    }*/

    //Muestra la informacion del album
    public String toString(){
      return "Nombre del Álbum: "+getNombre()+"\nDescripción: "+super.getDescripcion()+"Fecha de creación: "+super.getFecha();
    }

    public int compareTo(Album al){      
      return 0;
    } 
    
}