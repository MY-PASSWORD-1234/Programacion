package com.ejemplo;

public class Cliente {
    private String nif;
    private String clave;
    private String nombre;
    private String apellidos;
    private int numero;
    private Boolean iniciado;

    public Cliente(String nif, String clave, String nombre, String apellidos, int numero) {
        this.nif = nif;
        this.clave = clave;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numero = numero;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Boolean getIniciado() {
        return iniciado;
    }

    public void setIniciado(Boolean iniciado) {
        this.iniciado = iniciado;
    }

    public String getNif() {
        return nif;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
