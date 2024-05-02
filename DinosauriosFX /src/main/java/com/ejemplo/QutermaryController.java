package com.ejemplo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class QutermaryController {
    @FXML
    private Button salir;
   
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botonanaranjaE;

    @FXML
    private Button botonazulS;

    @FXML
    private Button botonrojoO;

    @FXML
    private Button botonverdeN;

    @FXML
    private Button volver;

    @FXML
    void mostrarDinoEste(ActionEvent event) {
        try {
            PreparedStatement st;
            ResultSet ps;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/JurassicPark", "root", "root");
            st = con.prepareStatement("SELECT nombre from JurassicPark.Atraccion where id_zona = 2");
            ps = st.executeQuery();
            String zonas = "";
            while (ps.next()) {
                zonas += ps.getString("nombre")+" | ";

            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Diálogo de información");
            alert.setHeaderText("Atracciones Del Este");
            alert.setContentText(zonas);
            alert.showAndWait();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void mostrarDinoNorte(ActionEvent event) {
        try {
            PreparedStatement st;
            ResultSet ps;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/JurassicPark", "root", "root");
            st = con.prepareStatement("SELECT nombre from JurassicPark.Atraccion where id_zona = 1");
            ps = st.executeQuery();
            String zonas = "";
            while (ps.next()) {
                zonas += ps.getString("nombre")+" | ";

            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Diálogo de información");
            alert.setHeaderText("Atracciones Del Norte");
            alert.setContentText(zonas);
            alert.showAndWait();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    

    @FXML
    void mostrarDinoOeste(ActionEvent event) {
        try {
            PreparedStatement st;
            ResultSet ps;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/JurassicPark", "root", "root");
            st = con.prepareStatement("SELECT nombre from JurassicPark.Atraccion where id_zona = 4");
            ps = st.executeQuery();
            String zonas = "";
            while (ps.next()) {
                zonas += ps.getString("nombre")+" | ";

            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Diálogo de información");
            alert.setHeaderText("Atracciones Del Oeste");
            alert.setContentText(zonas);
            alert.showAndWait();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    

    @FXML
    void mostrarDinoSud(ActionEvent event) {
        try {
            PreparedStatement st;
            ResultSet ps;
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/JurassicPark", "root", "root");
            st = con.prepareStatement("SELECT nombre from JurassicPark.Atraccion where id_zona = 3");
            ps = st.executeQuery();
            String zonas = "";
            while (ps.next()) {
                zonas += ps.getString("nombre")+" | ";

            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Diálogo de información");
            alert.setHeaderText("Atracciones Del Sud");
            alert.setContentText(zonas);
            alert.showAndWait();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    

    @FXML
    void vol(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
    @FXML
    void Salir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void initialize() {

    }

}
