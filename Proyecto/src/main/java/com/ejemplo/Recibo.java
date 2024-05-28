package com.ejemplo;

public class Recibo {
    private String coche;
    private int precio_base;
    private int iva;
    private int cambio_nombre;
    private int precio_final;
    private String fecha;
    public Recibo(String coche, int precio_base, int iva, int cambio_nombre, int precio_final, String fecha) {
        this.coche = coche;
        this.precio_base = precio_base;
        this.iva = iva;
        this.cambio_nombre = cambio_nombre;
        this.precio_final = precio_final;
        this.fecha = fecha;
    }
    public String getCoche() {
        return coche;
    }
    public void setCoche(String coche) {
        this.coche = coche;
    }
    public int getPrecio_base() {
        return precio_base;
    }
    public void setPrecio_base(int precio_base) {
        this.precio_base = precio_base;
    }
    public int getIva() {
        return iva;
    }
    public void setIva(int iva) {
        this.iva = iva;
    }
    public int getCambio_nombre() {
        return cambio_nombre;
    }
    public void setCambio_nombre(int cambio_nombre) {
        this.cambio_nombre = cambio_nombre;
    }
    public int getPrecio_final() {
        return precio_final;
    }
    public void setPrecio_final(int precio_final) {
        this.precio_final = precio_final;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
