package org.example.controller;

import org.example.model.LineaPedido;
import org.example.service.LineaPedidoService;

import java.util.List;

public class LineaPedidoController {

    LineaPedidoService lineaPedidoService = new LineaPedidoService();

    public void create(LineaPedido lineaPedido) {
        lineaPedidoService.create(lineaPedido);
    }

    public LineaPedido findOne(int id) {
        return lineaPedidoService.findOne(id);
    }

    public List<LineaPedido> findAll() {
        return lineaPedidoService.findAll();
    }

    public void update(LineaPedido lineaPedido) {
        lineaPedidoService.update(lineaPedido);
    }

    public void delete(LineaPedido lineaPedido) {
        lineaPedidoService.delete(lineaPedido);
    }
}
