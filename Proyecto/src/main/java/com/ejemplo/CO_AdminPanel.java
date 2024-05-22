package com.ejemplo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CO_AdminPanel {
    private Connection con;
    UsuariosPanel us = null;
    @FXML
    private ResourceBundle resources;
    @FXML
    private TableColumn<UsuariosPanel, String> perfilesCoches;

    @FXML
    private TableColumn<UsuariosPanel, String> perfilesDni;

    @FXML
    private TableColumn<UsuariosPanel, String> perfilesNombre;

    @FXML
    private TableView<UsuariosPanel> perfilesUsuarios;
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
    void editarCochesExistentes(ActionEvent event) throws IOException {
        App.setRoot("EditarCoche");
    }

    @FXML
    void mostrarPerfilesUsuarios(ActionEvent event) {
          ObservableList<UsuariosPanel> usus = FXCollections.observableArrayList();
        if (perfilesUsuarios.getOpacity() == 1) {
            perfilesUsuarios.setOpacity(0);

        } else {

            perfilesUsuarios.setOpacity(1.0);
        }
        try {
            PreparedStatement st = con.prepareStatement(
                    "SELECT u.Nombre, u.Dni, (SELECT GROUP_CONCAT(CONCAT(c.Marca, ' ', c.Modelo) SEPARATOR ', ') FROM Coches c WHERE c.idUsuario = u.idUsuario) AS Coches FROM Usuarios u;");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("Nombre");
                String dni = rs.getString("Dni");
                String coches = rs.getString("Coches");
                us = new UsuariosPanel(nombre, dni, coches);
                usus.add(us);

            }
            perfilesUsuarios.setItems(usus);
        } catch (Exception e) {
        }

    }

    @FXML
    void initialize() {
        con = CO_InicioSesion.getCon();
        perfilesUsuarios.setOpacity(0);
         perfilesDni.setCellValueFactory(new PropertyValueFactory<UsuariosPanel, String>("Dni"));
        perfilesNombre.setCellValueFactory(new PropertyValueFactory<UsuariosPanel, String>("usuario"));
        perfilesCoches.setCellValueFactory(new PropertyValueFactory<UsuariosPanel, String>("Coches"));
    }
}
