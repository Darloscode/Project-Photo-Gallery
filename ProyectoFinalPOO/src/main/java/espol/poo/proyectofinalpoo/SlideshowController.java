package espol.poo.proyectofinalpoo;

import espol.poo.proyectofinalpoo.model.Fotografia;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class SlideshowController implements Initializable{        
    @FXML
    private ImageView visorImagen;
    @FXML
    private Button botonPlay;
    @FXML
    private Button botonStop;
    @FXML

    private ComboBox<Integer> botonVelocidad;
    Thread hilo;
    
    private volatile boolean running = true;
    private int x = 0;
    private int seleccionVelocidad;
    public static int iAlbum;
    
    private static Scene scene;    
    
    ArrayList<Fotografia> imagenes = App.galeria.getAlbunes().get(iAlbum).getFotografias();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        seleccionVelocidad = 1000;
        botonPlay.setOnAction((e) -> {
            running = true;
            hilo = new Thread(new Runnable() {
                public void run() {
                   while (running) {
                        if (x >= imagenes.size()) {
                            x = 0;
                        }
                        try {
                            Image image = imagenes.get(x).obtenerFoto(400, 500);                                                        
                            Platform.runLater(() -> {
                                visorImagen.setImage(image);
                            });
                            x++;
                            Thread.sleep(seleccionVelocidad);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
            hilo.setDaemon(true);
            hilo.start();
        });
        botonStop.setOnAction((e) -> {
            running = false;
        });
        
        botonVelocidad.getItems().addAll(1000,2000, 3000, 4000);
        botonVelocidad.setValue(1000);
        botonVelocidad.setOnAction(e -> {
            seleccionVelocidad = botonVelocidad.getValue();
        });
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void nuevaVentana() throws IOException {                
        Stage stage = new Stage();        
        scene = new Scene(loadFXML("quinary"));
        stage.setScene(scene);
        stage.setTitle("Modo Slideshow");
        stage.show();
    }            
}
