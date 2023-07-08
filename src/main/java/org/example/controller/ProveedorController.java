package org.example.controller;

import org.example.model.Proveedor;
import org.example.service.ProveedorService;

import java.util.List;

public class ProveedorController implements CRUD<Proveedor> {
    private final ProveedorService proveedorService;

    public ProveedorController() {
        this.proveedorService = new ProveedorService();
    }

    @Override
    public void create(Proveedor nuevoProveedor) {
        proveedorService.create(nuevoProveedor);
    }

    @Override
    public List<Proveedor> findAll() {
        return proveedorService.findAll();
    }

    @Override
    public Proveedor findOne(String cuit) {
        return proveedorService.findOne(cuit);
    }

    @Override
    public Proveedor update(Proveedor pr) {
        return proveedorService.update(pr);
    }

    @Override
    public void delete(String cuit) {
        proveedorService.delete(cuit);
    }
}
