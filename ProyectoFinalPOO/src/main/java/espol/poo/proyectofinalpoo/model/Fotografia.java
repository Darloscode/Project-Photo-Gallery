package espol.poo.proyectofinalpoo.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDate;
import javafx.scene.image.Image;


public class Fotografia extends Imagen implements Serializable, Comparable<Fotografia>{    
    private Album album;
    private String lugar;
    private String imagen;
    private String nombre;
     
    public Fotografia(String descripcion, String lugar , LocalDate fecha, Album album, String imagen){
      super(descripcion, fecha);
      this.lugar = lugar;
      this.album = album;
      this.imagen = imagen;
    }
    
    public Fotografia(String descripcion, String lugar , LocalDate fecha, Album album, String imagen, ArrayList<Persona> personas){
      super(descripcion, fecha, personas);
      this.lugar = lugar;
      this.album = album;
      this.imagen = imagen;
    }
    
    public Image obtenerFoto(){
        Image image = null;
        try{
            FileInputStream input = new FileInputStream(imagen);
            image = new Image(input,150 , 150, false, false);
        }catch(Exception e){
            e.printStackTrace();
        }
        return image;
    }
    
    public Album getAlbum(){
        return album;
    }
    
    public String getLugar(){
        return lugar;
    }
    
    public String toString(){
        return super.getDescripcion()+"---"+getLugar()+"----"+album.getNombre();
    }     

    public void mostrarLugares(){      
    }

    public void mostrarPersonas(){      
    }

    public int compareTo(Fotografia ft){      
      return 0;
    }     
}
