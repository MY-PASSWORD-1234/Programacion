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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class CO_InicioSesion {
    static Cliente cls = null;
    static Connection con;
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
          
            PreparedStatement st = con.prepareStatement("SELECT Nombre, Contraseña,Dni from SuperCoches.Usuarios");
            ResultSet rs = st.executeQuery();

            boolean usuarioValido = false;
            Boolean salir = false;
            while (rs.next() || salir) {
                String nom = rs.getString("Nombre");
                String contrasenia = rs.getString("Contraseña");
                String dni = rs.getString("Dni");

                if (nombre.equals("admin") && contr.equals("admin")) {
                    App.setRoot("AdminPanel");
                    salir = true;

                } else if ((nombre.equals(nom) && contr.equals(contrasenia))) {
                    usuarioValido = true;
                    cls = new Cliente(nombre, contrasenia, dni);
                }
            }

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
                insertarNombre.setText("");

            }

           
        } catch (SQLException e) {
         
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

   

