package com.ejemplo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField contraseña;

    @FXML
    private TextField usuario;

    @FXML
    private Button validar;

    @FXML
    void probarusuario(ActionEvent event) {
        String us = usuario.getText();
        String contr = contraseña.getText();
        String linea;
        String[] lineaspl;
        boolean existe = false;
        try {
            File f = new File("src/main/java/com/ejemplo/contrasenas.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while ((linea = br.readLine()) != null) {
                lineaspl = linea.split(":");
                if (us.equals(lineaspl[0]) && contr.equals(lineaspl[1])) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("ACCESO CONCEDIDO");
                    alert.showAndWait();
                    existe = true;

                }

            }
            if (!existe) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Formato incorrecto"); //Texto del error
                alert.showAndWait();
            }

          
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
       
    

    @FXML
    void initialize() {
        assert contraseña != null : "fx:id=\"contraseña\" was not injected: check your FXML file 'primary.fxml'.";
        assert usuario != null : "fx:id=\"usuario\" was not injected: check your FXML file 'primary.fxml'.";
        assert validar != null : "fx:id=\"validar\" was not injected: check your FXML file 'primary.fxml'.";

    }

}
