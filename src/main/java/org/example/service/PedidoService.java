package org.example.service;

import org.example.model.*;
import org.example.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoService implements CRUD<Pedido> {

    private final SectorRepository sectorRepository;
    private final PedidoRepository pedidoRepository;
    private final LineaPedidoRepository lineaPedidoRepository;
    private final SucursalRepository sucursalRepository;
    private final RemitoRepository remitoRepository;


    public PedidoService() {
        this.sectorRepository = new SectorRepository();
        this.pedidoRepository = new PedidoRepository();
        this.lineaPedidoRepository = new LineaPedidoRepository();
        this.sucursalRepository = new SucursalRepository();
        this.remitoRepository = new RemitoRepository();
    }

    @Override
    public void create(Pedido pedido) {
        if (pedidoRepository.findOne(pedido.getPedidoId()) == null) {
            SeguimientoPedido nuevoSeguimiento = new SeguimientoPedido(LocalDateTime.now(), pedido.getSucursalOrigen().getLatitud(), pedido.getSucursalOrigen().getLongitud(), pedido, sectorRepository.findOne(pedido.getSucursalOrigen().getSucId() + "1"));
            pedido.getSeguimientoPedido().add(nuevoSeguimiento);
            pedidoRepository.save(pedido);
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
        return pedidoRepository.findAll();
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

    public void agregarLineaPedido(Pedido pedido, String id) {
        if (lineaPedidoRepository.findOne(id) != null) {
            lineaPedidoRepository.agregado(lineaPedidoRepository.findOne(id));
        }
        pedidoRepository.agregarLineaPedido(pedido, lineaPedidoRepository.findOne(id));
    }

    public void setSectorOrigen(Pedido pedido, String id) {
        pedidoRepository.setSectorOrigen(pedido, sucursalRepository.findOne(id));
    }

    public void setSectorDestino(Pedido pedido, String id) {
        pedidoRepository.setSectorDestino(pedido, sucursalRepository.findOne(id));
    }

    public void createRemito(Pedido pedido, Sucursal origen, Empleado emisor, Sucursal destino, Empleado receptor, Transportista transportista) {
        remitoRepository.create(pedido, origen, emisor, destino, receptor, transportista);
    }

    public List<Remito> verRemitos() {
        return remitoRepository.findAll();
    }

    public List<Remito> verRemitosPorTransportista(String id) {
        List<Remito> resultado = new ArrayList<>();
        for (Remito remito : remitoRepository.findAll()) {
            if (remito.getEncargado().getCuit().equals(id)) {
                resultado.add(remito);
            }
        }
        return resultado;
    }

    public Empleado setEmpleado(String idSucursal) {
        return remitoRepository.setEmpleado(idSucursal);
    }

    public void siguienteEstado(Pedido pedido) {
        int siguiente = pedido.getSeguimientoPedido().size() + 1;
        if (siguiente < 7) {
            pedido.getSeguimientoPedido().add(new SeguimientoPedido(LocalDateTime.now(), pedido.getSucursalOrigen().getLatitud(), pedido.getSucursalOrigen().getLongitud(), pedido, sectorRepository.findOne(pedido.getSucursalOrigen().getSucId() + siguiente)));
        } else {
            pedido.getSeguimientoPedido().add(new SeguimientoPedido(LocalDateTime.now(), pedido.getSucursalDestino().getLatitud(), pedido.getSucursalDestino().getLongitud(), pedido, sectorRepository.findOne(pedido.getSucursalDestino().getSucId() + siguiente)));
        }
    }

    public void nuevoTransito(Pedido pedido, Double latitud, Double longitud) {
        pedido.getSeguimientoPedido().add(new SeguimientoPedido(LocalDateTime.now(), latitud, longitud, pedido, sectorRepository.findOne(pedido.getSucursalDestino().getSucId() + 6)));
    }

}
