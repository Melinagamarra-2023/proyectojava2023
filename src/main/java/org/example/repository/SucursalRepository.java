package org.example.repository;


import org.example.model.Sucursal;

import java.util.ArrayList;
import java.util.List;

public class SucursalRepository implements CRUD<Sucursal> {

    private final List<Sucursal> sucursales;

    public SucursalRepository() {
        this.sucursales = new ArrayList<>();
        this.upload();
    }

    @Override
    public Sucursal findOne(String sucId) {
        for (Sucursal sucu : sucursales) {
            if (sucu.getSucId().equals(sucId)) {
                return sucu;
            }
        }
        return null;
    }

    @Override
    public List<Sucursal> findAll() {
        return sucursales;
    }

    @Override
    public void save(Sucursal sucursal) {
        sucursales.add(sucursal);
    }

    @Override
    public void delete(String codSuc) {
        if (findOne(codSuc) != null) {
            findOne(codSuc).setHabilitado(false);
        }
    }

    @Override
    public Sucursal update(Sucursal sucursalActualizada) {
        if (findOne(sucursalActualizada.getSucId()) != null) {
            findOne(sucursalActualizada.getSucId()).setLongitud(sucursalActualizada.getLongitud());
            findOne(sucursalActualizada.getSucId()).setLatitud(sucursalActualizada.getLatitud());
            findOne(sucursalActualizada.getSucId()).setDireccion(sucursalActualizada.getDireccion());
            findOne(sucursalActualizada.getSucId()).setContinente(sucursalActualizada.getContinente());
            findOne(sucursalActualizada.getSucId()).setHabilitado(sucursalActualizada.getHabilitado());
        }
        return null;
    }

    public void upload() {

        Sucursal sucursalA = new Sucursal("7392", 40.7128, -74.0060, "NuevaYork", "America del Norte", true);
        Sucursal sucursalB = new Sucursal("1234", 37.7749, -122.4194, "San Francisco", "America del Norte", true);
        Sucursal sucursalC = new Sucursal("5678", 51.5074, -0.1278, "London", "Europe", true);
        Sucursal sucursalD = new Sucursal("9012", -33.8688, 151.2093, "Sydney", "Australia", true);
        Sucursal sucursalE = new Sucursal("3456", 35.6895, 139.6917, "Tokyo", "Asia", true);
        Sucursal sucursalF = new Sucursal("7890", -23.5505, -46.6333, "Sao Paulo", "South America", true);

        sucursales.add(sucursalA);
        sucursales.add(sucursalB);
        sucursales.add(sucursalC);
        sucursales.add(sucursalD);
        sucursales.add(sucursalF);
        sucursales.add(sucursalE);
    }
}













