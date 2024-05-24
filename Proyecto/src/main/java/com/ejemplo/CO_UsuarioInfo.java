package com.ejemplo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CO_UsuarioInfo {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> cambionomvreTable;

    @FXML
    private TableColumn<?, ?> cocheTable;

    @FXML
    private TextField dni;

    @FXML
    private TableColumn<?, ?> fechaTable;

    @FXML
    private TableColumn<?, ?> ivaTable;

    @FXML
    private TextField nombre;

    @FXML
    private TableColumn<?, ?> preciobaseTable;

    @FXML
    private TableColumn<?, ?> preciofinalTable;

    @FXML
    private TableView<?> tablaEntera;

    @FXML
    void volver(ActionEvent event) {

    }

    @FXML
    void initialize() {
      
    }

}
