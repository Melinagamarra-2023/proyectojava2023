package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Transportista {
    private String nombre;
    private String apellido;
    private String cuit;
    private String direccion;
    private String correo;
    private String telefono;
    private Transporte tipoDeTransporte;
    private Boolean habilitado;
    private Ubicacion ubicacion;
    /*
    private Double latitud; PREGUNTAR
    private Double longitud;

     */


}
