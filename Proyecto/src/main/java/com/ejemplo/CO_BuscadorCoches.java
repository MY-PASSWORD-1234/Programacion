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
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class CO_BuscadorCoches {
    static Coche cocheSeleccionado =null;
    @FXML
    private TextField mostrarNombre;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox CicuentaKilometros;

    @FXML
    private CheckBox VeinteKilom;

    @FXML
    private Slider barraPrecio;

    @FXML
    private TextField buscador;

    @FXML
    private Label combustible;

    @FXML
    private Label kilometros;

    @FXML
    private CheckBox masKilometros;

    @FXML
    private ChoiceBox<String> mostrarMarca;

    @FXML
    private ChoiceBox<String> mostrarModelo;

    @FXML
    private ChoiceBox<String> opcionesCombustible;

    @FXML
    private Label puertas;
    @FXML
    private TableColumn<Coche, String> combustibleTabla;

    @FXML
    private TableColumn<Coche, String> estadoTabla;

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
    private CheckBox puertas3;

    @FXML
    private CheckBox puertas5;
    @FXML
    private Label precio;
    @FXML
    private Label marca;
    @FXML
    private Label modelo;

    @FXML
    void abrirCocheTabla(MouseEvent event) throws IOException {
    
   
    }

    @FXML
    void mostrarDatos(ActionEvent event) {

    }

    @FXML
    void buscarLupa(ActionEvent event) {
        ObservableList<Coche> coches = FXCollections.observableArrayList();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/SuperCoches", "root", "root");

            StringBuilder sql = new StringBuilder(
                    "SELECT Marca, Modelo, Puertas, Combustible, Kilometraje, Precio, Estado FROM SuperCoches.Coches WHERE 1=1");
            List<Object> parameters = new ArrayList<>();

            if (puertas3.isSelected() || puertas5.isSelected()) {
                sql.append(" AND Puertas = ?");
                parameters.add(puertas3.isSelected() ? 3 : 5);
            }

            String combustible = opcionesCombustible.getSelectionModel().getSelectedItem();
            if (combustible != null && !combustible.isEmpty()) {
                sql.append(" AND Combustible = ?");
                parameters.add(combustible);
            }

            if (VeinteKilom.isSelected() || CicuentaKilometros.isSelected() || masKilometros.isSelected()) {
                sql.append(" AND (");
                boolean isFirst = true;
                if (VeinteKilom.isSelected()) {
                    sql.append(isFirst ? "" : " OR ").append("Kilometraje <= 50000");
                    isFirst = false;
                }
                if (CicuentaKilometros.isSelected()) {
                    sql.append(isFirst ? "" : " OR ").append("Kilometraje > 50000 AND Kilometraje <= 100000");
                    isFirst = false;
                }
                if (masKilometros.isSelected()) {
                    sql.append(isFirst ? "" : " OR ").append("Kilometraje > 100000");
                }
                sql.append(")");
            }

            Double precio = barraPrecio.getValue();
            if (precio > 0) {
                sql.append(" AND Precio <= ?");
                parameters.add(precio);
            }

            String marca = mostrarMarca.getSelectionModel().getSelectedItem();
            if (marca != null && !marca.isEmpty()) {
                sql.append(" AND Marca = ?");
                parameters.add(marca);
            }

            String modelo = mostrarModelo.getSelectionModel().getSelectedItem();
            if (modelo != null && !modelo.isEmpty()) {
                sql.append(" AND Modelo = ?");
                parameters.add(modelo);
            }

            PreparedStatement st = con.prepareStatement(sql.toString());

            for (int i = 0; i < parameters.size(); i++) {
                st.setObject(i + 1, parameters.get(i));
            }

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String resultMarca = rs.getString("Marca");
                String resultModelo = rs.getString("Modelo");
                int resultPuerta = rs.getInt("Puertas");
                String resultComb = rs.getString("Combustible");
                int resultKilomet = rs.getInt("Kilometraje");
                int resultPrecio = rs.getInt("Precio");
                String resultEstado = rs.getString("Estado");

                Coche c = new Coche(resultMarca, resultModelo, resultPuerta, resultComb, resultKilomet, resultPrecio,
                        resultEstado);
                coches.add(c);
            }
            tablaEntera.setItems(coches);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void cerrarSesion(ActionEvent event) throws IOException {
        CO_InicioSesion.cls = null;
        App.setRoot("InicioSesion");
        App.scene.getWindow().setWidth(1000);
        App.scene.getWindow().setHeight(570);
    }

    @FXML
    void mostrarFiltros(ActionEvent event) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/SuperCoches", "root", "root");
            PreparedStatement st = con.prepareStatement("SELECT distinct Marca from SuperCoches.Coches");
            ResultSet rs = st.executeQuery();
            ArrayList<String> marcas = new ArrayList<>();
            ArrayList<String> comb = new ArrayList<>();
            while (rs.next()) {
                String marca = rs.getString("Marca");

                marcas.add(marca);

            }
            st = con.prepareStatement("SELECT distinct Combustible from SuperCoches.Coches");
            rs = st.executeQuery();
            while (rs.next()) {

                String combustible = rs.getString("Combustible");

                comb.add(combustible);
            }

            mostrarMarca.setItems(FXCollections.observableArrayList(marcas));
            opcionesCombustible.setItems(FXCollections.observableArrayList(comb));

        } catch (Exception e) {
            // TODO: handle exception
        }

        if (puertas.getOpacity() == 1) {
            puertas.setOpacity(0);
            puertas3.setOpacity(0);
            puertas5.setOpacity(0);
            combustible.setOpacity(0);
            opcionesCombustible.setOpacity(0);
            kilometros.setOpacity(0);
            VeinteKilom.setOpacity(0);
            CicuentaKilometros.setOpacity(0);
            masKilometros.setOpacity(0);
            precio.setOpacity(0);
            barraPrecio.setOpacity(0);
            mostrarMarca.setOpacity(0);
            mostrarModelo.setOpacity(0);
            marca.setOpacity(0);
            modelo.setOpacity(0);

        } else {
            puertas.setOpacity(1.0);
            puertas3.setOpacity(1.0);
            puertas5.setOpacity(1.0);
            combustible.setOpacity(1.0);
            opcionesCombustible.setOpacity(1.0);
            kilometros.setOpacity(1.0);
            VeinteKilom.setOpacity(1.0);
            CicuentaKilometros.setOpacity(1.0);
            masKilometros.setOpacity(1.0);
            precio.setOpacity(1.0);
            barraPrecio.setOpacity(1.0);
            mostrarMarca.setOpacity(1.0);
            mostrarModelo.setOpacity(1.0);
            marca.setOpacity(1.0);
            modelo.setOpacity(1.0);
        }

    }

    @FXML
    void motrarModelos(MouseEvent event) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/SuperCoches", "root", "root");
            PreparedStatement st = con
                    .prepareStatement("SELECT distinct Modelo from SuperCoches.Coches where Marca = ?");
            st.setString(1, mostrarMarca.getSelectionModel().getSelectedItem());
            ResultSet rs = st.executeQuery();

            ArrayList<String> modelos = new ArrayList<>();
            while (rs.next()) {
                String modelo = rs.getString("Modelo");

                modelos.add(modelo);

            }
            mostrarModelo.setItems(FXCollections.observableArrayList(modelos));

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    void initialize() {

        mostrarNombre.setText(CO_InicioSesion.cls.getNombre());

        puertaTabla.setCellValueFactory(new PropertyValueFactory<Coche, String>("Puertas"));
        marcaTabla.setCellValueFactory(new PropertyValueFactory<Coche, String>("Marca"));
        modeloTabla.setCellValueFactory(new PropertyValueFactory<Coche, String>("Modelo"));
        combustibleTabla.setCellValueFactory(new PropertyValueFactory<Coche, String>("Combustible"));
        kilomTabla.setCellValueFactory(new PropertyValueFactory<Coche, String>("Kilometraje"));
        estadoTabla.setCellValueFactory(new PropertyValueFactory<Coche, String>("Estado"));
        precioTabla.setCellValueFactory(new PropertyValueFactory<Coche, String>("Precio"));

        puertas.setOpacity(0);
        puertas3.setOpacity(0);
        puertas5.setOpacity(0);
        combustible.setOpacity(0);
        opcionesCombustible.setOpacity(0);
        kilometros.setOpacity(0);
        VeinteKilom.setOpacity(0);
        CicuentaKilometros.setOpacity(0);
        masKilometros.setOpacity(0);
        precio.setOpacity(0);
        barraPrecio.setOpacity(0);
        mostrarMarca.setOpacity(0);
        mostrarModelo.setOpacity(0);
        marca.setOpacity(0);
        modelo.setOpacity(0);

        tablaEntera.setRowFactory(tv -> {
            TableRow<Coche> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                 cocheSeleccionado = row.getItem();
                    try {
                        App.setRoot("CocheInfo");
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
