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
            FileInputStream input = new FileInputStream("icono.png");
            image = new Image(input, 50, 50, false, false);
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
    /*
    public Fotografia(String lugar ,String fecha ,ArrayList<Persona> personas){
        super(lugar, fecha, personas);        
    }
    
    
    public Album getAlbum(){
      return album;
    }*/
  
    public void agregarPersona (Persona p){
      //super.agregarPersona(p);
      System.out.println("Se añadió una persona a la fotografia");
    }

    public void mostrarLugares(){
      
    }

    public void mostrarPersonas(){
      
    }

    public int compareTo(Fotografia ft){      
      return 0;
    } 

    public void agregarInformacion(){
      //Se agreagará informacion o modificará segun lo indicado en la ejecucion del programa    
    }

    public void moverFotografia(Album al){
      
    }
  /*
    //Muestra la informacion de la fotografía
    public String toString(){
      return "Descripcion: "+getDescripcion()+"\nLugar: "+getLugar()+"\nFecha: "+getFecha();
    }
  
    public byte[] abrirImagen ( File archivo){
        byte[] byteImg= new byte[1024*100];
        try{
            FileInputStream entrada = new FileInputStream( archivo);
            entrada.read(byteImg);
        } catch (Exception e) {
            
        }
      return byteImg;
    } 
    
    public String guardarImagen(File archivo, byte[]byteImg){
        String respuesta = null; 
        try{
            FileOutputStream salida =new FileOutputStream(archivo);
            salida.write(byteImg);
            respuesta = "la imagen ha sido guardada con exito";
        }catch (Exception e){
            
        }
         return respuesta; 
    } */
}
