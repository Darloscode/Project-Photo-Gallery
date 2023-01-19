package espol.poo.proyectofinalpoo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditAlbumController implements Initializable{
    
    @FXML
    private Button cancerlarAlbum;
    @FXML
    private Button eliminarAlbum;
    @FXML
    private Button guardarAlbum;
    @FXML
    private TextField textNombre;
    @FXML
    private TextArea textDescrip;
    
    private static Scene scene;
    
    public static int iAlbum;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cargarDatos();
        
        cancerlarAlbum.setOnAction(event -> {
            Node source = (Node) event.getSource();   
            Stage stage = (Stage) source.getScene().getWindow();    
            stage.close();            
        });
        
        guardarAlbum.setOnAction(eh -> {
            String nuevoNombre = textNombre.getText();
            String nuevaDescripcion = textDescrip.getText();
            
            if((!nuevoNombre.equals("")) & (!nuevaDescripcion.equals(""))){
                App.galeria.getAlbunes().get(iAlbum).setNombre(nuevoNombre);
                App.galeria.getAlbunes().get(iAlbum).setDescripcion(nuevaDescripcion);
                App.mostrarMensaje(Alert.AlertType.INFORMATION, "Editar Álbum", "Editar", "Se ha actualizado el album");
                Node source = (Node) eh.getSource();    
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close(); 
            }else{
                App.mostrarMensaje(Alert.AlertType.WARNING, "Editar Álbum", "Editar", "Debe llenar todos los campos");
            }            
        });
        
        eliminarAlbum.setOnAction(e -> {
            App.galeria.getAlbunes().remove(iAlbum);            
            Node source = (Node) e.getSource();    
            Stage stage = (Stage) source.getScene().getWindow();  
            stage.close();
            App.mostrarMensaje(Alert.AlertType.CONFIRMATION, "Eliminar", "Eliminar Album", "Se ha eliminado el mensaje correctamente");
        });                
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void nuevaVentana() throws IOException {                
        Stage stage = new Stage();        
        scene = new Scene(loadFXML("editalbum"));
        stage.setScene(scene);
        stage.setTitle("Crear Álbun Nuevo");
        stage.show();
    }
    
    public void cargarDatos(){
        String nombre = App.galeria.getAlbunes().get(iAlbum).getNombre();
        String descripcion = App.galeria.getAlbunes().get(iAlbum).getDescripcion();        
        
        textDescrip.setText(descripcion);
        textNombre.setText(nombre);        
    }
    
}
