package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor

public class Pedido {
    private String pedidoId;
    private Sucursal sucursalOrigen;
    private Sucursal sucursalDestino;
    private List<SeguimientoPedido> seguimientoPedido;
    private List<LineaPedido> detalle;
    private Cliente cliente;
    private Empleado encargado;
}