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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class InicioSesion {
     ArrayList<Cliente> listacls = new ArrayList<>();

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
            PreparedStatement st = con.prepareStatement("SELECT NIF, clave from CajeroNOVA.Cliente");
            ResultSet rs = st.executeQuery();

            boolean usuarioValido = false;
            while (rs.next() ) {
                String nif = rs.getString("NIF");
                String contrasenia = rs.getString("clave");

                 if ((nombre.equals(nif) && contr.equals(contrasenia))) {
                    usuarioValido = true;
                }
            }

            if (usuarioValido) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Inicio");
                alert.setHeaderText("");
                alert.setContentText("Has iniciado con exito. Disfrute");
                alert.showAndWait();
                App.setRoot("Aplicacion");
                for (int i = 0; i < listacls.size(); i++) {
                        if (listacls.get(i).getNif().equals(nombre)) {
                            listacls.get(i).setIniciado(true);
                        }
                    
                }

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
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/CajeroNOVA", "root", "root");
            PreparedStatement st = con.prepareStatement("SELECT * from CajeroNOVA.Cliente");
            ResultSet ps = st.executeQuery();
            while (ps.next()) {

                String codigo = ps.getString("NIF");
                String clave = ps.getString("clave");
                String nombre = ps.getString("nombre");
                String apellido = ps.getString("apellidos");
                int num = ps.getInt("movil");
           

                Cliente cls = new Cliente(codigo, clave, nombre, apellido, num);
                listacls.add(cls);
            }

    }catch(SQLException e){
        e.printStackTrace();
    }
}
}



