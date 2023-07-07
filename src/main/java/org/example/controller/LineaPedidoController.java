package org.example.controller;

import org.example.model.LineaPedido;
import org.example.service.LineaPedidoService;

import java.util.List;

public class LineaPedidoController implements CRUD<LineaPedido> {

    private final LineaPedidoService lineaPedidoService;

    public LineaPedidoController() {
        this.lineaPedidoService = new LineaPedidoService();
    }

    @Override
    public LineaPedido findOne(String id) {
        return lineaPedidoService.findOne(id);
    }

    @Override
    public LineaPedido update(LineaPedido lineaPedido) {
        lineaPedidoService.update(lineaPedido);
        return null;
    }

    @Override
    public void create(LineaPedido lineaPedido) {
        lineaPedidoService.create(lineaPedido);
    }

    @Override
    public List<LineaPedido> findAll() {
        return lineaPedidoService.findAll();
    }

    @Override
    public void delete(String id) {
        lineaPedidoService.delete(id);
    }

    public void calificarProveedor(LineaPedido lineaPedido, int star) {
        lineaPedidoService.calificarProveedor(lineaPedido, star);
    }

    public void calificarTransportista(LineaPedido lineaPedido, int star) {
        lineaPedidoService.calificarTransportista(lineaPedido, star);
    }
}
