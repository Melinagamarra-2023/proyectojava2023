package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Proveedor {
    private String nombre;
    private String direccion;
    private String cuit;
    private String correo;
    private String telefono;
    private Boolean habilitado;
}
