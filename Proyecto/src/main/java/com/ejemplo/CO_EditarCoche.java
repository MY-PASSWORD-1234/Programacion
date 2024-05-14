package com.ejemplo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class CO_EditarCoche {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> CVTabla;

    @FXML
    private TableColumn<?, ?> anioTable;

    @FXML
    private TableColumn<?, ?> combustibleTabla;

    @FXML
    private TableColumn<?, ?> estadoTabla11;

    @FXML
    private TableColumn<?, ?> kilomTabla;

    @FXML
    private TableColumn<?, ?> marcaTabla;

    @FXML
    private TableColumn<?, ?> modeloTabla;

    @FXML
    private TableColumn<?, ?> precioTabla;

    @FXML
    private TableColumn<?, ?> puertaTabla;

    @FXML
    private TableView<?> tablaEntera;


    @FXML
    void mostrarDatos(ActionEvent event) {

    }

    @FXML
    void volverPanel(ActionEvent event) throws IOException {
        App.setRoot("AdminPanel");
    }

    @FXML
    void initialize() {
        
        
    }

}