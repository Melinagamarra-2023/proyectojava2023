package org.example.controller;

import org.example.model.LineaPedido;
import org.example.model.Pedido;
import org.example.service.PedidoService;

import java.util.List;

public class PedidoController implements CRUD<Pedido> {

    private final PedidoService pedidoService;

    public PedidoController() {
        this.pedidoService =  new PedidoService();
    }

    @Override
    public void create(Pedido pedido) {
        pedidoService.create(pedido);
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoService.findAll();
    }

    @Override
    public Pedido findOne(String id) {
        return pedidoService.findOne(id);
    }

    @Override
    public Pedido update(Pedido pedido) {
        return pedidoService.update(pedido);
    }

    @Override
    public void delete(String id) {
        pedidoService.delete(id);
    }

    public void agregarLineaPedido(Pedido pedido, LineaPedido lineaPedido) {
        pedidoService.agregarLineaPedido(pedido, lineaPedido);
    }

    public void setSectorOrigen(Pedido pedido, String id) {
        pedidoService.setSectorOrigen(pedido, id);
    }

    public void setSectorDestino(Pedido pedido, String id) {
        pedidoService.setSectorDestino(pedido, id);
    }
}
