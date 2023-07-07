package org.example.service;

import org.example.model.*;
import org.example.repository.PedidoRepository;
import org.example.repository.RemitoRepository;
import org.example.repository.SectorRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
            SeguimientoPedido nuevoSeguimiento = new SeguimientoPedido(LocalDate.now(),LocalDateTime.now(),23.89,23.99,pedido);
            pedido.getSeguimientoPedido().add(nuevoSeguimiento);
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
