package org.example.service;

import org.example.repository.*;
import org.example.util.CalcularDistancia;

public class InformeService {

    private final PedidoRepository pedidoRepository;
    private final LineaPedidoRepository lineaPedidoRepository;
    private final SucursalRepository sucursalRepository;

    public InformeService() {
        this.pedidoRepository = new PedidoRepository();
        this.lineaPedidoRepository = new LineaPedidoRepository();
        this.sucursalRepository = new SucursalRepository();
    }

}
