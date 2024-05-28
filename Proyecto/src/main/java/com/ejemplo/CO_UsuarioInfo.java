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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CO_UsuarioInfo {
    private static Connection con;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Recibo, Integer> cambionomvreTable;

    @FXML
    private TableColumn<Recibo, String> cocheTable;

    @FXML
    private TextField dni;

    @FXML
    private TableColumn<Recibo, String> fechaTable;

    @FXML
    private TableColumn<Recibo, Integer> ivaTable;

    @FXML
    private TextField nombre;

    @FXML
    private TableColumn<Recibo, Integer> preciobaseTable;

    @FXML
    private TableColumn<Recibo, Integer> preciofinalTable;

    @FXML
    private TableView<Recibo> tablaEntera;

    @FXML
    void volver(ActionEvent event) throws IOException {
        App.setRoot("BuscadorCoches");
        App.scene.getWindow().setWidth(1121);
        App.scene.getWindow().setHeight(560);
    }

    @FXML
    void initialize() {
        con = CO_InicioSesion.getCon();
        nombre.setText(CO_InicioSesion.cls.getNombre());
        dni.setText(CO_InicioSesion.cls.getDni());
        cambionomvreTable.setCellValueFactory(new PropertyValueFactory<>("cambio_nombre"));
        fechaTable.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ivaTable.setCellValueFactory(new PropertyValueFactory<>("iva"));
        preciobaseTable.setCellValueFactory(new PropertyValueFactory<>("precio_base"));
        preciofinalTable.setCellValueFactory(new PropertyValueFactory<>("precio_final"));
        cocheTable.setCellValueFactory(new PropertyValueFactory<>("coche"));
        try {
            ObservableList<Recibo> recibos = FXCollections.observableArrayList();
            PreparedStatement st = con.prepareStatement(
                    "SELECT concat(c.Marca ,' ',c.Modelo) AS Coche,  r.Precio_Base, r.Iva, r.Cambio_Nombre, r.Precio_Final, r.Fecha FROM Coches c JOIN Recibo r ON c.idCoches = r.idCoche where r.idUsuario = ?");
            st.setInt(1, CO_InicioSesion.cls.getId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String coche = rs.getString("Coche");
                int precioB = rs.getInt("r.Precio_Base");
                int iva = rs.getInt("r.Iva");
                int precioF = rs.getInt("r.Precio_Final");
                int nombre = rs.getInt("r.Cambio_Nombre");
                String fecha = rs.getString("r.Fecha");
                Recibo rcb = new Recibo(coche, precioB, iva, nombre, precioF, fecha);
                recibos.add(rcb);

            }
            tablaEntera.setItems(recibos);
        } catch (Exception e) {

        }
    }

}
