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
    private final EmpleadoRepository empleadoRepository;
    private final RemitoRepository remitoRepository;


    public PedidoService() {
        this.sectorRepository = new SectorRepository();
        this.pedidoRepository = new PedidoRepository();
        this.lineaPedidoRepository = new LineaPedidoRepository();
        this.sucursalRepository = new SucursalRepository();
        this.empleadoRepository = new EmpleadoRepository();
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

    public List<Pedido> buscarPedidosPorCliente(Cliente cliente) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido pedido : pedidoRepository.findAll()) {
            if (pedido.getCliente().equals(cliente)) {
                resultado.add(pedido);
            }
        }
        return resultado;
    }

    @Override
    public Pedido update(Pedido pedido) {
        return null;
    }

    @Override
    public void delete(String id) {
        Pedido pedido = pedidoRepository.findOne(id);
        pedido.getSeguimientoPedido().add(new SeguimientoPedido(LocalDateTime.now(), 0.0, 0.0, pedido, sectorRepository.findOne(pedido.getSucursalOrigen().getSucId() + 9)));
        for (LineaPedido lineaPedido : pedido.getDetalle()) {
            lineaPedidoRepository.delete(lineaPedido.getCodigo());
        }
    }

    public void agregarLineaPedido(Pedido pedido, LineaPedido lineaPedido) {
        pedidoRepository.agregarLineaPedido(pedido, lineaPedido);
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

    public Remito verRemitoDePedido(String id) {
        for (Remito remito : remitoRepository.findAll()) {
            if (remito.getDetalle().getPedidoId().equals(id)) {
                return remito;
            }
        }
        return null;
    }

    public List<Remito> verRemitosPorTransportista(String id) {
        List<Remito> resultado = new ArrayList<>();
        for (Remito remito : remitoRepository.findAll()) {
            if (remito.getTransportista().getCuit().equals(id)) {
                resultado.add(remito);
            }
        }
        return resultado;
    }

    public void calificarTransportista(String id, int star) {
        remitoRepository.calificarTransportista(id, star);
    }

    public Empleado setEmpleado(String idSucursal) {
        for (Empleado empleado : empleadoRepository.findAll()) {
            if (empleado.getSucursal().getSucId().equals(sucursalRepository.findOne(idSucursal).getSucId())) {
                return empleado;
            }
        }
        return null;
    }

    public List<Sector> verSectores() {
        return sectorRepository.findAll();
    }

    public void siguienteEstado(Pedido pedido) {
        int siguiente = pedido.getSeguimientoPedido().size() + 1;
        if (siguiente < 7) {
            pedido.getSeguimientoPedido().add(new SeguimientoPedido(LocalDateTime.now(), pedido.getSucursalOrigen().getLatitud(), pedido.getSucursalOrigen().getLongitud(), pedido, sectorRepository.findOne(pedido.getSucursalOrigen().getSucId() + siguiente)));
        } else {
            pedido.getSeguimientoPedido().add(new SeguimientoPedido(LocalDateTime.now(), pedido.getSucursalDestino().getLatitud(), pedido.getSucursalDestino().getLongitud(), pedido, sectorRepository.findOne(pedido.getSucursalDestino().getSucId() + siguiente)));
        }
    }

    public void entregar(Pedido pedido) {
        for (LineaPedido lineaPedido : pedido.getDetalle()) {
            lineaPedidoRepository.entregado(lineaPedido.getCodigo());
        }
    }

    public void nuevoTransito(Pedido pedido, Double latitud, Double longitud) {
        pedido.getSeguimientoPedido().add(new SeguimientoPedido(LocalDateTime.now(), latitud, longitud, pedido, sectorRepository.findOne(pedido.getSucursalDestino().getSucId() + 6)));
    }

    public void createLP(LineaPedido lineaPedido) {
        if (lineaPedidoRepository.findOne(lineaPedido.getCodigo()) == null) {
            lineaPedidoRepository.save(lineaPedido);
        }
    }

    public LineaPedido findOneLP(String id) {
        if (lineaPedidoRepository.findOne(id) != null) {
            return lineaPedidoRepository.findOne(id);
        }
        return null;
    }

    public List<LineaPedido> findAllLP() {
        List<LineaPedido> respuesta = new ArrayList<>();
        for (LineaPedido lp : lineaPedidoRepository.findAll()) {
            if (lp.getHabilitado()) {
                respuesta.add(lp);
            }
        }
        return respuesta;
    }

    public List<LineaPedido> buscarPorProveedorLP(String cuit) {
        List<LineaPedido> respuesta = new ArrayList<>();
        for (LineaPedido lp : lineaPedidoRepository.findAll()) {
            if (lp.getPedidoEntregado() && lp.getHabilitado() && lp.getProducto().getProveedor().getCuit().equals(cuit)) {
                respuesta.add(lp);
            }
        }
        return respuesta;
    }

    public LineaPedido updateLP(LineaPedido lineaPedido) {
        if (lineaPedidoRepository.findOne(lineaPedido.getCodigo()) != null) {
            return lineaPedidoRepository.update(lineaPedido);
        }
        return null;
    }

    public void calificarProveedor(String id, int star) {
        lineaPedidoRepository.calificarProveedor(id, star);
    }

}
