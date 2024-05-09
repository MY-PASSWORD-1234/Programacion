package com.ejemplo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InicioSesion {

    static Cliente cls = null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField PonerContrasenia;

    @FXML
    private TextField PonerDni;

    @FXML
    void IniciarSesion(ActionEvent event) throws IOException {
        String nombre = PonerDni.getText();
        String contr = PonerContrasenia.getText();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/CajeroNOVA", "root", "root");
            PreparedStatement st = con.prepareStatement("SELECT * from CajeroNOVA.Cliente");
            ResultSet rs = st.executeQuery();

            boolean usuarioValido = false;
            while (rs.next()) {
                String nif = rs.getString("NIF");
                String contrasenia = rs.getString("clave");
                String nombres = rs.getString("nombre");
                String apll = rs.getString("apellidos");
                int mov = rs.getInt("movil");

                if ((nombre.equals(nif) && contr.equals(contrasenia))) {
                    usuarioValido = true;
                    cls = new Cliente(nif, contrasenia, nombres, apll, mov);
                }
            }

            if (usuarioValido) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Inicio");
                alert.setHeaderText("");
                alert.setContentText("Has iniciado con exito. Disfrute");
                alert.showAndWait();
                App.setRoot("Aplicacion");

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Eror");
                alert.setContentText(
                        "DNI/NIF o Contraseña no son correctos");
                alert.showAndWait();
                PonerContrasenia.setText("");
                PonerDni.setText("");

            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }
}
