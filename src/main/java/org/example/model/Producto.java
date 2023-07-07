package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor

public class Producto {
    private String id;
    private String nombre;
    private String descripcion;
    private Double ancho;
    private Double alto;
    private Double profundidad;
    private Double peso;
    private Categoria categoria;
    private Proveedor proveedor;
    private Boolean habilitado;

}
