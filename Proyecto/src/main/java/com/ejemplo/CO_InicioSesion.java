package com.ejemplo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CO_InicioSesion {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BotonIniciarSesion;

    @FXML
    private TextField insertarNombre;

    @FXML
    private PasswordField insertarContrasenia;

    @FXML
    void IniciarSesion(ActionEvent event) throws IOException {
        String nombre = insertarNombre.getText();
        String contr = insertarContrasenia.getText();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/SuperCoches", "root", "root");
            PreparedStatement st = con.prepareStatement("SELECT Nombre, Contraseña from SuperCoches.Usuarios");
            ResultSet rs = st.executeQuery();

            boolean usuarioValido = false;
            Boolean salir = false;
            while (rs.next() || salir) {
                String nom = rs.getString("Nombre");
                String contrasenia = rs.getString("Contraseña");

                if (nombre.equals("admin") && contr.equals("admin")) {
                    App.setRoot("AdminPanel");
                    salir = true;

                } else if ((nombre.equals(nom) && contr.equals(contrasenia))) {
                    usuarioValido = true;
                }
            }

            if (usuarioValido) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Inicio");
                alert.setHeaderText("");
                alert.setContentText("Has iniciado con éxito. ¡Disfrute!");
                alert.showAndWait();
                App.setRoot("BuscadorCoches");
                resizeStage(1121, 548);

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("");
                alert.setContentText(
                        "Usuario o Contraseña no válidos. Vuelva a intentarlo o cree una cuenta en Registrar");
                alert.showAndWait();
                insertarContrasenia.setText("");
                insertarNombre.setText("");

            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void SalirAplicacion(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void irResgistrarUsuario(ActionEvent event) throws IOException {
        App.setRoot("RegistroCliente");
    }

    @FXML
    void initialize() {

    }

    public void resizeStage(double width, double height) {
        // Obtiene la referencia al Stage desde cualquier nodo en la escena
        Stage stage = (Stage) BotonIniciarSesion.getScene().getWindow();
        stage.setWidth(width);
        stage.setHeight(height);

    }

}
