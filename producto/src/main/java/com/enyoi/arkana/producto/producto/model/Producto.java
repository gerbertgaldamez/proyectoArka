package com.enyoi.arkana.producto.producto.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productos")
public class Producto {

    @Id
    private String id;
    private String nombre;
    private String marca;
    private String categoria;
    private String descripcion;
    private Double precio;
    private Integer stockDisponible;


    public Producto() {}

    public Producto(String nombre, String marca, String categoria, Double precio, Integer stockDisponible, String descripcion) {
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.precio = precio;
        this.stockDisponible = stockDisponible;
        this.descripcion = descripcion;

    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) { this.marca = marca; }

    public String getCategoria() {
        return categoria; }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stockDisponible;
    }
    public void setStock(Integer stockDisponible) {
        this.stockDisponible = stockDisponible;
    }
    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
