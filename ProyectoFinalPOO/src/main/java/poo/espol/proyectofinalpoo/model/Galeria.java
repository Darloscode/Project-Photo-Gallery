package poo.espol.proyectofinalpoo.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Galeria implements Serializable{
    
    private ArrayList<Album> albunes ; 
    private ArrayList<Fotografia> fotos;
    
    public void mostrarAlbunes(){
        for(Album ft : albunes){
        }
    }
    
    public void mostrarFotos(){
        for(Fotografia ft : fotos){
        }
        for(Album ft : albunes){
            ft.mostrarFotos();
        }
    }
    
    public void mostrarLugares(){        
    }
    
    public void mostrarPersonas(){
        for(Fotografia ft : fotos){            
        }
    }
}
