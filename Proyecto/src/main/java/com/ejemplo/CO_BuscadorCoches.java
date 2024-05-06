package com.ejemplo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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
    private ChoiceBox<?> mostrarMarca;

    @FXML
    private ChoiceBox<?> mostrarModelo;

    @FXML
    private ChoiceBox<?> opcionesCombustible;

    @FXML
    private Label puertas;

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
    void buscarLupa(MouseEvent event) {

    }

    @FXML
    void cerrarSesion(ActionEvent event) throws IOException {
        App.setRoot("InicioSesion");
    }

    @FXML
    void mostrarFiltros(ActionEvent event) {
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


