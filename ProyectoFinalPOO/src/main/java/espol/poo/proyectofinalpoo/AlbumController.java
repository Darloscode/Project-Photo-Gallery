package espol.poo.proyectofinalpoo;

import static espol.poo.proyectofinalpoo.App.galeria;
import espol.poo.proyectofinalpoo.model.Album;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
            Node source = (Node) event.getSource();     //Me devuelve el elemento al que hice click
            Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
            stage.close();            
        });
        
        guardarAlbumNuevo.setOnAction(event -> {
            String nombreAlbum = textNombre.getText();
            String descripcionAlbum = textDescrip.getText();
            LocalDate date = dtFecha.getValue();
            dtFecha.setEditable(false);
        
            if((!nombreAlbum.equals("")) & (!descripcionAlbum.equals("")) & (date != null)){            
                App.galeria.agregarAlbum(new Album(nombreAlbum, descripcionAlbum, date));                                                                              
                Node source = (Node) event.getSource();     //Me devuelve el elemento al que hice click
                Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
                stage.close();
                mostrarMensaje(Alert.AlertType.INFORMATION,"Ha creado el álbum con éxito");
            }else{
                mostrarMensaje(Alert.AlertType.WARNING,"Llene todos los campos");
                textNombre.clear();
                textDescrip.clear();                
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
        stage.setTitle("Crear Álbun Nuevo");
        stage.show();
    }            
    
    @FXML
    private void crearAlbum()throws IOException {                            
        //System.out.println(date.toString());        
    }
        
    public void mostrarMensaje(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);        
        alert.setTitle("Advertencia");
        alert.setHeaderText("Información del Album");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
