package espol.poo.proyectofinalpoo;

import espol.poo.proyectofinalpoo.model.Album;
import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AlbumController {
    @FXML
    private Button guardarAlbum;
    @FXML
    private TextField textNombre;
    @FXML
    private TextArea textDescrip;
    @FXML
    private DatePicker dtFecha;
    
    @FXML
    private void cancelarCrearAlbum() throws IOException {
        App.setRoot("primary");
    }
    
    @FXML
    private void crearAlbum()throws IOException {                
        String nombreAlbum = textNombre.getText();
        String descripcionAlbum = textDescrip.getText();
        LocalDate date = dtFecha.getValue();
        dtFecha.setEditable(false);
        
        if((!nombreAlbum.equals("")) & (!descripcionAlbum.equals("")) & (date != null)){            
            App.galeria.agregarAlbum(new Album(nombreAlbum, descripcionAlbum, date));
            App.setRoot("primary");
        }else{
            mostrarAlerta(Alert.AlertType.WARNING,"Llene todos los campos");
            textNombre.clear();
            textDescrip.clear();                
        }                
        //System.out.println(date.toString());        
    }
        
    public void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);        
        alert.setTitle("Advertencia");
        alert.setHeaderText("Información del Album");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
