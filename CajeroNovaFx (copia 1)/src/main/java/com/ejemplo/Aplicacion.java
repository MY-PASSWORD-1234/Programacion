package com.ejemplo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;

public class Aplicacion {

    String nombrec = "";
    @FXML
    private Button pagar;

    @FXML
    private Button sacar;

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

            App.setRoot("InicioSesion");
            InicioSesion.cls = null;
        } else {

        }

    }

    @FXML
    void MostrarCuentas(MouseEvent event) {

        ArrayList<String> todas = new ArrayList<>();
        try {

            sacar.setDisable(false);
            pagar.setDisable(false);
            String nif = "";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/CajeroNOVA", "root", "root");
            PreparedStatement st = con.prepareStatement("SELECT num_cta from CajeroNOVA.Cuenta where NIF = ?");

            nif = InicioSesion.cls.getNif();

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
        List<String> facs = new ArrayList<>();
        try {
            String saldo = "";
            String fac = "";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/CajeroNOVA", "root", "root");
            PreparedStatement st = con.prepareStatement("SELECT saldo FROM CajeroNOVA.Cuenta WHERE num_cta = ?");
            st.setString(1, cuentas.getSelectionModel().getSelectedItem());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                saldo = rs.getString("saldo");

            }
            st = con.prepareStatement("SELECT num_fra FROM CajeroNOVA.Factura WHERE NIF = ?");
            st.setString(1, InicioSesion.cls.getNif());
            rs = st.executeQuery();
            while (rs.next()) {
                fac = rs.getString("num_fra");
                facs.add(fac);
            }

            ChoiceDialog<String> dialog = new ChoiceDialog<>("", facs);
            dialog.setTitle("Seleccionar factura");
            dialog.setHeaderText("Saldo disponible: " + saldo);
            dialog.setContentText("Eliga una factura:");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                try {
                    st = con.prepareStatement("SELECT importe FROM CajeroNOVA.Factura WHERE num_fra = ?");
                    st.setString(1, dialog.getSelectedItem());
                    rs = st.executeQuery();
                    if (rs.next()) {
                        String impo = rs.getString("importe");

                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Pagar Factura");
                        alert.setHeaderText("Dinero:" + saldo + " Numero Fac:" + dialog.getSelectedItem()
                                + "\\n Importe factura: " + impo);
                        alert.setContentText("¿Deseas Pagarla?");
                        Optional<ButtonType> results = alert.showAndWait();

                        if (result.isPresent() && results.get() == ButtonType.OK) {
                            Double importe = Double.parseDouble(impo);

                            if (importe > Double.parseDouble(saldo)) {
                                Alert alerts = new Alert(AlertType.WARNING);
                                alerts.setTitle("Pagar Factura");
                                alerts.setHeaderText("Advertencia");
                                alerts.setContentText("No tienes saldo suficiciente saldo para pagarla ");
                                alerts.showAndWait();
                            } else {
                                Double saldoFInal = Double.parseDouble(saldo) - importe;
                                Alert alertes = new Alert(AlertType.INFORMATION);
                                alertes.setTitle("Aceptada");
                                alertes.setHeaderText("OPERACION REALIZADA \n" + "Su saldo actual es: " + saldoFInal);
                                alertes.setContentText("");
                                alertes.showAndWait();
                                con = DriverManager.getConnection("jdbc:mysql://localhost:33006/CajeroNOVA", "root",
                                        "root");
                                st = con.prepareStatement("UPDATE CajeroNOVA.Cuenta set saldo= ? where num_cta =?");
                                st.setString(2, cuentas.getSelectionModel().getSelectedItem());
                                st.setDouble(1, saldoFInal);
                                int s = st.executeUpdate();
                                st = con.prepareStatement("DElETE FROM CajeroNOVA.Factura where num_fra = ?");
                                st.setString(1, dialog.getSelectedItem());
                                s = st.executeUpdate();

                            }

                        }
                    }

                } catch (Exception e) {

                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    void SacarDinero(ActionEvent event) {

        try {
            String saldo = "";
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/CajeroNOVA", "root", "root");
            PreparedStatement st = con.prepareStatement("SELECT saldo FROM CajeroNOVA.Cuenta WHERE num_cta = ?");
            st.setString(1, cuentas.getSelectionModel().getSelectedItem());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                saldo = rs.getString("saldo");

                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Sacar dinero");
                dialog.setHeaderText("Saldo Disponible: " + saldo);
                dialog.setContentText("Introduce el dinero:");
                Optional<String> result = dialog.showAndWait();

                if (result.isPresent()) {
                    try {

                        saldo = rs.getString("saldo");
                        Double cantidad = Double.parseDouble(result.get());

                        if (cantidad > Double.parseDouble(saldo)) {

                            Alert alert = new Alert(AlertType.WARNING);
                            alert.setTitle("Sacar Dinero");
                            alert.setHeaderText("Advertencia");
                            alert.setContentText("No tienes saldo suficiciente \n" + "Solo puedes retirar: " + saldo);
                            alert.showAndWait();
                        } else {
                            if (cantidad > 1000) {
                                Alert alert = new Alert(AlertType.WARNING);
                                alert.setTitle("Sacar Dinero");
                                alert.setHeaderText("Advertencia");
                                alert.setContentText("No puedes sacar mas de 1000€");
                                alert.showAndWait();
                            } else {
                                Double saldoFInal = Double.parseDouble(saldo) - cantidad;

                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Aceptada");
                                alert.setHeaderText("OPERACION REALIZADA \n" + "Su saldo actual es: " + saldoFInal);
                                alert.setContentText("");
                                alert.showAndWait();
                                con = DriverManager.getConnection("jdbc:mysql://localhost:33006/CajeroNOVA", "root",
                                        "root");
                                st = con.prepareStatement("UPDATE CajeroNOVA.Cuenta set saldo= ? where num_cta =?");
                                st.setString(2, cuentas.getSelectionModel().getSelectedItem());
                                st.setDouble(1, saldoFInal);
                                int s = st.executeUpdate();
                            }
                        }

                    } catch (Exception e) {
                        Alert alerts = new Alert(AlertType.ERROR);
                        alerts.setTitle("Error");
                        alerts.setHeaderText("El Formato No Es Correcto ");
                        alerts.setContentText("Debe de ser numero solo '100' por ejemplo");
                        alerts.showAndWait();
                    }
                } else {

                }
            }

        } catch (SQLException e) {

        }

    }

    @FXML
    void initialize() {
        String nombrec = "";
        nombrec = InicioSesion.cls.getNombre() + " " + InicioSesion.cls.getApellidos();
        NombreCompleto.setText(nombrec);
        sacar.setDisable(true);
        pagar.setDisable(true);

    }

}
