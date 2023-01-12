package espol.poo.proyectofinalpoo;

import espol.poo.proyectofinalpoo.model.Album;
import espol.poo.proyectofinalpoo.model.Galeria;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;    
    public static Galeria galeria = new Galeria();      

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
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

    public static void main(String[] args) {
        launch();
        almacenarData();//Busca otra forma
    }
    
    public static void almacenarData(){        
        try{
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("data.ser"));
            salida.writeObject(galeria);
            salida.close();
        }catch(Exception e){
            System.out.println("NO SE PUEDE SERIALIZAR");
        }
    }

}