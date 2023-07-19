package org.example.controller;

import org.example.model.*;
import org.example.service.LineaPedidoService;
import org.example.service.PedidoService;

import java.util.List;

public class PedidoController implements CRUD<Pedido> {

    private final PedidoService pedidoService;
    private final LineaPedidoService lineaPedidoService;

    public PedidoController() {
        this.pedidoService = new PedidoService();
        this.lineaPedidoService = new LineaPedidoService();
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

    public void agregarLineaPedido(Pedido pedido, String id) {
        pedidoService.agregarLineaPedido(pedido, id);
    }

    public void setSucursalOrigen(Pedido pedido, String id) {
        pedidoService.setSectorOrigen(pedido, id);
    }

    public void setSucursalDestino(Pedido pedido, String id) {
        pedidoService.setSectorDestino(pedido, id);
    }

    public void createRemito(Pedido pedido, Sucursal origen, Empleado emisor, Sucursal destino, Empleado receptor, Transportista transportista) {
        pedidoService.createRemito(pedido, origen, emisor, destino, receptor, transportista);
    }
    public List<Remito> verRemitos() {
       return pedidoService.verRemitos();
    }

    public List<Remito> verRemitosPorTransportista(String id) {
        return pedidoService.verRemitosPorTransportista(id);
    }

    public Empleado setEmpleado(String idSucursal) {
        return pedidoService.setEmpleado(idSucursal);
    }

    public LineaPedido findOneLP(String id) {
        return lineaPedidoService.findOne(id);
    }

    public LineaPedido updateLP(LineaPedido lineaPedido) { //solucionar warning
        return lineaPedidoService.update(lineaPedido);
    }

    public void createLP(LineaPedido lineaPedido) {
        lineaPedidoService.create(lineaPedido);
    }

    public List<LineaPedido> findAllLP() {
        return lineaPedidoService.findAll();
    }

    public void deleteLP(String id) {
        lineaPedidoService.delete(id);
    }

    public void calificarProveedor(LineaPedido lineaPedido, int star) {
        lineaPedidoService.calificarProveedor(lineaPedido, star);
    }

    public void siguienteEstado(Pedido pedido) {
        pedidoService.siguienteEstado(pedido);
    }

    public void nuevoTransito(Pedido pedido, Double latitud, Double longitud) {
        pedidoService.nuevoTransito(pedido, latitud, longitud);
    }

}
