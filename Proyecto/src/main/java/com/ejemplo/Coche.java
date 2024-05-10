package com.ejemplo;

public class Coche {
    private String Marca;
    private String Modelo;
    private int Puertas;
    private String Combustible;
    private int Kilometros;
    private int Precio;
    private int CV;
    private int Anio;
    private String Descripcion;
    private String Estado;
    public Coche(String marca, String modelo, int puertas, String combustible, int kilometros, int precio,
            String estado) {
        Marca = marca;
        Modelo = modelo;
        Puertas = puertas;
        Combustible = combustible;
        Kilometros = kilometros;
        Precio = precio;
        Estado = estado;
    }
    public String getMarca() {
        return Marca;
    }
    public void setMarca(String marca) {
        Marca = marca;
    }
    public String getModelo() {
        return Modelo;
    }
    public void setModelo(String modelo) {
        Modelo = modelo;
    }
    public int getPuertas() {
        return Puertas;
    }
    public void setPuertas(int puertas) {
        Puertas = puertas;
    }
    public String getCombustible() {
        return Combustible;
    }
    public void setCombustible(String combustible) {
        Combustible = combustible;
    }
    public int getKilometros() {
        return Kilometros;
    }
    public void setKilometros(int kilometros) {
        Kilometros = kilometros;
    }
    public int getPrecio() {
        return Precio;
    }
    public void setPrecio(int precio) {
        Precio = precio;
    }
    public int getCV() {
        return CV;
    }
    public void setCV(int cV) {
        CV = cV;
    }
    public int getAnio() {
        return Anio;
    }
    public void setAnio(int anio) {
        Anio = anio;
    }
    public String getDescripcion() {
        return Descripcion;
    }
    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
    public String getEstado() {
        return Estado;
    }
    public void setEstado(String estado) {
        Estado = estado;
    }

    
}
