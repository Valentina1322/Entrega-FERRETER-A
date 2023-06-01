package com.example.fereteria;

public class Client {
    private int id;
    private String cedula;
    private String nombre;
    private String direccion;
    private String telefono;

    public Client(String cedula, String nombre, String direccion, String telefono) {
        //this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() { return nombre; }

    public String getDireccion() { return direccion; }

    public String getTelefono() {
        return telefono;
    }
}
