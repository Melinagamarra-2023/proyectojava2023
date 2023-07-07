package org.example.controller;

import org.example.model.Sucursal;
import org.example.service.SucursalService;

import java.util.List;

public class SucursalController implements CRUD<Sucursal> {
    private final SucursalService sucursalService;

    public SucursalController() {
        this.sucursalService = new SucursalService();
    }

    @Override
    public void create(Sucursal sucursal) {
        sucursalService.create(sucursal);
    }

    @Override
    public List<Sucursal> findAll() {
        return sucursalService.findAll();
    }

    @Override
    public Sucursal findOne(String sucId) {
        return sucursalService.findOne(sucId);
    }

    @Override
    public Sucursal update(Sucursal sucursal) {
        sucursalService.update(sucursal);
        return null;
    }

    @Override
    public void delete(String sucId) {
        sucursalService.delete(sucId);
    }
}
