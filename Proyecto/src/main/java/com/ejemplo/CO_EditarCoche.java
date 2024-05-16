package com.ejemplo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class CO_EditarCoche {
   static Coche cocheSeleccionado2 ;
   private  Connection con;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Coche, String> CVTabla;

    @FXML
    private TableColumn<Coche, String> anioTable;

    @FXML
    private TableColumn<Coche, String> combustibleTabla;

    @FXML
    private TableColumn<Coche, String> estadoTabla11;

    @FXML
    private TableColumn<Coche, String> kilomTabla;

    @FXML
    private TableColumn<Coche, String> marcaTabla;

    @FXML
    private TableColumn<Coche, String> modeloTabla;

    @FXML
    private TableColumn<Coche, String> precioTabla;

    @FXML
    private TableColumn<Coche, String> puertaTabla;

    @FXML
    private TableView<Coche> tablaEntera;

    @FXML
    void mostrarDatos(ActionEvent event) {

    }

    @FXML
    void volverPanel(ActionEvent event) throws IOException {
        App.setRoot("AdminPanel");
    }

    @FXML
    void initialize() {
        CVTabla.setCellValueFactory(new PropertyValueFactory<Coche, String>("CV"));
        anioTable.setCellValueFactory(new PropertyValueFactory<Coche, String>("Año"));
        puertaTabla.setCellValueFactory(new PropertyValueFactory<Coche, String>("Puertas"));
        marcaTabla.setCellValueFactory(new PropertyValueFactory<Coche, String>("Marca"));
        modeloTabla.setCellValueFactory(new PropertyValueFactory<Coche, String>("Modelo"));
        combustibleTabla.setCellValueFactory(new PropertyValueFactory<Coche, String>("Combustible"));
        kilomTabla.setCellValueFactory(new PropertyValueFactory<Coche, String>("Kilometraje"));
        estadoTabla11.setCellValueFactory(new PropertyValueFactory<Coche, String>("Estado"));
        precioTabla.setCellValueFactory(new PropertyValueFactory<Coche, String>("Precio"));

        ObservableList<Coche> coches = FXCollections.observableArrayList();
        try {
            con = CO_InicioSesion.getCon();
            PreparedStatement st = con.prepareStatement("SELECT * from SuperCoches.Coches");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String marca = rs.getString("Marca");
                String modelo = rs.getString("Modelo");
                int puerta = rs.getInt("Puertas");
                String comb = rs.getString("Combustible");
                int precio = rs.getInt("Precio");
                String estado = rs.getString("Estado");
                int kilomet = rs.getInt("Kilometraje");
                int CV = rs.getInt("CV");
                int anio = rs.getInt("Año");

                Coche c = new Coche(marca, modelo, puerta, comb, kilomet, precio, CV, anio, estado);
                coches.add(c);

            }
            tablaEntera.setItems(coches);
        } catch (Exception e) {

        }
        tablaEntera.setRowFactory(tv -> {
            TableRow<Coche> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    cocheSeleccionado2 = row.getItem();
                    try {
                        App.setRoot("EditarInfoCoche");
                        App.scene.getWindow().setWidth(1000);
                        App.scene.getWindow().setHeight(570);
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

}