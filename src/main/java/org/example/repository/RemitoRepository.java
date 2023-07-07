package org.example.repository;

import org.example.model.Pedido;
import org.example.model.Remito;
import org.example.model.Sucursal;
import org.example.model.Transportista;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RemitoRepository {

    private final List<Remito> remitos;
    private final EmpleadoRepository empleadoRepository;

    int codigo = 0;

    public RemitoRepository() {
        this.empleadoRepository = new EmpleadoRepository();
        this.remitos = new ArrayList<>();
    }

    public void create(Pedido pedido, Sucursal origen, Sucursal destino, Transportista transportista) {
        codigo++;
        Remito remito = new Remito(String.valueOf(codigo), LocalDate.EPOCH, pedido, empleadoRepository.findOne(origen.getSucId() + "01"), empleadoRepository.findOne(destino.getSucId() + "01"), transportista);
        remitos.add(remito);
    }

    public Remito findOne(String id) {
        for (Remito remito : remitos) {
            if (remito.getCodigo().equals(id)) {
                return remito;
            }
        }
        return null;
    }

    public List<Remito> findAll() {
        return remitos;
    }

}
