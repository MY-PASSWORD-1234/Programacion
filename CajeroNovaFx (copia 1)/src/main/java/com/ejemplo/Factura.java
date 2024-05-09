package com.ejemplo;

public class Factura {
    private int num_fra;
    private String nif;
    private int num_habitacion;
    private int importe;

    public Factura(int num_fra, String nif, int num_habitacion, int importe) {
        this.num_fra = num_fra;
        this.nif = nif;
        this.num_habitacion = num_habitacion;
        this.importe = importe;
    }

    public int getNum_fra() {
        return num_fra;
    }

    public void setNum_fra(int num_fra) {
        this.num_fra = num_fra;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public int getNum_habitacion() {
        return num_habitacion;
    }

    public void setNum_habitacion(int num_habitacion) {
        this.num_habitacion = num_habitacion;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

}
