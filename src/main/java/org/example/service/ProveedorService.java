package org.example.service;

import org.example.model.Proveedor;
import org.example.repository.ProveedorRepository;

import java.util.ArrayList;
import java.util.List;

public class ProveedorService {
    ProveedorRepository proveedorRepository;
    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }
    public void create(Proveedor nuevoCliente) {
        Proveedor proveedorExiste = proveedorRepository.findOne(nuevoCliente.getCuit());
        if(proveedorExiste == null){
            proveedorRepository.save(nuevoCliente);
        }
    }

    public void update(Proveedor pr) {
        Proveedor proveedorAnterior = proveedorRepository.findOne(pr.getCuit());
        if (proveedorAnterior != null) {
            proveedorRepository.update(pr);
            proveedorRepository.findOne(pr.getCuit());
        }
    }

    public void delete(String cuit){
        if(proveedorRepository.findOne(cuit) != null){
            proveedorRepository.delete(cuit);
        }
    }

    public Proveedor findOne(String cuit) {
        for (Proveedor pr : proveedorRepository.findAll()) {
            if (pr.getCuit().equals(cuit)) {
                return pr;
            }
        }
        return null;
    }

    public List<Proveedor> findAll() {
        List<Proveedor> resultado = new ArrayList<>();
        for (Proveedor pr : this.proveedorRepository.findAll()) {
            if (pr.getHabilitado()) {
                resultado.add(pr);
            }
        }
        return resultado;
    }
}
