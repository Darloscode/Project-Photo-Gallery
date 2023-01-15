package espol.poo.proyectofinalpoo;

import espol.poo.proyectofinalpoo.model.Album;
import espol.poo.proyectofinalpoo.model.Fotografia;
import espol.poo.proyectofinalpoo.model.Galeria;
import espol.poo.proyectofinalpoo.model.Imagen;
import espol.poo.proyectofinalpoo.model.Persona;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class SecondaryController implements Initializable{
    
    @FXML
    private VBox vboxPersonas;
    @FXML
    private Button cancelarButton;
    @FXML
    private ComboBox albunes;
    @FXML
    private TextArea txtDescripcion; 
    @FXML
    private TextField txtLugar;
    @FXML
    private DatePicker txtFecha;
    @FXML
    private ComboBox Album;
    
    private String direccionImagen = null;
    
    
    private static Scene scene;    
                        
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        //añadirPersonas();                
        
        cancelarButton.setOnAction(event -> {
            Node source = (Node) event.getSource();     //Me devuelve el elemento al que hice click
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
        scene = new Scene(loadFXML("secondary"));
        stage.setScene(scene);
        stage.setTitle("Abrir nueva Fotografía");
        stage.show();
    }            

    public void mostrarMensaje(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);        
        alert.setTitle("Advertencia");
        alert.setHeaderText("Información de la Fotografía");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
           
    private void cargarDatos(){        
        for(Album al : App.galeria.getAlbunes()){
            String nombre = al.getNombre();
            albunes.getItems().add(nombre);            
        }
        albunes.setValue("-SELECCIONE-");
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
        vboxPersonas.getChildren().add(persona);                    
        eliminar.setOnAction(eve -> {                        
            vboxPersonas.getChildren().remove(persona);            
        });        
    }
    
    @FXML
    private void cargarImagen() {
        try{
            FileChooser imgChooser = new FileChooser();
            imgChooser.setTitle("Seleccionar Imagen");
                
            //Filtro para buscar solo imagenes .PNG
            imgChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
                
            //Recuperar la imagen seleccionada
            File imagenFile = imgChooser.showOpenDialog(null);
            direccionImagen = imagenFile.getAbsolutePath().toString();
            Image img = new Image("file:" + imagenFile.getAbsolutePath());
        
            imageview.setImage(img);                        
        }catch(Exception e){
            System.out.println("No se ha cargado ninguna imagen");
        }
        
    }
    
    
    @FXML
    private void guardarDatos(ActionEvent event){
        ArrayList<Persona> listaPersonas = new ArrayList<>();
                                
        String descripcion = txtDescripcion.getText();
        String lugar = txtLugar.getText();
        LocalDate fecha = txtFecha.getValue();
        
        String nombreAlbum = (String) albunes.getValue(); 
        
        if((!nombreAlbum.equals("-SELECCIONE-")) & (!txtDescripcion.equals("")) & (!txtLugar.equals("")) & (fecha != null) & (direccionImagen != null)){
            
            int indice = encontrarAlbum(nombreAlbum);     
            System.out.println(indice);
            
            for(Node v : vboxPersonas.getChildren()){
                HBox personas = (HBox) v;
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
                    listaPersonas.add(p1);
                }else{
                    mostrarMensaje(Alert.AlertType.WARNING,"Seleccione una edad válida para "+nombrePersona);
                }            
            }
        
            Fotografia ft = null;
        
            if(listaPersonas.size()>0){
                ft = new Fotografia(descripcion, lugar, fecha, App.galeria.getAlbunes().get(indice), direccionImagen, listaPersonas);
                System.out.println("TODO BIEN CON PESONAS");
            }else{
                ft = new Fotografia(descripcion, lugar, fecha, App.galeria.getAlbunes().get(indice), direccionImagen);
                System.out.println("TODO BIEN SIN PERSONAS");
            }
                        
            App.galeria.getAlbunes().get(indice).agregarFoto(ft);
            System.out.println("HAS AGREGADO CON EXITO");    
            
            Node source = (Node) event.getSource();     //Me devuelve el elemento al que hice click
            Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
            stage.close();
            
        }else{
            mostrarMensaje(Alert.AlertType.WARNING,"Llene todos los campos");                
        }           
    }
            

    private int encontrarAlbum(String nombre){
        for(Album al : App.galeria.getAlbunes()){
            if(al.getNombre().equals(nombre)){
                return App.galeria.getAlbunes().indexOf(al);
            }
        }
        return -1;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
    
   
    @FXML
    private ImageView imageview; // MUESTRA LA IMAGEN EN SECONDARY
    @FXML
    private ImageView imageupload ;
    
    @FXML 
     Image imagen ; 
    
    @FXML
    private Button cargarImagen; //Abre la imag

    private Button guardarImagen; // SECONDAR
   
    private HBox contenedorH; 
    
    //Usando las clases para secondary controller 
    private Fotografia foto;
    private Persona persona;
    private Imagen imagenGeneral ; // Aqui esta la lista de personas 
    
    private Album alb ;
    //String nombre, String descripcion, String fecha, String lugar, ArrayList<Persona>personas
    private Galeria galeria; 

    
    private Button mostrarImagen; 
    
 
    /* FileInputStream  entrada; 
    FileOutputStream salida; 
    ObjectOutputStream ObjectSalida ; 
    File file  ; 
    byte[] byteImg= new byte[1024*100];*/
    
   
    // AÑADIR INFORMACION   ( SECUNDARY )
   
    
    
    // AÑADIR PERSONAS ( SECUNDARY )  
    @FXML
    private TextField txtNombre ;  private TextField txtApellido ;
    private ComboBox Edad ;  private Button Añadir ;  private Button Guardar; private Button Cancelar ;
    
    
   
    
    @FXML 
    private ArrayList<String> Albunes;
    
    final FileChooser archivo = new FileChooser();
   
    
    
   
    
     @FXML
    
    
    
    
 
 
    
    
    private void guardarImagen (ActionEvent event) throws ClassNotFoundException {
      imageupload = new ImageView(); 
    try{
        //FileInputStream FileIn = new FileInputStream("Personas.ser");
       // ObjectInputStream ObjectEntrada = new ObjectInputStream(FileIn); 
        //contenedor = (HBox)ObjectEntrada.readObject();
        
            // castin de contenedor a imagen
         
        //Image imagen = new Image(imageview.getImage().getUrl());
            // guardar imagen 
            
            FileWriter fi= new FileWriter("C:\\ProyectoFinalPOO\\src\\main\\java\\espol\\poo\\proyectofinalpoo\\galeria");
            fi.write(imagen.toString());
            
        }catch (IOException e){
            
        e.printStackTrace();
        
        }
  
    }
     
}