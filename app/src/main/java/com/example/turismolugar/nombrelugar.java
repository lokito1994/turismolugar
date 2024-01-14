package com.example.turismolugar;

public class nombrelugar {
    private String nombre;
    private String categoria;
    private String telefono;

    public nombrelugar(String nombre, String categoria, String telefono) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getTelefono() {
        return telefono;
    }
}
