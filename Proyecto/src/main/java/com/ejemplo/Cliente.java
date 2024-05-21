package com.ejemplo;

public class Cliente {
    private int id;
    private String nombre;
    private String contraseña;
    private String Dni;
    private String Usuario;
    public Cliente(int id ,String nombre, String contraseña, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        Dni = dni;
    }
    public Cliente(String nombre, String contraseña, String dni) {

        this.nombre = nombre;
        this.contraseña = contraseña;
        Dni = dni;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public String getDni() {
        return Dni;
    }
    public void setDni(String dni) {
        Dni = dni;
    }
    public String getUsuario() {
        return Usuario;
    }
    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public int getId() {
        return id;
    }
    
}
