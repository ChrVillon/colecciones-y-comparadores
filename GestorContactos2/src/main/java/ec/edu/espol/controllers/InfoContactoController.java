package ec.edu.espol.controllers;

import ec.edu.espol.gestorcontactos.App;
import ec.edu.espol.model.Contacto;
import ec.edu.espol.model.Utilitaria;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class InfoContactoController implements Initializable {

    @FXML
    private Label tipoContacto;
    @FXML
    private ImageView imageView;
    @FXML
    private Label nombre;
    @FXML
    private Button ant;
    @FXML
    private Button sgte;
    @FXML
    private VBox contactos;
    @FXML
    private VBox telefonos;
    @FXML
    private VBox correos;
    @FXML
    private VBox fotos;
    @FXML
    private Button antFoto;
    @FXML
    private ImageView imvFotos;
    @FXML
    private Button sgteFoto;
    
    private ArrayList<Contacto> contactosList;
    private Contacto contacto;
    private int currentPos;
    private int cont=0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contactosList = Utilitaria.readFileContacto("Contacto.XML");
        if(contactosList.size()>1){
            ant.setVisible(true);
            sgte.setVisible(true);
        }        
    }
    
    @FXML
    private void anterior(MouseEvent event) {
        try {
            FXMLLoader loader;
            loader = App.loadFXML("infoContacto");
            Scene sc = new Scene(loader.load());
            InfoContactoController icc = loader.getController();
            currentPos--;
            Contacto c; 
            if(currentPos == -1){
                c=contactosList.get(contactosList.size()-1);
                icc.setContacto(c);
            }else{
                c=contactosList.get(currentPos);
                icc.setContacto(c);
                    
                    }
            App.setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
    
    @FXML
    private void anteriorFoto(MouseEvent event) {
        if(cont == 0)
            cont = contacto.getFotos().size();
        cont--;
        imvFotos.setImage(new Image(contacto.getFotos().get(cont)));
    }

    @FXML
    private void siguiente(MouseEvent event) {
        try {
            FXMLLoader loader;
            loader = App.loadFXML("infoContacto");
            Scene sc = new Scene(loader.load());
            InfoContactoController icc = loader.getController();
            currentPos++;
            if(currentPos == (contactosList.size())){
                icc.setContacto(contactosList.get(0));
            } else {
                icc.setContacto(contactosList.get(currentPos));
            }    
            App.setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
    
    @FXML
    private void siguienteFoto(MouseEvent event) {
        if(cont == contacto.getFotos().size() - 1)
            cont = -1;
        cont++;
        imvFotos.setImage(new Image(contacto.getFotos().get(cont)));
    }

    @FXML
    private void volver(MouseEvent event) {
        try {
            FXMLLoader loader;
            loader = App.loadFXML("listContacto");
            Scene sc = new Scene(loader.load());
            App.setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void eliminar(MouseEvent event) {
    }

    public void setContacto(Contacto c){
        contacto = c;
        if (contacto.getFotos().size()>1){
            antFoto.setVisible(true);
            sgteFoto.setVisible(true);
        }
        //Implementar un metodo para hacer esta busqueda
        for(int i = 0; i < contactosList.size(); i++){
            if(contactosList.get(i).getNombre().equals(contacto.getNombre()))
                currentPos = i;
        }
        
        //Mover a otro metodo showData
        System.out.println(""+currentPos);
        nombre.setText(contacto.getNombre());
        imageView.setImage(new Image(contacto.getPerfil()));
        imvFotos.setImage(new Image(contacto.getFotos().get(cont)));
        
        for(String s:contacto.getTelefonos()){
            Label lb = new Label();
            lb.setText(s);
            telefonos.getChildren().add(lb);
        }
        for(String s:contacto.getCorreos()){
            Label lb2 = new Label();
            lb2.setText(s);
            correos.getChildren().add(lb2);
        }
        for(Contacto ct : contacto.getContactos()){
            Label lb3 = new Label();
            lb3.setText(ct.getNombre());
            contactos.getChildren().add(lb3);
        }
    }
    
}
