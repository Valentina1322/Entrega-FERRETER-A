package com.example.fereteria;

public class Producto {
    private String codProducto;
    private String descripcion;
    private String valor;

    public Producto(String codProducto, String descripcion, String valor) {
        this.codProducto = codProducto;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
