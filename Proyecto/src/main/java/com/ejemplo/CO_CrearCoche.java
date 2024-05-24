package com.ejemplo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class CO_CrearCoche {
    String foto;
    String rutaModificada;
    private Connection con;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField anio;

    @FXML
    private ChoiceBox<String> combustible;

    @FXML
    private Button crearcoche;

    @FXML
    private TextField cv;

    @FXML
    private TextArea descripcion;

    @FXML
    private TextField kilometros;

    @FXML
    private Button limpiarCampos;

    @FXML
    private TextField marca;

    @FXML
    private TextField modelo;

    @FXML
    private ImageView ponerImagen;

    @FXML
    private TextField precio;

    @FXML
    private ChoiceBox<String> puertas;

    @FXML
    void seleccionarFoto(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona una foto");
        fileChooser.setInitialDirectory(new File("src/main/resources/com/ejemplo/img"));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            foto = file.getAbsolutePath();
            String rutaParaEliminar = "/home/dam/Escritorio/Programacion/Proyecto";
            if (foto.startsWith(rutaParaEliminar)) {
                rutaModificada = foto.substring(rutaParaEliminar.length());
                if (rutaModificada.startsWith("/")) {
                    rutaModificada = rutaModificada.substring(1);
                }

            } else {

            }

            Image image = new Image(file.toURI().toString());
            ponerImagen.setImage(image);
        } else {

        }

    }

    @FXML
    void crearCoche(ActionEvent event) {

        try {

            PreparedStatement st = con
                    .prepareStatement("SELECT * FROM SuperCoches.Coches WHERE Marca = ? AND Modelo = ?");
            st.setString(1, marca.getText());
            st.setString(2, modelo.getText());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Diálogo de información");
                alert.setHeaderText("");
                alert.setContentText(
                        "No se ha podido crear este coche porque ya existe un coche de esa Marca y Modelo");
                alert.showAndWait();
                marca.setText("");
                modelo.setText("");
                puertas.setValue(null);
                kilometros.setText("");
                precio.setText("");
                combustible.setValue(null);
                descripcion.setText("");
                cv.setText("");
                anio.setText("");
            } else {

                st = con.prepareStatement(
                        "INSERT INTO SuperCoches.Coches (Marca, Modelo, Puertas, Combustible, Kilometraje, Precio, CV, Año, Descripcion,Img) VALUES (?,?,?,?,?,?,?,?,?,?)");
                st.setString(1, marca.getText());
                st.setString(2, modelo.getText());
                st.setInt(3, Integer.parseInt(puertas.getSelectionModel().getSelectedItem()));
                st.setString(4, combustible.getSelectionModel().getSelectedItem());
                st.setInt(5, Integer.parseInt(kilometros.getText()));
                st.setInt(6, Integer.parseInt(precio.getText()));
                st.setInt(7, Integer.parseInt(cv.getText()));
                st.setInt(8, Integer.parseInt(anio.getText()));
                st.setString(9, descripcion.getText());
                st.setString(10, rutaModificada);
                st.executeUpdate();

                marca.setText("");
                modelo.setText("");
                puertas.setValue(null);
                kilometros.setText("");
                precio.setText("");
                combustible.setValue(null);
                descripcion.setText("");
                cv.setText("");
                anio.setText("");
                ponerImagen.setImage(null);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Diálogo de información");
                alert.setHeaderText("");
                alert.setContentText("Coche creado con éxito");
                alert.showAndWait();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void limpiarCampos(ActionEvent event) {
        marca.setText("");
        modelo.setText("");
        puertas.setValue(null);
        kilometros.setText("");
        precio.setText("");
        combustible.setValue(null);
        descripcion.setText("");
        cv.setText("");
        anio.setText("");
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        App.setRoot("AdminPanel");
    }

    @FXML
    void initialize() {
        con = CO_InicioSesion.getCon();
        try {
            PreparedStatement st = con.prepareStatement("SELECT distinct Puertas from SuperCoches.Coches");
            ResultSet rs = st.executeQuery();
            ArrayList<String> puertass = new ArrayList<>();
            ArrayList<String> comb = new ArrayList<>();
            while (rs.next()) {
                String puerta = rs.getString("Puertas");

                puertass.add(puerta);

            }
            st = con.prepareStatement("SELECT distinct Combustible from SuperCoches.Coches");
            rs = st.executeQuery();
            while (rs.next()) {

                String combustible = rs.getString("Combustible");

                comb.add(combustible);
            }
            puertas.setItems(FXCollections.observableArrayList(puertass));
            combustible.setItems(FXCollections.observableArrayList(comb));
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
