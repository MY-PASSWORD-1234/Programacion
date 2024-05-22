package com.ejemplo;

public class UsuariosPanel {
    private String usuario;
    private String dni;
    private String coches;
    public UsuariosPanel(String usuario, String dni, String coches) {
        this.usuario = usuario;
        this.dni = dni;
        this.coches = coches;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getCoches() {
        return coches;
    }
    public void setCoches(String coches) {
        this.coches = coches;
    }
    
}
