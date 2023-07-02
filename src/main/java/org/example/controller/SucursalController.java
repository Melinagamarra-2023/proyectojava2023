package org.example.controller;

import org.example.model.Sucursal;
import org.example.service.SucursalService;

import java.util.List;

public class SucursalController implements CRUD<Sucursal, Sucursal> {
    SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
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
        return sucursalService.update(sucursal);
    }

    @Override
    public Sucursal delete(String sucId) {

        return sucursalService.delete(sucId);
    }
}
