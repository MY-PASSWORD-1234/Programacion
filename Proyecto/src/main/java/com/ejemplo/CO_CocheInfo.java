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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class CO_CocheInfo {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField anioMostrar;

    @FXML
    private TextField combMostrar;

    @FXML
    private TextField cvMostrar;

    @FXML
    private TextArea descripMostrar;

    @FXML
    private TextField kilometrosMostrar;

    @FXML
    private TextField marcaMostrar;

    @FXML
    private TextField modeloMostrar;

    @FXML
    private ImageView mostrarImagen;

    @FXML
    private TextField precioMostrar;

    @FXML
    private TextField puertasMostrar;

    @FXML
    void mostrarEstado(ActionEvent event) {
      
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        App.setRoot("BuscadorCoches");
        App.scene.getWindow().setWidth(1121);
        App.scene.getWindow().setHeight(560);
    }

    @FXML
    void initialize() {
        Coche c =null;
        try {
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/SuperCoches", "root", "root");
            PreparedStatement st = con.prepareStatement("SELECT *from SuperCoches.Coches where Modelo = ?");
            st.setString(1,  CO_BuscadorCoches.cocheSeleccionado.getModelo());
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
                String des = rs.getString("Descripcion");

             c = new Coche(marca, modelo, puerta, comb, kilomet, precio,CV,anio,des,estado);
                

              
            }

            anioMostrar.setText(c.getAño()+"");
            marcaMostrar.setText(c.getMarca());
            modeloMostrar.setText(c.getModelo());
            puertasMostrar.setText(c.getPuertas()+"");
            combMostrar.setText(c.getCombustible());
            kilometrosMostrar.setText(c.getKilometraje()+"");
            precioMostrar.setText(c.getPrecio()+"");
            cvMostrar.setText(c.getCV()+"");
            descripMostrar.setText(c.getDescripcion());
            
            

        } catch (Exception e) {
            // TODO: handle exception
        }



     

    }

}
