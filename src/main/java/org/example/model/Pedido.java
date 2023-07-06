package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor

public class Pedido {

    private String pedidoId;
    private Sector sectorOrigen;
    private Sector sectorDestino;
    private Ubicacion ubicacion;
    private LineaPedido detalle;
    private Cliente cliente;

}
