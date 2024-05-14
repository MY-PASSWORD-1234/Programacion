package com.ejemplo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CO_AdminPanel {

    @FXML
    private ResourceBundle resources;
    @FXML
    private TableColumn<?, ?> perfilesContrasena;

    @FXML
    private TableColumn<?, ?> perfilesDni;

    @FXML
    private TableColumn<?, ?> perfilesNombre;

    @FXML
    private TableView<?> perfilesUsuarios;
    @FXML
    private URL location;

    @FXML
    void cerrarSesion(ActionEvent event) throws IOException {
        App.setRoot("InicioSesion");
    }

    @FXML
    void crearNuevoCoche(ActionEvent event) throws IOException {
        App.setRoot("CrearCoche");
    }

    @FXML
    void editarCochesExistentes(ActionEvent event) {

    }

    @FXML
    void mostrarPerfilesUsuarios(ActionEvent event) {
        if  (perfilesUsuarios.getOpacity() == 1){
            perfilesUsuarios.setOpacity(0);

        }else{

            perfilesUsuarios.setOpacity(1.0);
        }
       
    }

    @FXML
    void initialize() {
        perfilesUsuarios.setOpacity(0);
    }
}
