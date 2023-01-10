package espol.poo.proyectofinalpoo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class PrimaryController implements Initializable{
    
    @FXML
    private ImageView ivImagen;            
    @FXML
    private MenuItem menuImport;
    @FXML
    private ImageView imgView1;
    @FXML
    private TextArea textDescrip;
    @FXML
    private TextField textLugar;
    @FXML
    private DatePicker dtFecha;
    @FXML
    private MenuItem menuAlbum;
    
    
    
    private Scene sceneDetalles;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    public void initialize(URL url, ResourceBundle rb){        
        menuImport.setOnAction(event -> {
            FileChooser imgChooser = new FileChooser();
            imgChooser.setTitle("Seleccionar Imagen");
            
            //Filtro para buscar solo imagenes .PNG
            imgChooser.getExtensionFilters().addAll(                                       
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            
            //Recuperar la imagen seleccionada
            File imagenFile = imgChooser.showOpenDialog(null);
            Image img = new Image("file:" + imagenFile.getAbsolutePath());
            /*
            try{
                agregarDetalles();
            }catch(IOException e){
                System.err.println("Error al mostrar Ventana de Información de Imgen");
            }
            */
        });
        
        menuAlbum.setOnAction(event ->{
            try{
                App.setRoot("tertiary");
            }catch(IOException e){
                e.printStackTrace();
            }            
        });
        
        
    }
    
    public void cerrarGaleria()throws IOException{        
    }
    
    
    
    /*
    private void agregarDetalles() throws IOException{
        App.setRoot("secondary");
        
        String descripcion = textDescrip.getText();
        String lugar = textLugar.getText();
        
        dtFecha.setOnAction(event -> {
            LocalDate date = dtFecha.getValue();
            System.err.println("Selected date: " + date);
        });                        
    }
    */
}
