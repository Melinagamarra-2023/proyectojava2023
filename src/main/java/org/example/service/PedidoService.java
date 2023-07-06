package org.example.service;

import org.example.model.LineaPedido;
import org.example.model.Pedido;
import org.example.repository.PedidoRepository;

import java.util.List;

public class PedidoService implements CRUD<Pedido> {

    PedidoRepository pedidoRepository = new PedidoRepository();

    @Override
    public void create(Pedido pedido) {
        if (pedidoRepository.findOne(pedido.getPedidoId()) == null) {
            pedidoRepository.create(pedido);
        }
    }

    @Override
    public Pedido findOne(String id) {
        if (pedidoRepository.findOne(id) != null) {
            return pedidoRepository.findOne(id);
        }
        return null;
    }

    @Override
    public List<Pedido> findAll() {
        if (pedidoRepository.findAll() != null) {
            return pedidoRepository.findAll();
        }
        return null;
    }

    @Override
    public Pedido update(Pedido pedido) {
        return null;
    }

    @Override
    public void delete(String id) {
        if (pedidoRepository.findOne(id) != null) {
            pedidoRepository.findOne(id);
        }
    }

    public void añadirLineaPedido(Pedido pedido, LineaPedido lineaPedido) {
        pedidoRepository.añadirLineaPedido(pedido, lineaPedido);
    }


}
