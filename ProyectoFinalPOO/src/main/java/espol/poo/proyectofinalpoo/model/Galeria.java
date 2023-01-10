package espol.poo.proyectofinalpoo.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Galeria implements Serializable, Comparable<Galeria>{
    
    private ArrayList<Album> albunes = new ArrayList<>(); 
    //private ArrayList<Fotografia> fotos;
   

    public ArrayList<Album> getAlbunes(){
      return albunes;
    }
/*
    public ArrayList<Fotografia> getFotografias(){
      return fotos;
    }
  */
    public void mostrarAlbunes(){
        for(Album ft : albunes){
            System.out.println(ft.toString());
        }
    }
    public void agregarAlbum(Album al){
        albunes.add(al);        
    }
/*
    public void agregarFoto(Fotografia ft){
        fotos.add(ft);
    }
  
    public void mostrarFotos(){
        for(Fotografia ft : fotos){
          //Se mostrará las fotos de la galeria
        }
        for(Album ft : albunes){
            ft.mostrarFotos();
          //Se mostrará las fotos de los albumnes que estan en la galeria
        }
    }
    
    public void mostrarLugares(){
      //Se mostrará todos los lugares de las fotos
    }
    
    public void mostrarPersonas(){
        for(Fotografia ft : fotos){ 
          //Se motrará una lista ordenada de todos las personas que aparecen
        }
    }
  */
    public int compareTo(Galeria ft){      
      return 0;
    } 
}
