package org.example.repository;

import org.example.model.Sector;
import org.example.model.Sucursal;

import java.util.ArrayList;
import java.util.List;

public class SectorRepository {
    private final SucursalRepository sucursalRepository;
    private final List<Sector> sectores;

    public SectorRepository() {
        this.sucursalRepository = new SucursalRepository();
        this.sectores = new ArrayList<>();
        this.upload();
    }

    public Sector findOne(String id) {
        for (Sector sector : sectores) {
            if (sector.getSucId().equals(id)) {
                return sector;
            }
        }
        return null;
    }

    public List<Sector> findAll() {
        return sectores;
    }

    public void upload() {
        for (Sucursal sc : sucursalRepository.findAll()) {
            sectores.add(new Sector("Pendiente", sc, sc.getSucId() + "1"));
            sectores.add(new Sector("En Proceso", sc, sc.getSucId() + "2"));
            sectores.add(new Sector("Completo", sc, sc.getSucId() + "3"));
            sectores.add(new Sector("Esperando despacho", sc, sc.getSucId() + "4"));
            sectores.add(new Sector("Enviado a despacho", sc, sc.getSucId() + "5"));
            sectores.add(new Sector("Transito", sc, sc.getSucId() + "6"));
            sectores.add(new Sector("En Sucursal de Destino", sc, sc.getSucId() + "7"));
            sectores.add(new Sector("Entregado", sc, sc.getSucId() + "8"));
            sectores.add(new Sector("Devuelto", sc, sc.getSucId() + "9"));
        }
    }


}
