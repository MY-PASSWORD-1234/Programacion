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

public class CO_Registrar {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField insertarContrasenia;

    @FXML
    private TextField insertarDni;

    @FXML
    private TextField insertarNombre;

    @FXML
void registrar(ActionEvent event) {
    String nombre = insertarNombre.getText();
    String contr = insertarContrasenia.getText();
    String dni = insertarDni.getText();
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/SuperCoches", "root", "root");
        PreparedStatement st = con.prepareStatement("SELECT Dni from SuperCoches.Usuarios");
        ResultSet rs = st.executeQuery();
        
        boolean dniExistente = false;
        
        while (rs.next()) {
            String dnie = rs.getString("Dni");
            if (dni.equals(dnie)) {
                dniExistente = true;
                break;
            }
        }
        
        if (dniExistente) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Ese Dni ya está en uso en alguno de nuestros usuarios");
            alert.showAndWait();
            insertarContrasenia.setText("");
            insertarNombre.setText("");
            insertarDni.setText("");
        } else {
            st = con.prepareStatement("INSERT INTO SuperCoches.Usuarios (Nombre, Contraseña, Dni) Values(?,?,?)");
            st.setString(1, nombre);
            st.setString(2, contr);
            st.setString(3, dni);
            int r  = st.executeUpdate();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Registro");
            alert.setHeaderText("");
            alert.setContentText("Te has registrado correctamente. Ahora puedes iniciar sesión.");
            alert.showAndWait();
            
            insertarContrasenia.setText("");
            insertarNombre.setText("");
            insertarDni.setText("");
        }
        
        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    @FXML
    void voler(ActionEvent event) throws IOException {
        App.setRoot("InicioSesion");
    }

    @FXML
    void initialize() {
       

    }

}


