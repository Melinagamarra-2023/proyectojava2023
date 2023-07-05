package org.example.controller;

import org.example.model.Proveedor;
import org.example.service.ProveedorService;

import java.util.List;

public class ProveedorController {
    ProveedorService proveedorService = new ProveedorService();


    public void create(Proveedor nuevoProveedor) {
        proveedorService.create(nuevoProveedor);
    }

    public List<Proveedor> findAll() {
        return proveedorService.findAll();
    }

    public Proveedor findOne(String cuit) {
        return proveedorService.findOne(cuit);
    }

    public void update(Proveedor pr) {
        proveedorService.update(pr);
    }

    public void delete(String cuit) {
        proveedorService.delete(cuit);
    }
}
