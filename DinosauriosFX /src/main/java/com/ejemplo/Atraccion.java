package com.ejemplo;

public class Atraccion {
    private String nombre ;
    private Dinosaurio dino ;
    private int capacidad ;
    private int edad;
    private Zona zona ;
    public Atraccion(String nombre, Dinosaurio dino, int capacidad, int edad, Zona zona) {
        this.nombre = nombre;
        this.dino = dino;
        this.capacidad = capacidad;
        this.edad = edad;
        this.zona = zona;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Dinosaurio getDino() {
        return dino;
    }
    public void asignarDino(Dinosaurio dino) {
        this.dino = dino;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public Zona getZona() {
        return zona;
    }
    public void asignarZona(Zona zona) {
        this.zona = zona;
    }
    
    

}
