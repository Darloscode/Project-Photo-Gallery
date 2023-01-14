package espol.poo.proyectofinalpoo;

import espol.poo.proyectofinalpoo.model.Album;
import espol.poo.proyectofinalpoo.model.Fotografia;
import espol.poo.proyectofinalpoo.model.Galeria;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.stage.WindowEvent;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;    
    public static Galeria galeria = new Galeria();      

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));                      

        stage.setOnCloseRequest((WindowEvent event) -> {
            almacenarData();
        });
        
        stage.setScene(scene);
        stage.setTitle("Galeria de Fotos");
        stage.show(); 

    }       
    
    @Override
    public void stop()throws IOException{
    }

    static void setRoot(String fxml) throws IOException {        
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {        
        launch();        
    }
    
    public static void almacenarData(){        
        try{
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("data.ser"));
            salida.writeObject(galeria);
            salida.close();
            System.out.println("SERIALIZADO");
        }catch(Exception e){
            System.out.println("NO SE PUEDE SERIALIZAR");
        }
    }
    
    public static void recuperarData(){
        try{
            ObjectInputStream oit= new ObjectInputStream(new FileInputStream("data.ser"));                                    
            galeria = (Galeria) oit.readObject();
            
            for(Album s : App.galeria.getAlbunes()){
                System.out.println(s.toString());
            }
            oit.close();
        }catch(Exception e){
            System.out.println("error");
        }
    }

}