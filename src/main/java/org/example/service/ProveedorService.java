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
    public void crearProveedor(Proveedor nuevoCliente) {
        Proveedor proveedorExiste = proveedorRepository.findOne(nuevoCliente.getCuit()); //tiene que ser findOne
        if(proveedorExiste == null){
            proveedorRepository.save(nuevoCliente);
        }
    }

    public void modificarProveedor(Proveedor pr) {
        Proveedor proveedorAnterior = proveedorRepository.findOne(pr.getCuit()); //tiene que ser findOne
        if (proveedorAnterior != null) {
            proveedorRepository.update(pr.getCuit(), pr);
            proveedorRepository.findOne(pr.getCuit());
        }
    }

    public void eliminarProveedor(String cuit){
        if(proveedorRepository.findOne(cuit) != null){
            proveedorRepository.delete(cuit);
        }
    }

    public Proveedor buscarPorCuit(String cuit) {
        for (Proveedor pr : proveedorRepository.findAll()) {
            if (pr.getCuit().equals(cuit)) {
                return pr; //función cambiada
            }
        }
        return null;
    }

    public List<Proveedor> buscarProveedores() {
        List<Proveedor> resultado = new ArrayList<>();
        for (Proveedor pr : this.proveedorRepository.findAll()) {
            if (pr.getHabilitado()) {
                resultado.add(pr);
            }
        }
        return resultado;
    }
}
