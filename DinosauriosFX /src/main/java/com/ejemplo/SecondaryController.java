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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SecondaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField capacidad;

    @FXML
    private Button crear;

    @FXML
    private ChoiceBox<String> dinos;

    @FXML
    private TextField edad;

    @FXML
    private TextField nombre;

    @FXML
    private Button salir;

    @FXML
    private Button volver;

    @FXML
    private ChoiceBox<String> zona;

    @FXML
    void CrearAtraccion(ActionEvent event) {
        try {
            PreparedStatement st;
            ResultSet psD;
            ResultSet psZ;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/JurassicPark", "root", "root");
            st = con.prepareStatement("SELECT id_dino from JurassicPark.Dinosaurio where nombre = ?");
            st.setString(1, dinos.getSelectionModel().getSelectedItem());
            psD= st.executeQuery();
            psD.next();
            int nd = psD.getInt("id_dino");
            st = con.prepareStatement("SELECT id_zona from JurassicPark.Zona where ubicacion = ?");
            st.setString(1, zona.getSelectionModel().getSelectedItem());
            psZ = st.executeQuery();
            psZ.next();
            int nz = psZ.getInt("id_zona");
            st = con.prepareStatement(
                    "INSERT INTO JurassicPark.Atraccion (id_zona, id_dino, nombre, capacidad, edad_minima) VALUES (?,?,?,?,?)");
            
            st.setString(3, nombre.getText());
            st.setInt(4, Integer.parseInt(capacidad.getText()));
            st.setInt(5, Integer.parseInt(edad.getText()));
            st.setInt(2, nd);
            st.setInt(1,  nz);
            int r = st.executeUpdate();
            
        } catch (Exception e) {
           System.out.println(e.getMessage()); 
        }
    }

    @FXML
    void ListarDinos(MouseEvent event) {
        ArrayList<String> js = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/JurassicPark", "root", "root");
            PreparedStatement st = con.prepareStatement("SELECT nombre from JurassicPark.Dinosaurio");
            ResultSet ps = st.executeQuery();

            while (ps.next()) {
                String ubi = ps.getString("nombre");
                js.add(ubi);
            }

            dinos.setItems(FXCollections.observableArrayList(js));
        } catch (Exception e) {
            e.getMessage();
        }

    }

    @FXML
    void ListarZona(MouseEvent event) {
        ArrayList<String> js = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/JurassicPark", "root", "root");
            PreparedStatement st = con.prepareStatement("SELECT ubicacion from JurassicPark.Zona");
            ResultSet ps = st.executeQuery();

            while (ps.next()) {
                String ubi = ps.getString("ubicacion");
                js.add(ubi);
            }

            zona.setItems(FXCollections.observableArrayList(js));
        } catch (Exception e) {
            e.getMessage();
        }

    }

    @FXML
    void Salir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void vol(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    void initialize() {
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/JurassicPark", "root", "root");

        } catch (Exception e) {
            e.getMessage();
        }

    }
}
