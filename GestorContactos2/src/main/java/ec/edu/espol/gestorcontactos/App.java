package ec.edu.espol.gestorcontactos;

import ec.edu.espol.model.Contacto;
import ec.edu.espol.model.Utilitaria;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.LinkedList;

public class App extends Application {

    private static Scene scene;
    private static Stage st;

    @Override
    public void start(Stage stage) throws IOException {
        st = stage;
        scene = new Scene(loadFXML("listContacto").load());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).load());
    }
    
    public static void setScene(Scene sc){
        st.setScene(sc);
    }
    
    public static Stage getStage(){
        return st;
    }
    
    public static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }
    
    public static void main(String[] args) {
        
        LinkedList<String> contactosLK2 = new LinkedList<>();
        LinkedList<String> contactosco2 = new LinkedList<>();
        LinkedList<String> contactost2 = new LinkedList<>();
        LinkedList<Contacto> contactosc2 = new LinkedList<>();
        contactosLK2.add("img/iconoFiltrar.png");
        contactosLK2.add("img/iconoFavorito.png");
        contactosLK2.add("img/iconoBuscar.png");
        Contacto c2 = new Contacto("Mar", "img/usuarioDefault.png", contactosLK2, contactosco2, contactost2, contactosc2);
        
        LinkedList<String> contactosLK1 = new LinkedList<>();
        LinkedList<String> contactosco1 = new LinkedList<>();
        contactosco1.add("a.gmail.com");
        contactosco1.add("b.gmail.com");
        contactosLK1.add("img/iconoMas.png");
        contactosLK1.add("img/iconoOpciones.png");
        LinkedList<String> contactost1 = new LinkedList<>();
        contactost1.add("0000000");
        contactost1.add("1111111");
        LinkedList<Contacto> contactosc1 = new LinkedList<>();
        contactosc1.add(c2);;
        Contacto c1 = new Contacto("Pepe", "img/usuarioDefault.png", contactosLK1, contactosco1, contactost1, contactosc1);
        
        
        Utilitaria.saveFile(c1, "Contacto.XML");
        Utilitaria.saveFile(c2, "Contacto.XML");
       
        launch();
    }

}