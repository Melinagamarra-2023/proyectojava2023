package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Empleado {
    private String nombre;
    private Sucursal sucursal;
    private String id;
}
