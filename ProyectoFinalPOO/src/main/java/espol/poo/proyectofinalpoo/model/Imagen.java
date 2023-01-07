package poo.espol.proyectofinalpoo.model;
import java.util.ArrayList;

public abstract class Imagen {
    private String descripcion;
    private String lugar;
    private String fecha;
    private ArrayList<Persona> personas;

    public Imagen(String descripcion, String lugar ,String fecha ,ArrayList<Persona> personas){
        this(lugar, fecha, personas);
        this.descripcion = descripcion;    
    }

    public Imagen(String lugar ,String fecha ,ArrayList<Persona> personas){
        this.lugar = lugar;
        this.fecha = fecha;
        this.personas = personas;    
    }

    public String getDescripcion(){
        return descripcion;
    }
    
    public String getLugar(){
        return lugar;
    }
    
    public String getFecha(){
        return fecha;
    }

      public ArrayList<Persona> getPersonas(){
        return personas;
    }

    public void agregarPersona(Persona p){
        personas.add(p);
    }

    public abstract void mostrarLugares();

    public abstract void mostrarPersonas();
    
}
