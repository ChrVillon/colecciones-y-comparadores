package ec.edu.espol.controllers;

import ec.edu.espol.gestorcontactos.App;
import ec.edu.espol.model.Contacto;
import ec.edu.espol.model.Empresa;
import ec.edu.espol.model.Persona;
import ec.edu.espol.model.Utilitaria;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class NuevoContactoController implements Initializable {

    @FXML
    private VBox campos;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField nombre;
    @FXML
    private TextField telefono;
    @FXML
    private TextField correo;
    @FXML
    private ComboBox<String> cbox;
    @FXML
    private VBox panelTelf;
    @FXML
    private VBox panelCorr;
    @FXML
    private VBox panelFotos;
    //atributos de Persona
    @FXML
    private TextField apodo;
    @FXML
    private TextField apellido;
    //atributos de una Empresa
    @FXML
    private TextField departamento;
    @FXML
    private TextField sitioWeb;
    
    String perfil = "img/usuarioDefault.png";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageView.setImage(new Image(perfil));
        imageView.setFitWidth(100); imageView.setFitHeight(100);
        cbox.getItems().addAll("Empresa", "Persona");
        cbox.setOnAction(this::cboxChanged);
        campos.getChildren().clear();
        
    }

    private void cboxChanged(ActionEvent event) {
        String selectedItem = cbox.getSelectionModel().getSelectedItem();

        // Limpiar el VBox específico para campos de persona o empresa
        campos.getChildren().clear();

        // Mostrar los campos según el tipo seleccionado en el ComboBox
        if ("Persona".equals(selectedItem)) {
            campos.getChildren().addAll(apellido, apodo);
            VBox.setMargin(apellido, new Insets(5, 0, 5, 0));
            VBox.setMargin(apodo, new Insets(0, 0, 5, 0));
        } else if ("Empresa".equals(selectedItem)) {
            campos.getChildren().addAll(departamento, sitioWeb);
            VBox.setMargin(departamento, new Insets(5, 0, 5, 0));
            VBox.setMargin(sitioWeb, new Insets(0, 0, 5, 0));
        }
    }
    @FXML
    private void guardar(MouseEvent event) {
        String nomb = nombre.getText();
        String telf = telefono.getText();
        String corr = correo.getText();
        LinkedList<String> correos = new LinkedList<>();
        correos.add(corr);
        LinkedList<String> telefonos = new LinkedList<>();
        telefonos.add(telf);
        String selectedItem = cbox.getSelectionModel().getSelectedItem();
        if ("Persona".equals(selectedItem)) {
            String apod = apodo.getText();
            String apell = apellido.getText();
            //ejempplo para guardar un contacto
            //los null son el perfil, lsita de fotos y lista de cpntactos asociados
            Contacto c = new Persona(apod, apell, nomb, perfil, null, correos, telefonos, null);
            Utilitaria.saveFile(c, "Contacto.XML");
            cancelar(event);
        } else if ("Empresa".equals(selectedItem)) {
            String dept = departamento.getText();
            String sitW = sitioWeb.getText();
            //ejempplo para guardar un contacto
            //los null son el perfil, lsita de fotos y lista de cpntactos asociados            
            Contacto c = new Empresa(dept, sitW, nomb, null, null, correos, telefonos, null);
            Utilitaria.saveFile(c, "Contacto.XML");
            cancelar(event);            
        } 
        if (nombre.getText().equals("") || telefono.getText().equals("")) {
            mostrarAlerta("Nombre y Telefono1 son obligatorios");
            return; // Sale del método si la validación falla
        } else{ 
            cancelar(event);
        }        
        cancelar(event); 
    }


    @FXML
    private void insertarImagen(MouseEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG Image","*.jpg"), new FileChooser.ExtensionFilter("PNG Image", "*.png"), new FileChooser.ExtensionFilter("All image files","*.jpg","*.png"));
        File imgFile = fc.showOpenDialog(App.getStage());
        if (imgFile != null) {
            perfil = "File:"+imgFile.getPath();
            imageView.setImage(new Image(perfil));
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
        }
    }

    @FXML
    private void cancelar(MouseEvent event) {
        try {
            FXMLLoader loader;
            loader = App.loadFXML("listContacto");
            Scene sc = new Scene(loader.load());
            App.setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    @FXML
    private void otroTelf(MouseEvent event) {
        TextField t = new TextField();
        int num = panelTelf.getChildren().size();
        t.setPromptText("Telefono " + (num+1));
        panelTelf.getChildren().add(t);
    }

    @FXML
    private void otroCorr(MouseEvent event) {
        TextField c = new TextField();
        int num = panelCorr.getChildren().size();
        c.setPromptText("Correo " + (num+1));
        panelCorr.getChildren().add(c);
    }

    @FXML
    private void subirGaleria(MouseEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG Image","*.jpg"), new FileChooser.ExtensionFilter("PNG Image", "*.png"), new FileChooser.ExtensionFilter("All image files","*.jpg","*.png"));
        File imgFile = fc.showOpenDialog(App.getStage());
        if (imgFile != null) {
            String dir = "File:"+imgFile.getPath();
            Label lb = new Label();
            lb.setText(dir);
            panelFotos.getChildren().add(lb);
        }
    }
}
