package com.ejemplo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Crearatracion;

    @FXML
    private Button listardino;

    @FXML
    private Button listarzona;

    @FXML
    private Button salir;

    @FXML
    void CrearAtraccion(ActionEvent event) throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    void ListarDino(ActionEvent event) throws IOException {
        App.setRoot("terciary");
    }

    @FXML
    void ListarZonas(ActionEvent event) throws IOException {
        App.setRoot("qutermary");
    }

    @FXML
    void Salir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void initialize() {
        assert Crearatracion != null : "fx:id=\"Crearatracion\" was not injected: check your FXML file 'primary.fxml'.";
        assert listardino != null : "fx:id=\"listardino\" was not injected: check your FXML file 'primary.fxml'.";
        assert listarzona != null : "fx:id=\"listarzona\" was not injected: check your FXML file 'primary.fxml'.";
        assert salir != null : "fx:id=\"salir\" was not injected: check your FXML file 'primary.fxml'.";

    }

}
