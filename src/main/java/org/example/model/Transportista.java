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
    private Transporte tipoDeTransporte;
    private Boolean habilitado;
    private Ubicacion ubicacion;



}
