module ec.edu.espol.gestorcontactos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.gestorcontactos to javafx.fxml;
    exports ec.edu.espol.gestorcontactos;
    /*opens ec.edu.espol.model to javafx.fxml;
    exports ec.edu.espol.model;
*/    
opens ec.edu.espol.controllers to javafx.fxml;
    exports ec.edu.espol.controllers;
}
