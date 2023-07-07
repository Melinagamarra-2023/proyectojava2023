package org.example.service;

import org.example.model.Proveedor;
import org.example.repository.ProveedorRepository;

import java.util.ArrayList;
import java.util.List;

public class ProveedorService implements CRUD<Proveedor> {
    private final ProveedorRepository proveedorRepository;

    public ProveedorService() {
        this.proveedorRepository = new ProveedorRepository();
    }

    @Override
    public void create(Proveedor nuevoProveedor) {
        Proveedor proveedorExiste = proveedorRepository.findOne(nuevoProveedor.getCuit());
        if (proveedorExiste == null) {
            proveedorRepository.create(nuevoProveedor);
        }
    }

    @Override
    public Proveedor findOne(String cuit) {
        for (Proveedor pr : proveedorRepository.findAll()) {
            if (pr.getCuit().equals(cuit)) {
                return pr;
            }
        }
        return null;
    }

    @Override
    public List<Proveedor> findAll() {
        List<Proveedor> resultado = new ArrayList<>();
        for (Proveedor pr : this.proveedorRepository.findAll()) {
            if (pr.getHabilitado()) {
                resultado.add(pr);
            }
        }
        return resultado;
    }

    @Override
    public Proveedor update(Proveedor pr) {
        Proveedor proveedorAnterior = proveedorRepository.findOne(pr.getCuit());
        if (proveedorAnterior != null) {
            proveedorRepository.update(pr);
            proveedorRepository.findOne(pr.getCuit());
        }
        return null;
    }

    @Override
    public void delete(String cuit) {
        if (proveedorRepository.findOne(cuit) != null) {
            proveedorRepository.delete(cuit);
        }
    }
}
