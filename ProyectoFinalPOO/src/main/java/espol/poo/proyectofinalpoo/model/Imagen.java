package espol.poo.proyectofinalpoo.model;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Imagen implements Serializable {
        
    private String descripcion;    
    private LocalDate fecha;
    private ArrayList<Persona> personas = new ArrayList<>();
    
    public Imagen(String descripcion, LocalDate fecha){        
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Imagen(String descripcion, LocalDate fecha, ArrayList<Persona> personas){
        this(descripcion, fecha);        
        this.personas = personas;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
            
    public LocalDate getFecha(){
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
