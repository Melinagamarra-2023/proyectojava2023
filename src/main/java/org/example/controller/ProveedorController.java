package org.example.controller;

import org.example.model.Proveedor;
import org.example.repository.ProveedorRepository;
import org.example.service.ProveedorService;

import java.util.List;

public class ProveedorController {
    private final ProveedorService proveedorService;
    private final ProveedorRepository proveedorRepository;
    public ProveedorController(ProveedorService proveedorService, ProveedorRepository proveedorRepository) {
        this.proveedorService = proveedorService;
        this.proveedorRepository = proveedorRepository;
    }
    public void crearProveedor(Proveedor nuevoProveedor) {
        proveedorService.crearProveedor(nuevoProveedor);
    }
    public List<Proveedor> buscarProveedores() {
        return proveedorService.buscarProveedores();
    }
    public Proveedor buscarPorCuit(String cuit) {
        return proveedorService.buscarPorCuit(cuit);
    }
    public void modificarProveedor(Proveedor pr) {
        proveedorService.modificarProveedor(pr);
    }
    public void eliminarProveedor(String cuit) {
        proveedorService.eliminarProveedor(cuit);
    }
}
