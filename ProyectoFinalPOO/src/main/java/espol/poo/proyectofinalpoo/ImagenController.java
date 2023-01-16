package espol.poo.proyectofinalpoo;

import espol.poo.proyectofinalpoo.model.Album;
import espol.poo.proyectofinalpoo.model.Fotografia;
import espol.poo.proyectofinalpoo.model.Persona;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ImagenController implements Initializable{
    
    @FXML
    private ComboBox albumes;
    @FXML
    private VBox mostrarPersonas;
    @FXML
    private VBox agregarPersonas;
    @FXML
    private Button buttonAgregar;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonGuardar;
    
    private static Scene scene;   
    public static int iFoto;
    public static int iAlbum;
    
    String nombreAlbum;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        
        botonCancelar.setOnAction(eh -> {
            Node source = (Node) eh.getSource();     //Me devuelve el elemento al que hice click
            Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
            stage.close();
        });
        
        botonGuardar.setOnAction(eh -> {
            if(agregarPersonas.getChildren().size()>0){                
                for(Node nd : agregarPersonas.getChildren()){
                    HBox personas = (HBox) nd;
                    Node nombre = personas.getChildren().get(0);
                    TextField n = (TextField) nombre;
                    String nombrePersona = n.getText();
            
                    Node apellido = personas.getChildren().get(1);
                    TextField ap = (TextField) apellido;
                    String apellidoPersona = ap.getText();
            
                    Node edad = personas.getChildren().get(2);
                    ComboBox ed = (ComboBox) edad;
                    
                    int edadPersona = (int) ed.getValue();
                    
                    if(edadPersona !=0 ){
                        Persona p1 = new Persona(nombrePersona, apellidoPersona, edadPersona);                        
                        App.galeria.getAlbunes().get(iAlbum).getFotografias().get(iFoto).agregarPersona(p1);
                    }else{
                        App.mostrarMensaje(Alert.AlertType.WARNING, "Advertencia", "Información de la Fotografía", "Seleccione una edad válida para "+nombrePersona);
                    }            
                }                
            }
            
            for(Node nd : mostrarPersonas.getChildren()){
                HBox personas = (HBox) nd;
                    Node nombre = personas.getChildren().get(0);
                    TextField n = (TextField) nombre;
                    String nombrePersona = n.getText();
            
                    Node apellido = personas.getChildren().get(1);
                    TextField ap = (TextField) apellido;
                    String apellidoPersona = ap.getText();
            
                    Node edad = personas.getChildren().get(2);
                    ComboBox ed = (ComboBox) edad;
                    
                    int edadPersona = (int) ed.getValue();
                    
                    if(edadPersona !=0 ){
                        Persona p1 = new Persona(nombrePersona, apellidoPersona, edadPersona);                        
                        if(!App.galeria.getAlbunes().get(iAlbum).getFotografias().get(iFoto).getPersonas().contains(p1)){
                            App.galeria.getAlbunes().get(iAlbum).getFotografias().get(iFoto).agregarPersona(p1);
                        }
                    }else{
                        App.mostrarMensaje(Alert.AlertType.WARNING, "Advertencia", "Información de la Fotografía", "Seleccione una edad válida para "+nombrePersona);
                    }
            }
            String nombreSeleccionado = (String) albumes.getValue();             
            if(!nombreAlbum.equals(nombreSeleccionado)){
                int indice = encontrarAlbum(nombreSeleccionado);
                App.galeria.getAlbunes().get(indice).agregarFoto(App.galeria.getAlbunes().get(iAlbum).getFotografias().get(iFoto));
                App.galeria.getAlbunes().get(iAlbum).getFotografias().remove(iFoto);                
            }                        
            
            Node source = (Node) eh.getSource();     //Me devuelve el elemento al que hice click
            Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
            stage.close();
        });
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void nuevaVentana() throws IOException {                
        Stage stage = new Stage();        
        scene = new Scene(loadFXML("quaternary"));       
        stage.setScene(scene);
        stage.setTitle("Editar Fotografía");
        stage.show();        
    }
    
    private void cargarDatos(){
        nombreAlbum = App.galeria.getAlbunes().get(iAlbum).getNombre();
        
        for(Album al : App.galeria.getAlbunes()){
            albumes.getItems().add(al.getNombre());            
        }
        
        albumes.setValue(nombreAlbum);        
        
        for(Persona p : App.galeria.getAlbunes().get(iAlbum).getFotografias().get(iFoto).getPersonas()){
            String nombre = p.getNombre();
            String apellido = p.getApellido();
            int edad = p.getEdad();
            
            HBox persona = new HBox(2);
            persona.setAlignment(Pos.CENTER);
            
            TextField lnombre = new TextField(nombre);
            TextField lapellido = new TextField(apellido);
            
            ComboBox cedad =  new ComboBox();
            cedad.setValue(edad);
            
            for(int i=1; i<=100; i++){
                cedad.getItems().add(i);
            }
            
            Button eliminar = new Button();
            eliminar.setText("X");  
            
            persona.getChildren().add(lnombre);
            persona.getChildren().add(lapellido);
            persona.getChildren().add(cedad);
            persona.getChildren().add(eliminar);
            
            mostrarPersonas.getChildren().add(persona);                    
            eliminar.setOnAction(eve -> {
                App.galeria.getAlbunes().get(iAlbum).getFotografias().get(iFoto).getPersonas().remove(p);
                mostrarPersonas.getChildren().remove(persona);           
            });        
        }        
    }
    
    @FXML
    private void agregarPersonas() {         
        HBox persona = new HBox(2);
        persona.setAlignment(Pos.CENTER);
        
        for (int a= 0; a<2; a++){
            TextField  textfield  = new TextField();
            persona.getChildren().add(textfield);
        }
        
        ComboBox edad =  new ComboBox();
        edad.setValue(0);
        
        for(int i=1; i<=100; i++){
            edad.getItems().add(i);
        }
        
        Button eliminar = new Button();
        eliminar.setText("X");                
        
        persona.getChildren().add(edad);
        persona.getChildren().add(eliminar);
        
        agregarPersonas.getChildren().add(persona);                    
        
        eliminar.setOnAction(eve -> {                        
            agregarPersonas.getChildren().remove(persona);            
        });        
    }
    
    private int encontrarAlbum(String nombre){
        for(Album al : App.galeria.getAlbunes()){
            if(al.getNombre().equals(nombre)){
                return App.galeria.getAlbunes().indexOf(al);
            }
        }
        return -1;
    }
}