package com.ejemplo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class CO_CocheInfo {
    private Connection con;

    @FXML
    private Button comprarBoton;
    @FXML
    private ResourceBundle resources;
    @FXML
    private Button estadoBoton;
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
    void comprarAcion(ActionEvent event) {

    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        App.setRoot("BuscadorCoches");
        App.scene.getWindow().setWidth(1121);
        App.scene.getWindow().setHeight(560);
    }

    @FXML
    void initialize() {
        comprarBoton.setOpacity(0);
        con = CO_InicioSesion.getCon();
        Coche c = null;
        try {
            String foto ="" ;
            PreparedStatement st = con.prepareStatement("SELECT *from SuperCoches.Coches where Modelo = ? and Marca = ?");
            st.setString(1, CO_BuscadorCoches.cocheSeleccionado.getModelo());
            st.setString(2, CO_BuscadorCoches.cocheSeleccionado.getMarca());
            ResultSet rs = st.executeQuery();
            String estado = "";
            while (rs.next()) {
                String marca = rs.getString("Marca");
                String modelo = rs.getString("Modelo");
                int puerta = rs.getInt("Puertas");
                String comb = rs.getString("Combustible");
                int precio = rs.getInt("Precio");
                estado = rs.getString("Estado");
                int kilomet = rs.getInt("Kilometraje");
                int CV = rs.getInt("CV");
                int anio = rs.getInt("Año");
                String des = rs.getString("Descripcion");
                foto = rs.getString("Img");

                c = new Coche(marca, modelo, puerta, comb, kilomet, precio, CV, anio, des, estado);
                BackgroundFill backgroundFill;
                Background background;

                if (estado.equals("Disponible")) {
                    backgroundFill = new BackgroundFill(Color.GREEN, null, null);
                    background = new Background(backgroundFill);
                    estadoBoton.setBackground(background);
                    comprarBoton.setOpacity(1.0);
                }

                if (estado.equals("Reservado")) {
                    backgroundFill = new BackgroundFill(Color.YELLOW, null, null);
                    background = new Background(backgroundFill);
                    estadoBoton.setBackground(background);
                }
                if (estado.equals("Vendido")) {
                    backgroundFill = new BackgroundFill(Color.RED, null, null);
                    background = new Background(backgroundFill);
                    estadoBoton.setBackground(background);
                    estadoBoton.applyCss();
                    estadoBoton.layout();

                }
            }
            File file = new File(foto);
            Image imagen = new Image(file.toURI().toString());
            anioMostrar.setText(c.getAño() + "");
            marcaMostrar.setText(c.getMarca());
            modeloMostrar.setText(c.getModelo());
            puertasMostrar.setText(c.getPuertas() + "");
            combMostrar.setText(c.getCombustible());
            kilometrosMostrar.setText(c.getKilometraje() + "");
            precioMostrar.setText(c.getPrecio() + "");
            cvMostrar.setText(c.getCV() + "");
            descripMostrar.setText(c.getDescripcion());
            mostrarImagen.setImage(imagen);

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
