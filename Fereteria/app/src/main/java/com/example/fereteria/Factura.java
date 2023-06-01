package com.example.fereteria;

public class Factura {
    private long id;
    private String codigoFactura;
    private String codigoPedido;
    private String fechaFactura;

    public Factura() {
    }

    public Factura(String codigoFactura, String codigoPedido, String fechaFactura) {
        this.codigoFactura = codigoFactura;
        this.codigoPedido = codigoPedido;
        this.fechaFactura = fechaFactura;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(String codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }
}
