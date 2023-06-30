package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Ubicacion {
    private String fecha;
    private String hora;
    private Posicion posicion;

}
