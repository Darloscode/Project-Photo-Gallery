package espol.poo.proyectofinalpoo.model;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Imagen {
    
    private String nombre;
    private String descripcion;    
    private LocalDate fecha;
    private ArrayList<Persona> personas;
    
    public Imagen(String nombre, String descripcion, LocalDate fecha){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Imagen(String nombre, String descripcion, LocalDate fecha, ArrayList<Persona> personas){
        this(nombre, descripcion, fecha);        
        this.personas = personas;
    }

    public String getNombre(){
        return nombre;
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
