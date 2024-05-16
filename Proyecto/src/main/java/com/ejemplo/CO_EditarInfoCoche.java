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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class CO_EditarInfoCoche {
    private Connection con;
    Coche c;
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
    private ImageView imagenCoche;

    @FXML
    private TextField kilometrosMostrar;

    @FXML
    private TextField marcaMostrar;

    @FXML
    private TextField modeloMostrar;

    @FXML
    private TextField precioMostrar;

    @FXML
    private TextField puertasMostrar;

    @FXML
    void actualizarCoche(ActionEvent event) {
        try {
            con = CO_InicioSesion.getCon();
            PreparedStatement st = con.prepareStatement(
                    "UPDATE SuperCoches.Coches set Marca = ? , Modelo = ?, Puertas = ?,Combustible = ?, Kilometraje = ?,Precio = ?,CV = ? ,Año = ? , Descripcion = ? where Modelo = ?");
            st.setString(1, marcaMostrar.getText());
            st.setString(2, modeloMostrar.getText());
            st.setInt(3, Integer.parseInt(puertasMostrar.getText()));
            st.setString(4, combMostrar.getText());
            st.setInt(5, Integer.parseInt(kilometrosMostrar.getText()));
            st.setInt(6, Integer.parseInt(precioMostrar.getText()));
            st.setInt(7, Integer.parseInt(cvMostrar.getText()));
            st.setInt(8, Integer.parseInt(anioMostrar.getText()));
            st.setString(9, descripMostrar.getText());
            st.setString(10, modeloMostrar.getText());

            st.executeUpdate();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Actualizado");
            alert.setHeaderText("");
            alert.setContentText("Se ha actualizado con exito");
            alert.showAndWait();
            App.setRoot("EditarCoche");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void seleccionarFoto(ActionEvent event) {

    }

    @FXML
    void voler(ActionEvent event) throws IOException {
        App.setRoot("EditarCoche");
    }

    @FXML
    void initialize() {
        con = CO_InicioSesion.getCon();

        System.out.println(con);
        try {
            PreparedStatement st = con.prepareStatement("SELECT * from SuperCoches.Coches where Modelo = ?");
            st.setString(1, CO_EditarCoche.cocheSeleccionado2.getModelo());
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

                c = new Coche(marca, modelo, puerta, comb, kilomet, precio, CV, anio, des, estado);
            }

            anioMostrar.setText(c.getAño() + "");
            marcaMostrar.setText(c.getMarca());
            modeloMostrar.setText(c.getModelo());
            puertasMostrar.setText(c.getPuertas() + "");
            combMostrar.setText(c.getCombustible());
            kilometrosMostrar.setText(c.getKilometraje() + "");
            precioMostrar.setText(c.getPrecio() + "");
            cvMostrar.setText(c.getCV() + "");
            descripMostrar.setText(c.getDescripcion());

        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}