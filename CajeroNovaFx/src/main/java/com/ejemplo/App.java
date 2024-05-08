package com.ejemplo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("InicioSesion"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    public static void llenarlista (){
         ArrayList<Cliente> listacls = new ArrayList<>();
           try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/CajeroNOVA", "root", "root");
            PreparedStatement st = con.prepareStatement("SELECT * from CajeroNOVA.Cliente");
            ResultSet ps = st.executeQuery();
            while (ps.next()) {

                String codigo = ps.getString("NIF");
                String clave = ps.getString("clave");
                String nombre = ps.getString("nombre");
                String apellido = ps.getString("apellidos");
                int num = ps.getInt("movil");
           

                Cliente cls = new Cliente(codigo, clave, nombre, apellido, num);
                listacls.add(cls);
            }

    }catch(SQLException e){
        e.printStackTrace();
    }
}
    }

