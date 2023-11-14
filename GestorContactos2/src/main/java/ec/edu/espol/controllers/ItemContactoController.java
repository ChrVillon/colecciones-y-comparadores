package ec.edu.espol.controllers;

import ec.edu.espol.gestorcontactos.App;
import ec.edu.espol.model.Contacto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemContactoController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private Label nombre;
    
    private Contacto contacto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    void setData(Contacto c) {
    contacto = c;
    try {
        Image image = new Image(c.getPerfil());
        imageView.setImage(image);
    } catch (Exception e) {
        // Manejar la excepción, por ejemplo, imprimir un mensaje de error
        System.err.println("Error al cargar la imagen: " + e.getMessage());
    }
    nombre.setText(c.getNombre());
}

    @FXML
    private void infoContacto(MouseEvent event) {
        try {
            System.out.println(this.nombre);
            FXMLLoader loader;
            loader = App.loadFXML("infoContacto");
            Scene sc = new Scene(loader.load());
            InfoContactoController icc = loader.getController();
            icc.setContacto(this.contacto);
            App.setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
