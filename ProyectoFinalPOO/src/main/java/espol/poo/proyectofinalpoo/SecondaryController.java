package espol.poo.proyectofinalpoo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;


public class SecondaryController{

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    @FXML
    private ImageView imageview;
    @FXML
    private ImageView imageupload ;
    
    @FXML 
    Image imagen ; 
    
    @FXML
    private Button cargarImagen; //Abre la imagen
    @FXML
    private Button guardarImagen;
    @FXML
  
    private VBox contenedorV ;
    @FXML
    private HBox contenedorH;        
    
    @FXML
    private Button mostrarImagen; 
    
    final FileChooser archivo = new FileChooser();
    /* FileInputStream  entrada; 
    FileOutputStream salida; 
    ObjectOutputStream ObjectSalida ; 
    File file  ; 
    byte[] byteImg= new byte[1024*100];*/
    
    HBox contenedor= new HBox(1.0);
  
    @FXML 
    /**
     * Initializes the controller class.
     */
    
     /*
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    */
    
    
    private void handlebtncargarImagen(ActionEvent event) {
         File fil = archivo.showOpenDialog(null); //muestra un nuevo dialogo de arvhivo
        if(fil != null){
             // muestra en el imageview la imagen que se crea abriendo el archivo

             imageview.setImage(new Image(fil.toURI().toString()));
              
             // guarda en un hbox la imagen
              
        } else{ 
            System.out.println("Archivo invalido"); 
        }
        
    }
    
    @FXML
    private void handlebtnguardarImagen(ActionEvent event){
        
         //String imagenpath =contenedor.getChildren().toString();
        
         
        try{
            FileOutputStream FileOut= new FileOutputStream("Personas.ser");
            ObjectOutputStream ObjectOut = new ObjectOutputStream(FileOut);
            ObjectOut.writeObject( contenedor.getChildren().add(imageview));
            ObjectOut.close();
           
            
        }catch (Exception e){
             e.printStackTrace();
      
    }
        
    
}
    
@FXML
    private void abrirImagen (ActionEvent event) throws ClassNotFoundException {
      imageupload = new ImageView(); 
    try{
        FileInputStream FileIn = new FileInputStream("Personas.ser");
        ObjectInputStream ObjectEntrada = new ObjectInputStream(FileIn); 
        //contenedor = (HBox)ObjectEntrada.readObject();
        imagen = (Image)ObjectEntrada.readObject();
        imageupload.setImage(new Image(imagen.getUrl()));
            // castin de contenedor a imagen
        }catch (IOException e){
        e.printStackTrace();
        
        }
  
    } 
     
}