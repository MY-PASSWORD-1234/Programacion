package com.ejemplo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class QutermaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botonanaranjaE;

    @FXML
    private Button botonazulS;

    @FXML
    private Button botonrojoO;

    @FXML
    private Button botonverdeN;

    @FXML
    private Button volver;

    @FXML
    void mostrarDinoEste(ActionEvent event) {

    }

    @FXML
    void mostrarDinoNorte(ActionEvent event) {

    }

    @FXML
    void mostrarDinoOeste(ActionEvent event) {

    }

    @FXML
    void mostrarDinoSud(ActionEvent event) {

    }

    @FXML
    void vol(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    void initialize() {
        assert botonanaranjaE != null : "fx:id=\"botonanaranjaE\" was not injected: check your FXML file 'qutermary.fxml'.";
        assert botonazulS != null : "fx:id=\"botonazulS\" was not injected: check your FXML file 'qutermary.fxml'.";
        assert botonrojoO != null : "fx:id=\"botonrojoO\" was not injected: check your FXML file 'qutermary.fxml'.";
        assert botonverdeN != null : "fx:id=\"botonverdeN\" was not injected: check your FXML file 'qutermary.fxml'.";
        assert volver != null : "fx:id=\"volver\" was not injected: check your FXML file 'qutermary.fxml'.";

    }

}


