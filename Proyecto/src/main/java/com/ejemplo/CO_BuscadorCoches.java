package com.ejemplo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class CO_BuscadorCoches {
    static Coche cocheSeleccionado = null;
    private static Connection con;
    static Timeline animacion;
    static Timeline animacion2;
    @FXML
    private ToggleGroup k;

    @FXML
    private ToggleGroup p;
    @FXML
    private TextField mostrarNombre;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton CicuentaKilometros;

    @FXML
    private RadioButton VeinteKilom;

    @FXML
    private RadioButton masKilometros;

    @FXML
    private Slider barraPrecio;

    @FXML
    private TextField buscador;

    @FXML
    private Label combustible;

    @FXML
    private Label kilometros;

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
    private TableColumn<Coche, Integer> kilomTabla;
    @FXML
    private TableColumn<Coche, String> marcaTabla;
    @FXML
    private TableColumn<Coche, String> modeloTabla;
    @FXML
    private TableColumn<Coche, Integer> precioTabla;

    @FXML
    private TableColumn<Coche, Integer> puertaTabla;
    @FXML
    private TableView<Coche> tablaEntera;

    @FXML
    private RadioButton puerta3;

    @FXML
    private RadioButton puerta5;
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
    void cargarPerfilUsuario(ActionEvent event) throws IOException {
        App.setRoot("UsurioInfo");
        App.scene.getWindow().setWidth(1078);
        App.scene.getWindow().setHeight(570);
    }
    
    @FXML
    void buscarLupa(ActionEvent event) {
        ObservableList<Coche> coches = FXCollections.observableArrayList();
        try {
            StringBuilder sql = new StringBuilder(
                    "SELECT Marca, Modelo, Puertas, Combustible, Kilometraje, Precio, Estado FROM SuperCoches.Coches WHERE 1=1");
            List<Object> parameters = new ArrayList<>();

            if (puerta3.isSelected() || puerta5.isSelected()) {
                sql.append(" AND Puertas = ?");
                parameters.add(puerta3.isSelected() ? 3 : 5);
            }
            String busca = buscador.getText();
            if (!busca.isEmpty()) {
                sql.append(" AND (Marca LIKE ? OR Modelo LIKE ?)");
                parameters.add("%" + busca + "%");
                parameters.add("%" + busca + "%");
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
            if (precio > 50000) {
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
                Integer resultPuerta = rs.getInt("Puertas");
                String resultComb = rs.getString("Combustible");
                Integer resultKilomet = rs.getInt("Kilometraje");
                Integer resultPrecio = rs.getInt("Precio");
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

        if (puertas.getOpacity() >= 0.9) {
            animacion2.play();

        } else {
            animacion.play();
        }

    }

    @FXML
    void motrarModelos(MouseEvent event) {
        try {

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
        con = CO_InicioSesion.getCon();
        mostrarNombre.setText(CO_InicioSesion.cls.getNombre());

        marcaTabla.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        modeloTabla.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
        puertaTabla.setCellValueFactory(new PropertyValueFactory<>("Puertas"));
        combustibleTabla.setCellValueFactory(new PropertyValueFactory<>("Combustible"));
        kilomTabla.setCellValueFactory(new PropertyValueFactory<>("Kilometraje"));

        precioTabla.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        estadoTabla.setCellValueFactory(new PropertyValueFactory<>("Estado"));

        puertas.setOpacity(0);
        puerta3.setOpacity(0);
        puerta5.setOpacity(0);
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
        animacion = new Timeline();
        animacion.setCycleCount(10);
        animacion.setAutoReverse(false);
        animacion.getKeyFrames().add(new KeyFrame(javafx.util.Duration.millis(100),
                event -> {
                    modelo.setOpacity(modelo.getOpacity() + 0.1);
                    puertas.setOpacity(puertas.getOpacity() + 0.1);
                    puerta3.setOpacity(puerta3.getOpacity() + 0.1);
                    puerta5.setOpacity(puerta5.getOpacity() + 0.1);
                    combustible.setOpacity(combustible.getOpacity() + 0.1);
                    opcionesCombustible.setOpacity(opcionesCombustible.getOpacity() + 0.1);
                    kilometros.setOpacity(kilometros.getOpacity() + 0.1);
                    VeinteKilom.setOpacity(VeinteKilom.getOpacity() + 0.1);
                    CicuentaKilometros.setOpacity(CicuentaKilometros.getOpacity() + 0.1);
                    masKilometros.setOpacity(masKilometros.getOpacity() + 0.1);
                    precio.setOpacity(precio.getOpacity() + 0.1);
                    barraPrecio.setOpacity(barraPrecio.getOpacity() + 0.1);
                    mostrarMarca.setOpacity(mostrarMarca.getOpacity() + 0.1);
                    mostrarModelo.setOpacity(mostrarModelo.getOpacity() + 0.1);
                    marca.setOpacity(marca.getOpacity() + 0.1);
                }));
        animacion2 = new Timeline();
        animacion2.setCycleCount(10);
        animacion2.setAutoReverse(false);
        animacion2.getKeyFrames().add(new KeyFrame(javafx.util.Duration.millis(45),
                event -> {
                    modelo.setOpacity(modelo.getOpacity() - 0.1);

                    puertas.setOpacity(puertas.getOpacity() - 0.1);
                    puerta3.setOpacity(puerta3.getOpacity() - 0.1);
                    puerta5.setOpacity(puerta5.getOpacity() - 0.1);
                    combustible.setOpacity(combustible.getOpacity() - 0.1);
                    opcionesCombustible.setOpacity(opcionesCombustible.getOpacity() - 0.1);
                    kilometros.setOpacity(kilometros.getOpacity() - 0.1);
                    VeinteKilom.setOpacity(VeinteKilom.getOpacity() - 0.1);
                    CicuentaKilometros.setOpacity(CicuentaKilometros.getOpacity() - 0.1);
                    masKilometros.setOpacity(masKilometros.getOpacity() - 0.1);
                    precio.setOpacity(precio.getOpacity() - 0.1);
                    barraPrecio.setOpacity(barraPrecio.getOpacity() - 0.1);
                    mostrarMarca.setOpacity(mostrarMarca.getOpacity() - 0.1);
                    mostrarModelo.setOpacity(mostrarModelo.getOpacity() - 0.1);
                    marca.setOpacity(marca.getOpacity() - 0.1);

                }));

    }

}
