package com.ejemplo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PrimaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Apellidos;

    @FXML
    private TextField Cargo;

    @FXML
    private TextField FechaNacimiento;

    @FXML
    private TextField Idempleado;

    @FXML
    private TextField Nombre;

    @FXML
    private TextField Telefono;

    @FXML
    private Button irAlInicio;

    @FXML
    private Button irAlRegAnterior;

    @FXML
    private Button irAnteriorReg;

    @FXML
    private Button irFInalReg;

    @FXML
    private AnchorPane irSiguienteReg;

    @FXML
    void BotonAnteriorReg(ActionEvent event) {

    }

    @FXML
    void BotonAnteriroReg(ActionEvent event) {

    }

    @FXML
    void BotonFinalReg(ActionEvent event) {

    }

    @FXML
    void BotonSiguienteReg(MouseEvent event) {

    }

    @FXML
    void botonInicio(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert Apellidos != null : "fx:id=\"Apellidos\" was not injected: check your FXML file 'primary.fxml'.";
        assert Cargo != null : "fx:id=\"Cargo\" was not injected: check your FXML file 'primary.fxml'.";
        assert FechaNacimiento != null : "fx:id=\"FechaNacimiento\" was not injected: check your FXML file 'primary.fxml'.";
        assert Idempleado != null : "fx:id=\"Idempleado\" was not injected: check your FXML file 'primary.fxml'.";
        assert Nombre != null : "fx:id=\"Nombre\" was not injected: check your FXML file 'primary.fxml'.";
        assert Telefono != null : "fx:id=\"Telefono\" was not injected: check your FXML file 'primary.fxml'.";
        assert irAlInicio != null : "fx:id=\"irAlInicio\" was not injected: check your FXML file 'primary.fxml'.";
        assert irAlRegAnterior != null : "fx:id=\"irAlRegAnterior\" was not injected: check your FXML file 'primary.fxml'.";
        assert irAnteriorReg != null : "fx:id=\"irAnteriorReg\" was not injected: check your FXML file 'primary.fxml'.";
        assert irFInalReg != null : "fx:id=\"irFInalReg\" was not injected: check your FXML file 'primary.fxml'.";
        assert irSiguienteReg != null : "fx:id=\"irSiguienteReg\" was not injected: check your FXML file 'primary.fxml'.";

    }

}
