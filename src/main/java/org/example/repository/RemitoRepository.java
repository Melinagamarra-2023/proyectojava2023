package org.example.repository;

import org.example.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RemitoRepository {

    private final List<Remito> remitos;
    private final EmpleadoRepository empleadoRepository;
    private final SucursalRepository sucursalRepository;
    private int codigo;

    public RemitoRepository() {
        this.remitos = new ArrayList<>();
        this.empleadoRepository = new EmpleadoRepository();
        this.sucursalRepository = new SucursalRepository();
        this.codigo = 0;
    }

    public void create(Pedido pedido, Sucursal origen, Empleado emisor, Sucursal destino, Empleado receptor, Transportista transportista) {
        codigo++;
        Remito remito = new Remito(String.valueOf(codigo), LocalDateTime.now(), pedido, origen, emisor, destino, receptor, transportista, 0);
        remitos.add(remito);
    }

    public Empleado setEmpleado(String idSucursal) {
        for (Empleado empleado : empleadoRepository.findAll()) {
            if (empleado.getSucursal().equals(sucursalRepository.findOne(idSucursal))) {
                return empleado;
            }
        }
        return null;
    }

    public Remito findOne(String id) {
        for (Remito remito : remitos) {
            if (remito.getCodigo().equals(id)) {
                return remito;
            }
        }
        return null;
    }

    public void calificarTransportista(String id, int star) {
        this.findOne(id).setCalificaTransportista(star);
    }

    public List<Remito> findAll() {
        return remitos;
    }

}
