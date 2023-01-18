package espol.poo.proyectofinalpoo;

import static espol.poo.proyectofinalpoo.App.recuperarData;
import espol.poo.proyectofinalpoo.model.Album;
import espol.poo.proyectofinalpoo.model.Fotografia;
import espol.poo.proyectofinalpoo.model.Galeria;
import espol.poo.proyectofinalpoo.model.Persona;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {
    
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
    @FXML
    private MenuItem menuCerrar;
    @FXML
    private MenuItem menuAbout;        
    @FXML
    private VBox vboxAlbunes;  
    @FXML
    private Label mostrarNombreAlbum;
    @FXML
    private Label mostrarDescripAlbum;
    @FXML
    private Label mostrarNombreAl;
    @FXML
    private GridPane mostrarFotos;
    @FXML
    private VBox verFotos;
    @FXML
    private Text mostrarDescripFoto;
    @FXML
    private Label mostrarLugarFoto;
    @FXML
    private Label mostrarAlbumFoto;
    @FXML
    private Label mostrarFechaFoto;
    @FXML
    private VBox mostrarPersonasFoto;
    @FXML
    private VBox detalles;    
    @FXML
    private Label labelVacio;
    @FXML
    private Button editarFoto;    
    @FXML
    private Label labelVacio2;
    @FXML
    private ScrollPane scrollPersonas;
    @FXML
    private ScrollPane scrollGaleria;
    @FXML
    private ScrollPane scrollFiltrar;
    
    private Button modoSlideshow;
    
    private ImageView imageView;
    
    private int indFotografia;
    private int indAlbum;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        scrollPersonas.setStyle("-fx-background-color:transparent;");
        
        App.recuperarData();
        
        Platform.runLater( ()-> {             
            App.scene.setOnMouseEntered(eh -> {
                mostrarNombreAl.setText("Ningun Álbum Seleccionado");
                mostrarDescripFoto.setText("");
                mostrarLugarFoto.setText("");
                mostrarFechaFoto.setText("");
                mostrarAlbumFoto.setText("");
                mostrarPersonasFoto.getChildren().clear();
                detalles.getChildren().remove(modoSlideshow);
                mostrarDescripAlbum.setText("");
                mostrarNombreAlbum.setText("");
                detalles.getChildren().set(9, labelVacio2);                  
                mostrarFotos.getChildren().clear();
                cargarAlbunes();
                System.out.println("Actualizado");                
            });                                    
            cargarAlbunes();                                        
        });
                
        menuImport.setOnAction(event -> {
            try {
                SecondaryController.nuevaVentana();               
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        menuAlbum.setOnAction(event -> {
            try {
                AlbumController.nuevaVentana();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        menuCerrar.setOnAction(event -> {
            App.almacenarData();
            Platform.exit();        
        });
        
        menuAbout.setOnAction(event -> {                       
            App.mostrarMensaje(Alert.AlertType.INFORMATION, "Información del Proyecto", "Creadores", "Carlos Flores González\nAaron Macias Catagua\nCristhian Rodriguez Villegas\n");                       
        });   
        
    }
    
    public void cargarAlbunes(){
        Button editarAlbum = new Button("Editar Album");
        
        vboxAlbunes.getChildren().clear();
        
        for(Album al : App.galeria.getAlbunes()){                        
            String nombre = al.getNombre();
            
            Label nombreAlbum = new Label("    "+nombre);
            
            try{
                FileInputStream input = new FileInputStream("icono.png");
                Image image = new Image(input, 17, 17, false, false);
                imageView = new ImageView(image);
            }catch(Exception e){
                System.err.println("No se ha encontrado el icono");            
            }
            
                        
            HBox albumFotos = new HBox();
            albumFotos.getChildren().add(imageView);
            albumFotos.getChildren().add(nombreAlbum);
                                    
            nombreAlbum.setOnMouseClicked(event -> {                
                
                detalles.getChildren().remove(modoSlideshow);
                
                mostrarNombreAlbum.setText(nombre);
                mostrarDescripAlbum.setText(al.getDescripcion());
                mostrarNombreAl.setText(nombre);      
                
                detalles.getChildren().set(6, labelVacio);         
                
                indAlbum = App.galeria.getAlbunes().indexOf(al);
                
                mostrarDescripFoto.setText("");
                mostrarLugarFoto.setText("");
                mostrarAlbumFoto.setText("");
                mostrarFechaFoto.setText("");
                mostrarPersonasFoto.getChildren().clear();
                mostrarFotos.getChildren().clear();
                
                detalles.getChildren().set(9, editarAlbum);
                
                editarAlbum.setOnAction(eh -> {
                    try {
                        EditAlbumController.iAlbum = indAlbum;
                        EditAlbumController.nuevaVentana();                        
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                
                if(al.getFotografias().size()>0){
                    modoSlideshow = new Button("Activar Modo Slideshow");
                    detalles.getChildren().add(modoSlideshow);
                    modoSlideshow.setOnAction(eh -> {
                        try {
                            SlideshowController.iAlbum = indAlbum;
                            SlideshowController.nuevaVentana();
                            
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                }
                
                
                int a = al.getFotografias().size() % 3;
                System.out.println(al.getFotografias().size());
                System.out.println(a);
                
                if(a > 0){                                                      
                    for(int i=0; i<a; i++){                        
                        int c = i*3;
                        int b = 0;
                        for(int j=c; j<c+3; j++){
                            if(j<al.getFotografias().size()){
                                try{
                                    Image img = al.getFotografias().get(j).obtenerFoto(150, 200);
                                    ImageView foto = new ImageView(img);
                                    mostrarFotos.add(foto, b, i);
                                    String descripFoto = al.getFotografias().get(j).getDescripcion();
                                    String lugarFoto = al.getFotografias().get(j).getLugar();
                                    String albumFoto = al.getFotografias().get(j).getAlbum().getNombre();
                                    String fechaFoto = al.getFotografias().get(j).getFecha().toString();
                                    
                                    int cntPersonas = al.getFotografias().get(j).getPersonas().size();
                                    ArrayList<Persona> personas = al.getFotografias().get(j).getPersonas();
                                    System.out.println("Muestra"+cntPersonas);
                                                                        
                                    foto.setOnMouseClicked(ev -> {
                                        mostrarPersonasFoto.getChildren().clear();
                                        mostrarDescripFoto.setText(descripFoto);
                                        mostrarLugarFoto.setText(lugarFoto);
                                        mostrarAlbumFoto.setText(albumFoto);
                                        mostrarFechaFoto.setText(fechaFoto); 
                                                              
                                        if(personas.size()>0){
                                            for(Persona p : personas){
                                                mostrarPersonasFoto.getChildren().add(new Label("- "+p.getNombre()+" "+p.getApellido()));
                                            }                                            
                                        }else{
                                            mostrarPersonasFoto.getChildren().clear();
                                        }                                                                                
                                        
                                        editarFoto = new Button("Editar Foto");
                                        
                                        detalles.getChildren().set(6, editarFoto);
                                        
                                        editarFoto.setOnAction(eh -> {                                            
                                            editarFoto(descripFoto);
                                        });                                                                                
                                    });
                                    b++;
                                }catch(Exception e){
                                    System.out.println("Error");
                                }
                            }                            
                        }                        
                    }                                        
                }else{
                    System.out.println("No hay fotografias por mostrar");
                }           
            });
            vboxAlbunes.getChildren().add(albumFotos);                                   
        }                 
    }
              
    @FXML
    public void editarFoto(String descripcion){                 
        for(Fotografia ft : App.galeria.getAlbunes().get(indAlbum).getFotografias()){
            if(descripcion.equals(ft.getDescripcion())){
                indFotografia = App.galeria.getAlbunes().get(indAlbum).getFotografias().indexOf(ft);
            }
        }        
        
        ImagenController.iFoto = indFotografia;
        ImagenController.iAlbum = indAlbum;
        
        try {
            ImagenController.nuevaVentana();
        } catch (IOException ex) {
            ex.printStackTrace();
        }                
    }        
}