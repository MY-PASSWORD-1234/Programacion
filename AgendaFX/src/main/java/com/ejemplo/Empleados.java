package com.ejemplo;

public class Empleados {
    private int IdEmpleado ;
    private String nombre ;
    private String apellidos;
    private int telefono;
    private  String fecha_nacimiento ;
    private  String cargo ;
    public Empleados(int idEmpleado, String nombre, String apellidos, int telefono, String fecha_nacimiento,
            String cargo) {
        IdEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.cargo = cargo;
    }
    public int getIdEmpleado() {
        return IdEmpleado;
    }
    public void setIdEmpleado(int idEmpleado) {
        IdEmpleado = idEmpleado;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }
    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }



    
}
