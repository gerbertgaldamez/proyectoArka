package com.enyoi.arkana.stock.inventario.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection = "inventario")
public class Inventario {

    @Id
    private String id;
    private String idProducto;
    private int cantidad;


    public Inventario() {}

    public Inventario(String idProducto, int cantidad) {
        this.id = id;
        this.idProducto = idProducto;
        this.cantidad = cantidad;

    }

    // getters and setters
    public String getId() {

        return id; }
    public void setId(String id) {
        this.id = id;
    }

    public String getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
