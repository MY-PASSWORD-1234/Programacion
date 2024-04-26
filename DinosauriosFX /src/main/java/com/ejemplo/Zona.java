package com.ejemplo;

public class Zona {
    private String nombre ;
    private String ubicacion ;
    private int num_atraccion ;
    
    public Zona(String nombre, String ubicacion, int num_atraccion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.num_atraccion = num_atraccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getNum_atraccion() {
        return num_atraccion;
    }

    public void setNum_atraccion(int num_atraccion) {
        this.num_atraccion = num_atraccion;
    }

    
}
