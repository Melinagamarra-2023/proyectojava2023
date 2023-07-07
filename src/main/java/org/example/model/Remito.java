package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Remito {
    private String codigo;
    private LocalDate emision;
    private Pedido detalle;
    private Empleado empleadoEmisor;
    private Empleado empleadoReceptor;
}
