package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
public class Sucursal {
    private String sucId;
    private Double latitud;
    private Double longitud;
    private String direccion;
    private String continente;
    private Boolean habilitado;
}
