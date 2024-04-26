package com.ejemplo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TerciaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> aboton;

    @FXML
    private TableColumn<Dinosaurio, String> alimentacio;

    @FXML
    private TableView<Dinosaurio> tabla;

    @FXML
    private TableColumn<Dinosaurio, String> tamnio;

    @FXML
    private ChoiceBox<String> tboton;

    @FXML
    private TableColumn<Dinosaurio, String> tipo;

    @FXML
    private ChoiceBox<String> tipoborton;

    @FXML
    private Button volver;

    @FXML
    private TableColumn<Dinosaurio, String> nombre;

    @FXML
    private TableColumn<Dinosaurio, String> tamanio;

    @FXML
    void nombreTabla(ActionEvent event) {

    }

    @FXML
    void tamanioTabla(ActionEvent event) {

    }

    @FXML
    void tipoTabla(ActionEvent event) {

    }

    @FXML
    void alimentacionTabla(ActionEvent event) {

    }

    @FXML
    void buscarDino(MouseEvent event) {

        ObservableList<Dinosaurio> dinos = FXCollections.observableArrayList();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/JurassicPark", "root", "root");
            PreparedStatement st = con.prepareStatement(
                    "SELECT nombre,tamanyo,alimentacion,tipo FROM  JurassicPark.Dinosaurio where tamanyo = ? and alimentacion = ? and tipo = ?");
            st.setString(1, tboton.getSelectionModel().getSelectedItem());
            st.setString(2, aboton.getSelectionModel().getSelectedItem());
            st.setString(3, tipoborton.getSelectionModel().getSelectedItem());
            ResultSet ps = st.executeQuery();
            while (ps.next()) {
                String nombre = ps.getString("nombre");
                String tamaño = ps.getString("tamanyo");
                String ali = ps.getString("alimentacion");
                String tipo = ps.getString("tipo");

                Dinosaurio d = new Dinosaurio(nombre, tamaño, ali, tipo);
                dinos.add(d);
            }
           
            tabla.setItems(dinos);
      

        } catch (Exception e) {
            e.getMessage();
        }
    }

    @FXML
    void mostrarAlimentacionBoton(MouseEvent event) {
        ArrayList<String> js = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/JurassicPark", "root", "root");
            PreparedStatement st = con.prepareStatement("SELECT distinct alimentacion FROM  JurassicPark.Dinosaurio;");
            ResultSet ps = st.executeQuery();

            while (ps.next()) {
                String ubi = ps.getString("alimentacion");
                js.add(ubi);
            }

            aboton.setItems(FXCollections.observableArrayList(js));
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @FXML
    void mostrarDatos(ActionEvent event) {

    }

    @FXML
    void mostrarTamañoBoton(MouseEvent event) {
        ArrayList<String> js = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/JurassicPark", "root", "root");
            PreparedStatement st = con.prepareStatement("SELECT distinct tamanyo FROM  JurassicPark.Dinosaurio;");
            ResultSet ps = st.executeQuery();

            while (ps.next()) {
                String ubi = ps.getString("tamanyo");
                js.add(ubi);
            }

            tboton.setItems(FXCollections.observableArrayList(js));
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @FXML
    void mostrarTipoBoton(MouseEvent event) {
        ArrayList<String> js = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/JurassicPark", "root", "root");
            PreparedStatement st = con.prepareStatement("SELECT distinct tipo FROM  JurassicPark.Dinosaurio;");
            ResultSet ps = st.executeQuery();

            while (ps.next()) {
                String ubi = ps.getString("tipo");
                js.add(ubi);
            }

            tipoborton.setItems(FXCollections.observableArrayList(js));
        } catch (Exception e) {
            e.getMessage();
        }

    }

    @FXML
    void vol(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    void initialize() {
        ObservableList<Dinosaurio> a = FXCollections.observableArrayList();
      
        nombre.setCellValueFactory(new PropertyValueFactory<Dinosaurio, String>("nombre"));
        tamanio.setCellValueFactory(new PropertyValueFactory<Dinosaurio, String>("tamanyo"));
        tipo.setCellValueFactory(new PropertyValueFactory<Dinosaurio, String>("tipo"));
        alimentacio.setCellValueFactory(new PropertyValueFactory<Dinosaurio, String>("alimentacion"));
      
    
        

    }

}
