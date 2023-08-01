package org.example.service;

import org.example.model.LineaPedido;
import org.example.repository.LineaPedidoRepository;

import java.util.ArrayList;
import java.util.List;

public class LineaPedidoService implements CRUD<LineaPedido> {

    private final LineaPedidoRepository lineaPedidoRepository;

    public LineaPedidoService() {
        this.lineaPedidoRepository = new LineaPedidoRepository();
    }

    @Override
    public void create(LineaPedido lineaPedido) {
        if (lineaPedidoRepository.findOne(lineaPedido.getCodigo()) == null) {
            lineaPedidoRepository.save(lineaPedido);
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
        List<LineaPedido> respuesta = new ArrayList<>();
        for (LineaPedido lp : lineaPedidoRepository.findAll()) {
            if (lp.getHabilitado() && !lp.getAgregado()) {
                respuesta.add(lp);
            }
        }
        return respuesta;
    }

    public List<LineaPedido> buscarPorProveedor(String cuit) {
        List<LineaPedido> respuesta = new ArrayList<>();
        for (LineaPedido lp : lineaPedidoRepository.findAll()) {
            if (lp.getPedidoEntregado() && lp.getHabilitado() && lp.getProducto().getProveedor().getCuit().equals(cuit)) {
                respuesta.add(lp);
            }
        }
        return respuesta;
    }

    @Override
    public LineaPedido update(LineaPedido lineaPedido) {
        if (lineaPedidoRepository.findOne(lineaPedido.getCodigo()) != null) {
            return lineaPedidoRepository.update(lineaPedido);
        }
        return null;
    }

    public void agregar(String id) {
        lineaPedidoRepository.agregar(id);
    }

    public void entregar(String id) {
        lineaPedidoRepository.entregado(id);
    }

    @Override
    public void delete(String id) {
        if (lineaPedidoRepository.findOne(id) != null) {
            lineaPedidoRepository.delete(id);
        }
    }

    public void calificarProveedor(String id, int star) {
        lineaPedidoRepository.calificarProveedor(id, star);
    }

}
