package com.ejemplo;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimaryController {
    ArrayList<Empleados> empleados = new ArrayList<>();
    int id = 0;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Apellidos;

    @FXML
    private TextField Cargo;
    @FXML
    private Button Insertar;

    @FXML
    private TextField FechaNacimiento;

    @FXML
    private TextField Idempleado;

    @FXML
    private TextField Nombre;

    @FXML
    private TextField Telefono;

    @FXML
    private Button irAlInicio;

    @FXML
    private Button irAlRegAnterior;

    @FXML
    private Button irFInalReg;

    @FXML
    private Button irSiguienteReg;
    @FXML
    private Button Guardar;
    @FXML
    private Button modificar;
    @FXML
    private Button Borrar;

    @FXML
    void BotonAnteriorReg(ActionEvent event) {

        if (id > 0) {
            id--;
            Idempleado.setText(String.valueOf(empleados.get(id).getIdEmpleado()));
            Nombre.setText(empleados.get(id).getNombre());
            Apellidos.setText(empleados.get(id).getApellidos());
            Telefono.setText(String.valueOf(empleados.get(id).getTelefono()));
            FechaNacimiento.setText(empleados.get(id).getFecha_nacimiento());
            Cargo.setText(empleados.get(id).getCargo());
            Idempleado.setDisable(true);
            Nombre.setDisable(true);
            Apellidos.setDisable(true);
            Telefono.setDisable(true);
            FechaNacimiento.setDisable(true);
            Cargo.setDisable(true);
        }

    }

    @FXML
    void BotonFinalReg(ActionEvent event) {
        Idempleado.setText(String.valueOf(empleados.get(empleados.size() - 1).getIdEmpleado()));
        Nombre.setText(String.valueOf(empleados.get(empleados.size() - 1).getNombre()));
        Apellidos.setText(String.valueOf(empleados.get(empleados.size() - 1).getApellidos()));
        Telefono.setText(String.valueOf(empleados.get(empleados.size() - 1).getTelefono()));
        FechaNacimiento.setText(String.valueOf(empleados.get(empleados.size() - 1).getFecha_nacimiento()));
        Cargo.setText(String.valueOf(empleados.get(empleados.size() - 1).getCargo()));

        Idempleado.setDisable(true);
        Nombre.setDisable(true);
        Apellidos.setDisable(true);
        Telefono.setDisable(true);
        FechaNacimiento.setDisable(true);
        Cargo.setDisable(true);
    }

    @FXML
    void BotonSiguienteReg(ActionEvent event) {

        if (id < empleados.size() - 1) {
            id++;
            Idempleado.setText(String.valueOf(empleados.get(id).getIdEmpleado()));
            Nombre.setText(empleados.get(id).getNombre());
            Apellidos.setText(empleados.get(id).getApellidos());
            Telefono.setText(String.valueOf(empleados.get(id).getTelefono()));
            FechaNacimiento.setText(empleados.get(id).getFecha_nacimiento());
            Cargo.setText(empleados.get(id).getCargo());
            Idempleado.setDisable(true);
            Nombre.setDisable(true);
            Apellidos.setDisable(true);
            Telefono.setDisable(true);
            FechaNacimiento.setDisable(true);
            Cargo.setDisable(true);

        }

    }

    @FXML
    void botonInicio(ActionEvent event) {

        Idempleado.setText(String.valueOf(empleados.get(0).getIdEmpleado()));
        Nombre.setText(String.valueOf(empleados.get(0).getNombre()));
        Apellidos.setText(String.valueOf(empleados.get(0).getApellidos()));
        Telefono.setText(String.valueOf(empleados.get(0).getTelefono()));
        FechaNacimiento.setText(String.valueOf(empleados.get(0).getFecha_nacimiento()));
        Cargo.setText(String.valueOf(empleados.get(0).getCargo()));
        Idempleado.setDisable(true);
        Nombre.setDisable(true);
        Apellidos.setDisable(true);
        Telefono.setDisable(true);
        FechaNacimiento.setDisable(true);
        Cargo.setDisable(true);
    }

    @FXML
    void BotonParaBorrar(ActionEvent event) {

    }

    @FXML
    void BotonParaInsertar(ActionEvent event) {
        Idempleado.setEditable(true);
        Idempleado.setDisable(false);
        Nombre.setDisable(false);
        Apellidos.setDisable(false);
        Telefono.setDisable(false);
        FechaNacimiento.setDisable(false);
        Cargo.setDisable(false);

        Idempleado.setText("");
        Nombre.setText("");
        Apellidos.setText("");
        Telefono.setText("");
        FechaNacimiento.setText("");
        Cargo.setText("");

    }

    @FXML
    void BotonParaGuardar(ActionEvent event) {

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/Agenda", "root", "root");

            PreparedStatement st = con.prepareStatement("Select * from Agenda.Empleados where IdEmpleado = ?");
            st.setInt(1, Integer.parseInt(Idempleado.getText()));

            Integer r = st.executeUpdate();
            if (r == null) {
                st = con.prepareStatement(
                        "INSERT INTO Agenda.Empleados (IdEmpleados, Nombre, Apellidos, Telefono, Fecha_Nacimiento, Cargo) VALUES (?,?,?,?, ?, ?)");
                        
                        r = st.executeUpdate();
            } else {
                st = con.prepareStatement(
                        "Update Agenda.Empleados Set Nombre = ?, Apellidos = ? , Telefono = ? , Fecha_Nacimiento = ? , Cargo = ? where IdEmpleados = ?");

                empleados.get(Integer.parseInt(Idempleado.getText()) - 1).setNombre(Nombre.getText());
                empleados.get(Integer.parseInt(Idempleado.getText()) - 1).setApellidos(Apellidos.getText());
                empleados.get(Integer.parseInt(Idempleado.getText()) - 1).setTelefono(Integer.parseInt(Telefono.getText()));
                empleados.get(Integer.parseInt(Idempleado.getText()) - 1).setFecha_nacimiento(FechaNacimiento.getText());
                empleados.get(Integer.parseInt(Idempleado.getText()) - 1).setCargo(Cargo.getText());

                st.setInt(6, empleados.get(Integer.parseInt(Idempleado.getText()) - 1).getIdEmpleado());
                st.setString(1, empleados.get(Integer.parseInt(Idempleado.getText()) - 1).getNombre());
                st.setString(2, empleados.get(Integer.parseInt(Idempleado.getText()) - 1).getApellidos());
                st.setInt(3, empleados.get(Integer.parseInt(Idempleado.getText()) - 1).getTelefono());
                st.setString(4, empleados.get(Integer.parseInt(Idempleado.getText()) - 1).getFecha_nacimiento());
                st.setString(5, empleados.get(Integer.parseInt(Idempleado.getText()) - 1).getCargo());

                r = st.executeUpdate();

            }

        } catch (Exception e) {
            e.getMessage();
        }

    }

    @FXML
    void BotonParaModifica(ActionEvent event) {
        Nombre.setDisable(false);
        Apellidos.setDisable(false);
        Telefono.setDisable(false);
        FechaNacimiento.setDisable(false);
        Cargo.setDisable(false);
    }

    @FXML
    void initialize() {

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/Agenda", "root", "root");
            PreparedStatement st = con.prepareStatement("SELECT * from Empleados", ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet ps = st.executeQuery();
            while (ps.next()) {

                int codigo = ps.getInt("IdEmpleados");
                String nombre = ps.getString("Nombre");
                String apellidos = ps.getString("Apellidos");
                int telefono = ps.getInt("Telefono");
                String fecha = ps.getString("Fecha_Nacimiento");
                String cargo = ps.getString("Cargo");

                Empleados emp = new Empleados(codigo, nombre, apellidos, telefono, fecha, cargo);
                empleados.add(emp);
            }

            Idempleado.setText(String.valueOf(empleados.get(0).getIdEmpleado()));
            Nombre.setText(String.valueOf(empleados.get(0).getNombre()));
            Apellidos.setText(String.valueOf(empleados.get(0).getApellidos()));
            Telefono.setText(String.valueOf(empleados.get(0).getTelefono()));
            FechaNacimiento.setText(String.valueOf(empleados.get(0).getFecha_nacimiento()));
            Cargo.setText(String.valueOf(empleados.get(0).getCargo()));
            Idempleado.setDisable(true);
            Nombre.setDisable(true);
            Apellidos.setDisable(true);
            Telefono.setDisable(true);
            FechaNacimiento.setDisable(true);
            Cargo.setDisable(true);

        } catch (Exception e) {
            e.getMessage();
        }

    }

}
