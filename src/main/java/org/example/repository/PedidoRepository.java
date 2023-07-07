package org.example.repository;

import org.example.model.LineaPedido;
import org.example.model.Pedido;
import org.example.model.*;

import java.util.ArrayList;
import java.util.List;

public class PedidoRepository implements CRUD<Pedido> {

    private final EmpleadoRepository empleadoRepository;
    private final List<Pedido> pedidos;

    public PedidoRepository() {
        this.empleadoRepository = new EmpleadoRepository();
        this.pedidos = new ArrayList<>();
    }


    @Override
    public Pedido findOne(String id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getPedidoId().equals(id)) {
                return pedido;
            }
        }
        return null;
    }

    @Override
    public List<Pedido> findAll() {
        return pedidos;
    }

    @Override
    public void create(Pedido pedido) {
        pedidos.add(pedido);
    }

    @Override
    public void delete(String id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getPedidoId().equals(id)) {
                pedido.setEstado(null);
            }
        }
    }

    @Override
    public Pedido update(Pedido pedidoActualizado) {
        return null;
    }

    public void agregarLineaPedido(Pedido pedido, LineaPedido lineaPedido) {
        pedido.getDetalle().add(lineaPedido);
    }

    @Override
    public void upload() {
        Pedido prueba = new Pedido(null, null, null, null, null, null, null, null);
        pedidos.add(prueba);
    }

    public void setSectorOrigen(Pedido pedido, Sector sector) {
        pedido.setSectorOrigen(sector);
        pedido.setEncargado(empleadoRepository.findOne(sector.getSucursal().getSucId() + "01"));
    }

    public void setSectorDestino(Pedido pedido, Sector sector) {
        pedido.setSectorDestino(sector);
    }
}
