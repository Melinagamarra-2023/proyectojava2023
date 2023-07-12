package org.example.repository;

import org.example.model.Empleado;
import org.example.model.Sucursal;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepository {

    private final List<Empleado> empleados;
    private final SucursalRepository sucursalRepository;

    public EmpleadoRepository() {
        this.sucursalRepository = new SucursalRepository();
        this.empleados = new ArrayList<>();
        this.upload();
    }
  
    public Empleado findOne(String id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId().equals(id)) {
                return empleado;
            }
        }
        return null;
    }

    public List<Empleado> findAll() {
        return empleados;
    }

    private void upload() {
        int codigo= 0;
        for (Sucursal sc : sucursalRepository.findAll()) {
            codigo++;
            empleados.add(new Empleado("nombre" + codigo, sc, sc.getSucId() + codigo));
            codigo++;
            empleados.add(new Empleado("nombre" + codigo, sc, sc.getSucId() + codigo));
            codigo++;
            empleados.add(new Empleado("nombre" + codigo, sc, sc.getSucId() + codigo));
            codigo++;
            empleados.add(new Empleado("nombre" + codigo, sc, sc.getSucId() + codigo));
        }
    }
  
}
