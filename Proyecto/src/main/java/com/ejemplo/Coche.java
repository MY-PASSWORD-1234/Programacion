package com.ejemplo;

public class Coche {
    private String Marca;
    private String Modelo;
    private Integer Puertas;
    private String Combustible;
    private Integer Kilometraje;
    private Integer Precio;
    private int CV;
    private int Año;
    private String Descripcion;
    private String Estado;

    public Coche(String marca, String modelo, Integer puertas, String combustible, Integer kilometros, Integer precio,
            String estado) {
        Marca = marca;
        Modelo = modelo;
        Puertas = puertas;
        Combustible = combustible;
        Kilometraje = kilometros;
        Precio = precio;
        Estado = estado;
    }

  

    public Coche(String marca, String modelo, int puertas, String combustible, int kilometros, int precio, int cV,
            int anio, String estado) {
        Marca = marca;
        Modelo = modelo;
        Puertas = puertas;
        Combustible = combustible;
        Kilometraje = kilometros;
        Precio = precio;
        CV = cV;
        Año = anio;
        Estado = estado;
    }
    



    public Coche(String marca, String modelo, int puertas, String combustible, int kilometraje, int precio, int cV,
            int año, String descripcion, String estado) {
        Marca = marca;
        Modelo = modelo;
        Puertas = puertas;
        Combustible = combustible;
        Kilometraje = kilometraje;
        Precio = precio;
        CV = cV;
        Año = año;
        Descripcion = descripcion;
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

    public int getKilometraje() {
        return Kilometraje;
    }

    public void setKilometraje(int kilometros) {
        Kilometraje = kilometros;
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

    public int getAño() {
        return Año;
    }

    public void setAño(int anio) {
        Año = anio;
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
