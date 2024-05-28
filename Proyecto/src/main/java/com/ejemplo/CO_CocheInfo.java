package com.ejemplo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class CO_CocheInfo {
    private Connection con;
    private Coche c = null;

    @FXML
    private Button anular;
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
    void anularReserva(ActionEvent event) {
        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Anular");
            alert.setHeaderText("Anular Tu Reserva");
            alert.setContentText("¿Seguro que quieres continuar?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                PreparedStatement st = con.prepareStatement(
                        "UPDATE SuperCoches.Coches set Estado = ? , idUsuario = NULL where idCoches = ?");

                st.setString(1, "Disponible");
                st.setInt(2, CO_BuscadorCoches.cocheSeleccionado.getId());
            
                st.executeUpdate();
                App.setRoot("BuscadorCoches");
                App.scene.getWindow().setWidth(1121);
                App.scene.getWindow().setHeight(560);
             
            } else {
            
            }

        } catch (Exception e) {

        }
    }

    @FXML
    void comprarAcion(ActionEvent event) {
        String estado = "";
        double iva = c.getPrecio() * 0.21;
        double cambion = c.getPrecio() * 0.007;
        double preciofin = iva + cambion + c.getPrecio();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Compra");
        alert.setHeaderText("Quieres Comprar/Reservar El Coche");
        ButtonType buttonTypeOne = new ButtonType("Comprar");
        ButtonType buttonTypeTwo = new ButtonType("Reservar");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo,
                buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == buttonTypeOne) {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Comprar");
                alert.setHeaderText("Precio Base  " + c.getPrecio() + "€\n" +
                        "Iva: " + iva + "€ \n" +
                        "Cambio De Nombre: " + cambion + "€\n" +
                        "Precio Final: " + preciofin + "€");
                alert.setContentText("¿Seguro que quieres comprar?");
                result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    estado = "Vendido";
                    try {
                        con = CO_InicioSesion.getCon();
                        PreparedStatement st = con.prepareStatement(
                                "UPDATE SuperCoches.Coches set idUsuario =? , Estado = ? where Marca =? and Modelo = ? ");
                        st.setInt(1, CO_InicioSesion.cls.getId());
                        st.setString(2, estado);
                        st.setString(3, c.getMarca());
                        st.setString(4, c.getModelo());
                        st.executeUpdate();
                        int idcoche = 0;
                        int precio = 0;
                        st = con.prepareStatement(
                                "Select * from SuperCoches.Coches where Marca = ? and Modelo =?");
                        st.setString(1, CO_BuscadorCoches.cocheSeleccionado.getMarca());
                        st.setString(2, CO_BuscadorCoches.cocheSeleccionado.getModelo());
                        ResultSet rs = st.executeQuery();
                        while (rs.next()) {
                            idcoche = rs.getInt("idCoches");
                            precio = rs.getInt("Precio");

                        }
                        int idUsuario = CO_InicioSesion.cls.getId();
                        st = con.prepareStatement(
                                "INSERT INTO SuperCoches.Recibo (idCoche, idUsuario, Precio_Base, Iva, Cambio_Nombre, Precio_Final) VALUES (?,?,?,?,?,?)");
                        st.setInt(1, idcoche);
                        st.setInt(2, idUsuario);
                        st.setInt(3, precio);
                        st.setInt(4, Double.valueOf(iva).intValue());
                        st.setInt(5, Double.valueOf(cambion).intValue());
                        st.setInt(6, Double.valueOf(preciofin).intValue());
                        st.executeUpdate();
                        App.setRoot("BuscadorCoches");
                        App.scene.getWindow().setWidth(1121);
                        App.scene.getWindow().setHeight(560);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                   
                }

            }

            else if (result.get() == buttonTypeTwo)
                try {

                    con = CO_InicioSesion.getCon();
                    PreparedStatement st;
                    if (c.getEstado().equals("Reservado")) {
                        alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Reservas");
                        alert.setHeaderText("Este Coche Ya Esta Reservado");
                        alert.setContentText("");
                        alert.showAndWait();
                    } else {
                        estado = "Reservado";
                        st = con.prepareStatement(
                                "UPDATE SuperCoches.Coches set idUsuario =? , Estado = ? where Marca =? and Modelo = ? ");
                        st.setInt(1, CO_InicioSesion.cls.getId());
                        st.setString(2, estado);
                        st.setString(3, c.getMarca());
                        st.setString(4, c.getModelo());
                        st.executeUpdate();
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Reservas");
                        alert.setHeaderText("Has Reservado Este Coche");
                        alert.setContentText("Marca " + c.getMarca() + "\n" +
                                "Modelo: " + c.getModelo() + "\n" +
                                "Puertas: " + c.getPuertas() + "\n" +
                                "Kilometros: " + c.getKilometraje() + "KM\n" +
                                "Combustible: " + c.getCombustible() + "\n" +
                                "Año: " + c.getAño() + "\n" +
                                "Precio: " + c.getPrecio() + "€\n");
                        alert.showAndWait();
                        App.setRoot("BuscadorCoches");
                        App.scene.getWindow().setWidth(1121);
                        App.scene.getWindow().setHeight(560);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            else {

            }

        }
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
        anular.setOpacity(0);

        try {
            String foto = "";
            PreparedStatement st = con
                    .prepareStatement("SELECT *from SuperCoches.Coches where Modelo = ? and Marca = ?");
            st.setString(1, CO_BuscadorCoches.cocheSeleccionado.getModelo());
            st.setString(2, CO_BuscadorCoches.cocheSeleccionado.getMarca());
            ResultSet rs = st.executeQuery();
            String estado = "";
            int id = 0;
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
                id = rs.getInt("idUsuario");

                c = new Coche(marca, modelo, puerta, comb, kilomet, precio, CV, anio, des, estado);

            }
            BackgroundFill backgroundFill;
            Background background;
            if (estado.equals("Disponible")) {
                backgroundFill = new BackgroundFill(Color.GREEN, null, null);
                background = new Background(backgroundFill);
                estadoBoton.setBackground(background);
                comprarBoton.setOpacity(1.0);
                comprarBoton.setDisable(false);
            }

            if (estado.equals("Reservado")) {
                backgroundFill = new BackgroundFill(Color.YELLOW, null, null);
                background = new Background(backgroundFill);
                estadoBoton.setBackground(background);
                st = con.prepareStatement("SELECT * from SuperCoches.Usuarios where idUsuario = ?");
                st.setInt(1, id);
                rs = st.executeQuery();
                String dni = "";
                while (rs.next()) {
                    dni = rs.getString("Dni");

                }
                if (CO_InicioSesion.cls.getDni().equals(dni)) {
                    comprarBoton.setOpacity(1.0);
                    comprarBoton.setDisable(false);
                    anular.setDisable(false);
                    anular.setOpacity(1.0);

                }

            }
            if (estado.equals("Vendido")) {
                backgroundFill = new BackgroundFill(Color.RED, null, null);
                background = new Background(backgroundFill);
                estadoBoton.setBackground(background);
                estadoBoton.applyCss();
                estadoBoton.layout();

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
