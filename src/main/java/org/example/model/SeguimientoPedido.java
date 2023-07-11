package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class SeguimientoPedido {
    private LocalDate fecha;
    private LocalDateTime hora;
    private Double latitud;
    private Double longitud;
    private Pedido pedido;
    private Sector estado;
}
