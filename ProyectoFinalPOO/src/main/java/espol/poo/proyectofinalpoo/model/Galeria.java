package espol.poo.proyectofinalpoo.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Galeria implements Serializable, Comparable<Galeria>{    
    private ArrayList<Album> albunes = new ArrayList<>();     
   
    public ArrayList<Album> getAlbunes(){
      return albunes;
    }
    
    public void mostrarAlbunes(){
        for(Album ft : albunes){
            System.out.println(ft.toString());
        }
    }
    
    public void agregarAlbum(Album al){
        albunes.add(al);        
    }

    public int compareTo(Galeria ft){      
      return 0;
    } 
}
