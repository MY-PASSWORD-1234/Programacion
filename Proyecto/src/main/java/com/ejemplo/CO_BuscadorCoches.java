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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
public class CO_BuscadorCoches {


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
    private TableColumn<?, ?> combustibleTabla;

    @FXML
    private TableColumn<?, ?> estadoTabla;

    @FXML
    private TableColumn<?, ?> kilomTabla;
    @FXML
    private TableColumn<?, ?> marcaTabla;
    @FXML
    private TableColumn<?, ?> modeloTabla;
    @FXML
    private TableColumn<?, ?> precioTabla;

    @FXML
    private TableColumn<?, ?> puertaTabla;
    @FXML
    private TableView<?> tablaEntera;
    

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
    void abrirCocheTabla(MouseEvent event) {

    }
    @FXML
    void buscarLupa(MouseEvent event) {
        try {
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/SuperCoches", "root", "root");
            PreparedStatement st = con.prepareStatement("SELECT Marca, Modelo , Puertas , Combustible, Kilometraje, Precio, Estado from SuperCoches.Coches where Puertas = ? and Combustible =? and  Kilometraje >  ? and Kilometraje <= ? and Precio <= ? and Marca = ? and Modelo = ? " );
           
            int puertas = 0;
            if (puertas3.isSelected()) {
                puertas = 3;
                puertas5.setSelected(false);
            }else{
                puertas = 5;
                puertas3.setSelected(false);
            }
         
           int kil1 = 0;
           int kil2 = 0;
           if (VeinteKilom.isSelected()) {
            kil1 = 25000 ;
            kil1 = 50000 ;
            }else if (CicuentaKilometros.isSelected()) {
                kil1 = 50000;
                kil2 = 100000;
            }else{
                kil1 = 100000;
                kil2 = 100000000;
            }




            st.setInt(1, puertas);
            st.setString(2, opcionesCombustible.getSelectionModel().getSelectedItem());
            st.setInt(3, kil1);
            st.setInt(4, kil2);
            st.setDouble(5, barraPrecio.getValue());
            st.setString(6,  mostrarMarca.getSelectionModel().getSelectedItem());
            st.setString(7,  mostrarModelo.getSelectionModel().getSelectedItem());
            ResultSet rs = st.executeQuery();


        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    void cerrarSesion(ActionEvent event) throws IOException {
        App.setRoot("InicioSesion");
        App.scene.getWindow().setWidth(1000);
        App.scene.getWindow().setHeight(570);
    }

    @FXML
    void mostrarFiltros(ActionEvent event) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/SuperCoches", "root", "root");
            PreparedStatement st = con.prepareStatement("SELECT distinct * from SuperCoches.Coches" );
            ResultSet rs = st.executeQuery();
            ArrayList <String> marcas = new ArrayList<>();
            ArrayList <String> comb = new ArrayList<>();
            while (rs.next()) {
             String marca =    rs.getString("Marca");
             String combustible  = rs.getString("Combustible");
                marcas.add(marca);
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

        }else{
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
    void initialize() {
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
        
        

    }

}


