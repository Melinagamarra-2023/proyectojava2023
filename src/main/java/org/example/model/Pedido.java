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
    private Sector sectorOrigen;
    private Sector sectorDestino;
    private List<Ubicacion> ubicacion;
    private List<LineaPedido> detalle;
    private Cliente cliente;
    private Estado estado;
    private Empleado encargado;
}
