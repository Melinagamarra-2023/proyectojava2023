package org.example.service;

import org.example.model.LineaPedido;
import org.example.model.Pedido;
import org.example.repository.PedidoRepository;
import org.example.repository.SectorRepository;
import org.example.repository.SucursalRepository;

import java.util.List;

public class PedidoService implements CRUD<Pedido> {

    PedidoRepository pedidoRepository = new PedidoRepository();
    SectorRepository sectorRepository = new SectorRepository();
    SucursalRepository sucursalRepository = new SucursalRepository();

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

    public void setSectorOrigen (Pedido pedido, String id) {
        pedidoRepository.setSectorOrigen(pedido, sectorRepository.findOne(id));
    }

    public void setSectorDestino (Pedido pedido, String id) {
        pedidoRepository.setSectorDestino(pedido, sectorRepository.findOne(id));
    }

}
