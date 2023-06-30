package org.example.repository;

import org.example.model.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class ProveedorRepository {
    private List<Proveedor> proveedores;

    public ProveedorRepository() {
        this.proveedores = new ArrayList<>();
        cargarProveedores();
    }

    private void cargarProveedores() {
        proveedores.add(new Proveedor("proveeA", "Las Hortensias", "12458407577","Paniaguasanty10@gmail.com", "3765101858", true));
        proveedores.add(new Proveedor("proveeB", "Las Rosas", "419516390","Paniaguasanty10@gmail.com", "3765101858", true));
        proveedores.add(new Proveedor("proveeC", "Las Rosas", "64458407589","Paniagua@gmail.com", "3454635", true));


    }

    public void save(Proveedor pr) {
        proveedores.add(pr);
    }

    public List<Proveedor> findAll() {
        return proveedores;
    }

    public Proveedor findOne(String cuit) {
        for (Proveedor pr : proveedores) {
            if (pr.getCuit().equals(cuit)) {
                return pr;
            }
        }
        return null;
    }

    public void delete(String cuit) {
        for (Proveedor pr : proveedores) {
            if (pr.getCuit().equals(cuit) && pr.getHabilitado()) {
                pr.setHabilitado(false); //Esto solo se usa para eliminar, no para modificar.
            }
        }
    }

    public void update(String cuit, Proveedor proveedorActualizado) {
        for (Proveedor pr : proveedores) {
            if (pr.getCuit().equals(cuit)) {
                pr.setNombre(proveedorActualizado.getNombre());
                pr.setDireccion(proveedorActualizado.getDireccion());
                pr.setCorreo(proveedorActualizado.getCorreo());
                pr.setTelefono(proveedorActualizado.getTelefono());
                pr.setHabilitado(proveedorActualizado.getHabilitado());
                break;
            }
        }
    }
}
