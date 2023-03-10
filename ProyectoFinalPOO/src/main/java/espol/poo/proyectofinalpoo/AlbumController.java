package espol.poo.proyectofinalpoo;

import static espol.poo.proyectofinalpoo.App.galeria;
import espol.poo.proyectofinalpoo.model.Album;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AlbumController implements Initializable {

    @FXML
    private Button guardarAlbumNuevo;
    @FXML
    private Button cerrarCrearAlbum;
    @FXML
    private TextField textNombre;
    @FXML
    private TextArea textDescrip;
    @FXML
    private DatePicker dtFecha;
        
    private static Scene scene;    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){        
        cerrarCrearAlbum.setOnAction(event -> {
            Node source = (Node) event.getSource();  
            Stage stage = (Stage) source.getScene().getWindow();  
            stage.close();            
        });
        
        guardarAlbumNuevo.setOnAction(event -> {
            String nombreAlbum = textNombre.getText();
            String descripcionAlbum = textDescrip.getText();
            LocalDate date = dtFecha.getValue();
            dtFecha.setEditable(false);
        
            if((!nombreAlbum.equals("")) & (!descripcionAlbum.equals("")) & (date != null)){                       
                App.galeria.agregarAlbum(new Album(nombreAlbum, descripcionAlbum, date));                
                Node source = (Node) event.getSource();  
                Stage stage = (Stage) source.getScene().getWindow();  
                stage.close();                
                App.mostrarMensaje(Alert.AlertType.INFORMATION, "Advertencia", "Informaci??n del Album", "Ha creado el ??lbum con ??xito");
            }else{
                App.mostrarMensaje(Alert.AlertType.WARNING, "Advertencia", "Informaci??n", "Llene todos los campos");                
            }             
        });        
    }
        
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void nuevaVentana() throws IOException {                
        Stage stage = new Stage();        
        scene = new Scene(loadFXML("tertiary"));
        stage.setScene(scene);
        stage.setTitle("Crear ??lbun Nuevo");
        stage.show();
    }                  
}
