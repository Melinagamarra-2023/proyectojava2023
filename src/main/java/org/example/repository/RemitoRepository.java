package org.example.repository;

import org.example.model.Pedido;
import org.example.model.Remito;
import org.example.model.Sucursal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RemitoRepository {

    private final List<Remito> remitos;
    EmpleadoRepository empleadoRepository = new EmpleadoRepository();
    int codigo = 0;

    public RemitoRepository() {
        this.remitos = new ArrayList<>();
    }

    public Remito create(Pedido pedido, Sucursal origen, Sucursal destino) {
        codigo++;
        Remito remito = new Remito(String.valueOf(codigo), LocalDate.EPOCH, pedido, empleadoRepository.findOne(origen.getSucId() + "01"), empleadoRepository.findOne(destino.getSucId() + "01"));
        remitos.add(remito);
        return remito;
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
