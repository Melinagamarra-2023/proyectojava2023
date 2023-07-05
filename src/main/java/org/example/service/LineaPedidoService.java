package org.example.service;

import org.example.model.LineaPedido;
import org.example.repository.LineaPedidoRepository;

import java.util.List;

public class LineaPedidoService implements CRUD<LineaPedido> {

    LineaPedidoRepository lineaPedidoRepository = new LineaPedidoRepository();

    @Override
    public void create(LineaPedido lineaPedido) {
        if (lineaPedidoRepository.findOne(lineaPedido.getCodigo()) == null) {
            lineaPedidoRepository.create(lineaPedido);
        }
    }

    @Override
    public LineaPedido findOne(String id) {
        if (lineaPedidoRepository.findOne(id) != null) {
            return lineaPedidoRepository.findOne(id);
        }
        return null;
    }

    @Override
    public List<LineaPedido> findAll() {
        return lineaPedidoRepository.findAll();
    }

    @Override
    public LineaPedido update(LineaPedido lineaPedido) {
        if (lineaPedidoRepository.findOne(lineaPedido.getCodigo()) != null) {
            return lineaPedidoRepository.update(lineaPedido);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        if (lineaPedidoRepository.findOne(id) != null) {
            lineaPedidoRepository.delete(id);
        }
    }

    public void calificarProveedor(LineaPedido lineaPedido, int star) {
        if (lineaPedidoRepository.findOne(lineaPedido.getCodigo()) != null) {
            lineaPedidoRepository.calificarProveedor(lineaPedido, star);
        }
    }

    public void calificarTransportista(LineaPedido lineaPedido, int star) {
        if (lineaPedidoRepository.findOne(lineaPedido.getCodigo()) != null) {
            lineaPedidoRepository.calificarTransportista(lineaPedido, star);
        }
    }
}
