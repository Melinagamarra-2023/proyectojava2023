package org.example.service;

import org.example.model.Cliente;

public class InformeService {

    private final ClienteService clienteService;
    private final LineaPedidoService lineaPedidoService;
    private final PedidoService pedidoService;
    private final ProductoService productoService;
    private final ProveedorService proveedorService;
    private final TransportistaService transportistaService;

    public InformeService() {
        this.clienteService = new ClienteService();
        this.lineaPedidoService = new LineaPedidoService();
        this.pedidoService = new PedidoService();
        this.productoService = new ProductoService();
        this.proveedorService = new ProveedorService();
        this.transportistaService = new TransportistaService();
    }

    public void informeCliente(String id) {
        Cliente cliente = clienteService.findOne(id);
        pedidoService.buscarPedidosPorCliente(cliente);
    }

}
