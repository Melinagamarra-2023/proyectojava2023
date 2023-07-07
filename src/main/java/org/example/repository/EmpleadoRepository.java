package org.example.repository;

import org.example.model.Empleado;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepository {

    private final List<Empleado> empleados;
    SucursalRepository sucursalRepository = new SucursalRepository();

    public EmpleadoRepository() {
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

    private void upload() {

        Empleado empleado01 = new Empleado("carlitos", sucursalRepository.findOne("7392AB"), "7392AB01");
        Empleado empleado02 = new Empleado("carlita", sucursalRepository.findOne("1234CD"), "1234CD01");

        empleados.add(empleado01);
        empleados.add(empleado02);
    }

}
