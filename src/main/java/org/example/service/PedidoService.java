package org.example.service;

import org.example.model.LineaPedido;
import org.example.model.Pedido;
import org.example.model.Sucursal;
import org.example.model.Transportista;
import org.example.repository.PedidoRepository;
import org.example.repository.RemitoRepository;
import org.example.repository.SectorRepository;

import java.util.ArrayList;
import java.util.List;

public class PedidoService implements CRUD<Pedido> {
    private final PedidoRepository pedidoRepository;
    private final SectorRepository sectorRepository;
    private final RemitoRepository remitoRepository;

    public PedidoService() {
        this.pedidoRepository = new PedidoRepository();
        this.sectorRepository = new SectorRepository();
        this.remitoRepository = new RemitoRepository();
    }

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
        return new ArrayList<>();
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

    public void agregarLineaPedido(Pedido pedido, LineaPedido lineaPedido) {
        pedidoRepository.agregarLineaPedido(pedido, lineaPedido);
    }

    public void setSectorOrigen(Pedido pedido, String id) {
        pedidoRepository.setSectorOrigen(pedido, sectorRepository.findOne(id));
    }

    public void setSectorDestino(Pedido pedido, String id) {
        pedidoRepository.setSectorDestino(pedido, sectorRepository.findOne(id));
    }

    public void createRemito(Pedido pedido, Sucursal origen, Sucursal destino, Transportista transportista) {
        remitoRepository.create(pedido, origen, destino, transportista);
    }

}
