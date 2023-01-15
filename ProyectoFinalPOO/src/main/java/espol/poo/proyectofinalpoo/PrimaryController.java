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
    private Button editarFoto = null;
    /*
    public void cargarAlbunes(){
        for(Album al : App.galeria.getAlbunes()){
            String nombre = al.getNombre();
            HBox albumFotos = new HBox();
            TitledPane tpane = new TitledPane(nombre, albumFotos);
            if(!vboxAlbunes.getChildren().contains(tpane)){
                vboxAlbunes.getChildren().add(tpane);
            }
        }
    }*/
    
    
    public void cargarAlbunes() throws FileNotFoundException{            
        for(Album al : App.galeria.getAlbunes()){                        
            String nombre = al.getNombre();
            
            Label nombreAlbum = new Label("    "+nombre);
            
            FileInputStream input = new FileInputStream("icono.png");
            Image image = new Image(input, 17, 17, false, false);
            ImageView imageView = new ImageView(image);
            
            
            HBox albumFotos = new HBox();
            albumFotos.getChildren().add(imageView);
            albumFotos.getChildren().add(nombreAlbum);
            
            
            
            nombreAlbum.setOnMouseClicked(event -> {
                mostrarNombreAlbum.setText(nombre);
                mostrarDescripAlbum.setText(al.getDescripcion());
                mostrarNombreAl.setText(nombre);                
                //detalles.getChildren().remove(editarFoto); ARREGLAR
                
                mostrarDescripFoto.setText("");
                mostrarLugarFoto.setText("");
                mostrarAlbumFoto.setText("");
                mostrarFechaFoto.setText("");
                mostrarPersonasFoto.getChildren().clear();
                mostrarFotos.getChildren().clear();
                
                        
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
                                    Image img = al.getFotografias().get(j).obtenerFoto();
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
                                                mostrarPersonasFoto.getChildren().add(new Label(p.getNombre()+p.getApellido()));
                                            }                                            
                                        }else{
                                            mostrarPersonasFoto.getChildren().clear();
                                        }
                                        
                                        editarFoto = new Button("Editar Foto");
                                        
                                        detalles.getChildren().set(6, editarFoto);
                                        
                                        editarFoto.setOnAction(eh -> {
                                            System.out.println("EDITANDO");
                                            
                                        });
                                        
                                    });
                                    b++;
                                }catch(Exception e){
                                    System.out.println("Error");
                                }
                            }
                            //mostrarFotos.add(new Label(Integer.toString(j)), b, i);                                                                                                                
                        }                        
                    }                                        
                }else{
                    mostrarFotos.getChildren().clear();                           
                }               
                
                //mostrarFotos.setGridLinesVisible(true);                                
                //METODO PARA CARGAR IMAGENES
            });
            vboxAlbunes.getChildren().add(albumFotos);                                   
        }                 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){      
       
        App.recuperarData();
        Platform.runLater( ()-> { 
            try {
                cargarAlbunes();                
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        
        /*
        if(App.galeria.getAlbunes().size()>0){            
            Persona p1 = new Persona("Carlos", "Flores", 141);
            Persona p2 = new Persona("Kenneth", "Flores", 134);
            Persona p3 = new Persona("Alberto", "Flores", 12);
            Persona p4 = new Persona("Ginger", "Flores", 110);
            Persona p5 = new Persona("MANEUEL", "Flores", 10);
            Persona p6 = new Persona("SDFAs", "Flores", 1);
            Persona p7 = new Persona("CarlADFAos", "Flores", 11);
            Persona p8 = new Persona("DSDSCarlos", "Flores", 13);
            ArrayList<Persona> a1 = new ArrayList<>();
            a1.add(p1);
            a1.add(p2);
            a1.add(p3);
            a1.add(p4);
            ArrayList<Persona> a2 = new ArrayList<>();
            a2.add(p5);
            a2.add(p6);
            a2.add(p7);
            a2.add(p8);
            
            FileInputStream input;
         
            try {                
                input = new FileInputStream("icono.png");                
                Image image =  new Image(input, 17, 17, false, false);                               
                LocalDate d = LocalDate.MAX;                
                Fotografia ft = new Fotografia("Flores", "Ecuador",d , App.galeria.getAlbunes().get(0), "icono.png");
                Fotografia f1 = new Fotografia("CARLOS", "Ecuador",d , App.galeria.getAlbunes().get(0), "icono.png", a1);
                App.galeria.getAlbunes().get(0).agregarFoto(f1);
                App.galeria.getAlbunes().get(0).agregarFoto(ft);
                App.galeria.getAlbunes().get(0).agregarFoto(ft);
                App.galeria.getAlbunes().get(0).agregarFoto(ft);
                App.galeria.getAlbunes().get(0).agregarFoto(ft);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }     
            System.out.println("Aquí"+App.galeria.getAlbunes().get(0).getFotografias().size());
        }
       
        if(App.galeria.getAlbunes().size()>0){
            Persona p1 = new Persona
            LocalDate d = LocalDate.MAX;
            Fotografia ft = new Fotografia("Carlos", "Flores", "Ecuador",d , App.galeria.getAlbunes().get(0));
            Fotografia f1 = new Fotografia("Carlos", "Flores", "Ecuador",d , App.galeria.getAlbunes().get(0));
            Fotografia f2 = new Fotografia("Carlos", "Flores", "Ecuador",d , App.galeria.getAlbunes().get(0));
            Fotografia f3 = new Fotografia("Carlos", "Flores", "Ecuador",d , App.galeria.getAlbunes().get(0));
            Fotografia f4 = new Fotografia("Carlos", "Flores", "Ecuador",d , App.galeria.getAlbunes().get(0));        
            App.galeria.getAlbunes().get(0).agregarFoto(ft);
            App.galeria.getAlbunes().get(0).agregarFoto(f1);
            App.galeria.getAlbunes().get(0).agregarFoto(f2);
            App.galeria.getAlbunes().get(0).agregarFoto(f3);
            App.galeria.getAlbunes().get(0).agregarFoto(f4);
            System.out.println("Aquí"+App.galeria.getAlbunes().get(0).getFotografias().size());
        }
        */
        menuImport.setOnAction(event -> {
            try {
                SecondaryController.nuevaVentana();
                /*
                FileChooser imgChooser = new FileChooser();
                imgChooser.setTitle("Seleccionar Imagen");
                
                //Filtro para buscar solo imagenes .PNG
                imgChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
                );
                
                //Recuperar la imagen seleccionada
                File imagenFile = imgChooser.showOpenDialog(null);
                Image img = new Image("file:" + imagenFile.getAbsolutePath());
                
                try{
                agregarDetalles();
                }catch(IOException e){
                System.err.println("Error al mostrar Ventana de Información de Imgen");
                }
                */
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);        
            alert.setTitle("Información del Proyecto");
            alert.setHeaderText("Creadores");
            alert.setContentText("Carlos Flores González\nAaron Macias Catagua\nCristhian Rodriguez Villegas\n");
            alert.showAndWait();            
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


