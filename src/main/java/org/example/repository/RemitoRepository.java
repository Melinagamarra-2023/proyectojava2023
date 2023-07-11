package org.example.repository;

import org.example.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RemitoRepository {

    private final List<Remito> remitos;
    private int codigo;

    public RemitoRepository() {
        this.remitos = new ArrayList<>();
        this.codigo = 0;
    }

    public void create(Pedido pedido, Sucursal origen, Empleado emisor, Sucursal destino, Empleado receptor, Transportista transportista) {
        codigo++;
        Remito remito = new Remito(String.valueOf(codigo), LocalDateTime.now(), pedido, origen, emisor, destino, receptor, transportista);
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
