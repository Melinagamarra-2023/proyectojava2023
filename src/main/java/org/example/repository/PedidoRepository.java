package org.example.repository;

import org.example.model.LineaPedido;
import org.example.model.Pedido;
import org.example.model.*;

import java.util.ArrayList;
import java.util.List;

public class PedidoRepository implements CRUD<Pedido> {

    private final SucursalRepository sucursalRepository;
    private final EmpleadoRepository empleadoRepository;
    private final ClienteRepository clienteRepository;
    private final List<Pedido> pedidos;

    public PedidoRepository() {
        this.sucursalRepository = new SucursalRepository();
        this.empleadoRepository = new EmpleadoRepository();
        this.clienteRepository = new ClienteRepository();
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
    public void save(Pedido pedido) {
        pedidos.add(pedido);
    }

    @Override
    public void delete(String id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getPedidoId().equals(id)) {
                pedido.setSeguimientoPedido(null);
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
        Pedido prueba = new Pedido("01", sucursalRepository.findOne("1234"), sucursalRepository.findOne("7392"), new ArrayList<>(), new ArrayList<>(), clienteRepository.findOne("123456789"), empleadoRepository.findOne("123401"));
        pedidos.add(prueba);
    }

    public void setSectorOrigen(Pedido pedido, Sucursal sucursal) {
        pedido.setSucursalOrigen(sucursal);
    }

    public void setSectorDestino(Pedido pedido, Sucursal sucursal) {
        pedido.setSucursalDestino(sucursal);
    }
}
