package com.upiiz.tienda.dto;

public class CategoriaDto {
    private String nombre;

    public CategoriaDto() {}
    public CategoriaDto(String nombre) { this.nombre = nombre; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
