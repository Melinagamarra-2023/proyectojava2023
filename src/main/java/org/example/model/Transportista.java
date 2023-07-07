package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Transportista {
    private String nombre;
    private String cuit;
    private String telefono;
    private Boolean habilitado;
    private Boolean terrestre;
    private Boolean maritimo;
    private Boolean aereo;
}
