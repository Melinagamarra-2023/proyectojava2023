package org.example.repository;


import org.example.model.Empleado;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepository {
    private final SucursalRepository sucursalRepository;
    private final List<Empleado> empleados;

    public EmpleadoRepository() {
        this.sucursalRepository = new SucursalRepository();
        this.empleados = new ArrayList<>();
        this.upload();
    }

    private void upload() {

        Empleado empleado1 = new Empleado("",sucursalRepository.findOne("7392AB"),"001");
        Empleado empleado2 = new Empleado("",sucursalRepository.findOne("7890KL"),"002");
        Empleado empleado3 = new Empleado("",sucursalRepository.findOne("3456IJ"),"003");
        Empleado empleado4 = new Empleado("",sucursalRepository.findOne("9012GH"),"004");
        Empleado empleado5 = new Empleado("",sucursalRepository.findOne("5678EF"),"005");
        Empleado empleado6 = new Empleado("",sucursalRepository.findOne("1234CD"),"006");
        empleados.add(empleado1);
        empleados.add(empleado2);
        empleados.add(empleado3);
        empleados.add(empleado4);
        empleados.add(empleado5);
        empleados.add(empleado6);

    }


}
