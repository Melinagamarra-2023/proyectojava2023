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
    private Float ancho;
    private Float alto;
    private Float profundidad;
    private Float peso;
    private Categoria categoria;
    private Proveedor proveedor;

}
