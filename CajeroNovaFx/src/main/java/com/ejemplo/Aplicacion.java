package com.ejemplo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Aplicacion {
  ArrayList<Cliente> listacls = new ArrayList<>();
    String nombrec= "";
    @FXML
    private ResourceBundle resources;
    @FXML
    private ChoiceBox<String> cuentas;
    @FXML
    private URL location;

    @FXML
    private TextField NombreCompleto;

    @FXML
    void CerrarSesion(ActionEvent event) throws IOException {
       
       
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Diálogo de confirmación");
        alert.setHeaderText("Cerrar Sesion");
        alert.setContentText("¿Seguro que quieres continuar?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            
            for (int i = 0; i < listacls.size(); i++) {
                String nombrec = listacls.get(i).getNombre()+listacls.get(i).getApellidos() ;
                if (NombreCompleto.getText().equals(nombrec)) {
                    listacls.get(i).setIniciado(false);
                }
        }
            App.setRoot("InicioSesion");
        } else {
           
        }

    }

    @FXML
    void MostrarCuentas(MouseEvent event) {
          ArrayList<String> todas = new ArrayList<>();
        try {
            String nif ="";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/CajeroNOVA", "root", "root");
            PreparedStatement st = con.prepareStatement("SELECT num_cta from CajeroNOVA.Cuenta where NIF = ?");
            for (int i = 0; i < listacls.size(); i++) {

                if (listacls.get(i).getIniciado() == true) {
                    nif = listacls.get(i).getNif();
                    nombrec = listacls.get(i).getNombre()+listacls.get(i).getApellidos() ;
                }
        }
            st.setString(1, nif);
            ResultSet ps = st.executeQuery();

            while (ps.next()) {
                String cuenta = ps.getString("num_cta");
                todas.add(cuenta);
            }

            cuentas.setItems(FXCollections.observableArrayList(todas));
        } catch (Exception e) {
            e.getMessage();
        }

    }
    

    @FXML
    void PagarFactura(ActionEvent event) {

    }

    @FXML
    void SacarDinero(ActionEvent event) {

    }

    @FXML
    void initialize() {
        String nombrec = "";
        for (int i = 0; i < listacls.size(); i++) {
            if (listacls.get(i).getIniciado()==true) {
              nombrec= listacls.get(i).getNombre()+listacls.get(i).getApellidos() ;
              nombrec = "pepe";
            }
           
    }
        NombreCompleto.setText(nombrec);
    }

}
