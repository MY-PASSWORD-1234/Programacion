package com.ejemplo;

public class Cuenta {
    private int num_cta;
    private String nif;
    private String saldo;

    public Cuenta(int num_cta, String nif, String saldo) {
        this.num_cta = num_cta;
        this.nif = nif;
        this.saldo = saldo;
    }

    public int getNum_cta() {
        return num_cta;
    }

    public String getNif() {
        return nif;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setNum_cta(int num_cta) {
        this.num_cta = num_cta;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

}
