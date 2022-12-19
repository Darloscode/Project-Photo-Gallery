package poo.espol.proyectofinalpoo.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Fotografia {
    private String descripcion ; 
    private String lugar; 
    private String fecha ; 
    private  ArrayList<Persona> personas;
    private Album album;
    
    public Fotografia(String descripcion, String lugar ,String fecha ,ArrayList<Persona> personas, Album album){
        this(lugar, fecha, personas, album);
        this.descripcion = descripcion;        
    }
    
    public Fotografia(String lugar ,String fecha ,ArrayList<Persona> personas, Album album){
        this.lugar = lugar;
        this.fecha = fecha;
        this.personas = personas;
        this.album = album; 
    }
    
    public void añadirPersona (Persona p){
        personas.add(p);
    }
    
    public  void añadirInformacion(){        
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
    
    public String guardarImagen (File archivo, byte[]byteImg){
        String respuesta = null; 
        try{
            FileOutputStream salida =new FileOutputStream(archivo);
            salida.write(byteImg);
            respuesta = "la imagen ha sido guardada conexito";
        }catch (Exception e){
            
        }
         return respuesta; 
    } 
}
