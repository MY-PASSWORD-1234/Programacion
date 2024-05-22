package com.ejemplo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.crypto.SecretKey;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class CO_InicioSesion {
    static Cliente cls = null;
    static Connection con;
    int intentos = 0;
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
    void IniciarSesion(ActionEvent event) throws Exception {
        String nombre = insertarNombre.getText();
        String contr = insertarContrasenia.getText();
        if (contr.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Contraseña Vacía");
            alert.setContentText("Debe introducir una contraseña para iniciar sesión.");
            alert.showAndWait();
            return;
        }

        String contraseñaenciptada = EncriptarDesencriptar.encriptar(contr);

        try {
            PreparedStatement st = con
                    .prepareStatement("SELECT idUsuario, Nombre, Contraseña, Dni FROM SuperCoches.Usuarios");
            ResultSet rs = st.executeQuery();

            boolean usuarioValido = false;
            Boolean salir = false;
            Boolean adminvalido = false;
            while (rs.next() && !salir) {
                String nom = rs.getString("Nombre");
                String contrasenia = rs.getString("Contraseña");
                String dni = rs.getString("Dni");
                int id = rs.getInt("idUsuario");

                if (nombre.equals("admin") && contr.equals("admin")) {
                    App.setRoot("AdminPanel");
                    salir = true;
                    adminvalido = true;
                } else if ((nombre.equals(nom) && contraseñaenciptada.equals(contrasenia))) {
                    usuarioValido = true;
                    cls = new Cliente(id, nombre, contrasenia, dni);
                }
            }
            if (adminvalido) {

            } else {
                if (usuarioValido) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Inicio");
                    alert.setHeaderText("");
                    alert.setContentText("Has iniciado con éxito. ¡Disfrute!");
                    alert.showAndWait();
                    App.setRoot("BuscadorCoches");
                    App.scene.getWindow().setWidth(1121);
                    App.scene.getWindow().setHeight(560);
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("");
                    alert.setContentText(
                            "Usuario o Contraseña no válidos. Vuelva a intentarlo o cree una cuenta en Registrar");
                    alert.showAndWait();
                    insertarContrasenia.setText("");
                    intentos++;

                    if (intentos >= 3) {
                        alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Cambio Contraseña");
                        alert.setHeaderText("¿Has olvidado la contraseña?");
                        alert.setContentText("¿Quieres Cambiar La Contraseña?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            TextInputDialog dialog = new TextInputDialog();
                            dialog.setTitle("¿Olvidaste Tu Contraseña?");
                            dialog.setHeaderText("");
                            dialog.setContentText("Introduce Tu Dni:");
                            Optional<String> results = dialog.showAndWait();
                            if (results.isPresent() && !results.get().trim().isEmpty()) {
                                dialog = new TextInputDialog();
                                dialog.setTitle("¿Olvidaste Tu Contraseña?");
                                dialog.setHeaderText("");
                                dialog.setContentText("Introduce Tu Contraseña Nueva:");
                                Optional<String> resultse = dialog.showAndWait();
                                if (resultse.isPresent()) {
                                    String encriptada = EncriptarDesencriptar.encriptar(resultse.get());
                                    if (encriptada != null) {
                                        try {
                                            st = con.prepareStatement(
                                                    "UPDATE SuperCoches.Usuarios SET Contraseña = ? WHERE Dni = ?");
                                            st.setString(1, encriptada);
                                            st.setString(2, results.get());
                                            int rowsUpdated = st.executeUpdate();
                                            if (rowsUpdated > 0) {
                                                alert = new Alert(AlertType.INFORMATION);
                                                alert.setTitle("Cambio Contraseña");
                                                alert.setHeaderText("Éxito");
                                                alert.setContentText(
                                                        "Se ha actualizado tu contraseña. Prueba con la nueva contraseña.");
                                                alert.showAndWait();
                                                intentos = 0;
                                            } else {
                                                alert = new Alert(AlertType.WARNING);
                                                alert.setTitle("Cuidado");
                                                alert.setHeaderText("");
                                                alert.setContentText("No se encontró ningún usuario con ese DNI");
                                                alert.showAndWait();
                                            }
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            } else {
                                alert = new Alert(AlertType.WARNING);
                                alert.setTitle("Advertencia");
                                alert.setHeaderText("DNI no proporcionado");
                                alert.setContentText(
                                        "Debe introducir un DNI para continuar con el cambio de contraseña.");
                                alert.showAndWait();
                            }
                        }
                    }
                }
            }
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

        try {
            con = crearConexion("33006", "SuperCoches", "root", "root");
        } catch (Exception e) {
            try {
                con.close();
            } catch (Exception e1) {
                System.out.println("[!]Error: " + e1.getMessage());
            }
            System.out.println("[!]Error: " + e.getMessage());
        }

    }

    public static Connection crearConexion(String puerto, String baseDatos, String usuario, String passwd) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:" + puerto + "/" + baseDatos, usuario,
                    passwd);
            return con;
        } catch (Exception e) {
            return null;
        }
    }

    public static Connection getCon() {
        return con;
    }

}
