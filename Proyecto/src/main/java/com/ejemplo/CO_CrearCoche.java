package com.ejemplo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class CO_CrearCoche {
    private Connection con;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField anio;

    @FXML
    private TextField combustible;

    @FXML
    private Button crearcoche;

    @FXML
    private TextField cv;

    @FXML
    private TextArea descripcion;

    @FXML
    private TextField kilometros;

    @FXML
    private Button limpiarCampos;

    @FXML
    private TextField marca;

    @FXML
    private TextField modelo;

    @FXML
    private ImageView ponerImagen;

    @FXML
    private TextField precio;

    @FXML
    private TextField puertas;

    @FXML
    void seleccionarFoto(ActionEvent event) {

    }

    @FXML
    void crearCoche(ActionEvent event) {
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    void limpiarCampos(ActionEvent event) {

    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        App.setRoot("AdminPanel");
    }

    @FXML
    void initialize() {
        con = CO_InicioSesion.getCon();

    }

}
