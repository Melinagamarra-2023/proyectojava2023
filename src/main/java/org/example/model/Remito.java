package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Remito {
    private String codigo;
    private LocalDateTime emision;
    private Pedido detalle;
    private Sucursal origen;
    private Empleado empleadoEmisor;
    private Sucursal destino;
    private Empleado empleadoReceptor;
    private Transportista transportista;
}
