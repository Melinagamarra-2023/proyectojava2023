package org.example.view;

import org.example.controller.SucursalController;

import org.example.model.Sucursal;

import java.util.Scanner;

public class MenuSucursal {

    SucursalController sucursalController = new SucursalController();
    Scanner input = new Scanner(System.in);
    int option = 99;

    public int seleccionarOpcion() {
        option = 99;
        System.out.println("""
                
                ----- MENÚ SUCURSALES -----
                Seleccione la opción:
                1. Agregar una Sucursal.
                2. Modificar una Sucursal.
                3. Eliminar una Sucursal.
                4. Buscar por codigo una Sucursal.
                5. Obtener lista de todos las Sucursales.
                0. Salir.
                """);
        option = input.nextInt();
        return option;
    }

    public void agregarUnaSucursal() {
        System.out.println("\nporfavor escriba los datos de la sucursal");
        System.out.println("sucId: ");
        String sucId = input.next();
        Sucursal sucursalExistente = sucursalController.findOne(sucId);
        while (sucursalExistente != null) {
            System.out.println("Ya existe la sucursal con el Codigo ingresado, por favor vuelva a intentar:");
            System.out.print("sucId: ");
            sucId = input.next();
            sucursalExistente = sucursalController.findOne(sucId);
        }
        System.out.println("Latitud: ");
        Double latitud = input.nextDouble();
        System.out.println("Longitud: ");
        Double longitud = input.nextDouble();
        System.out.println("Dirrecion: ");
        String direccion = input.next();
        System.out.println("Continente: ");
        String continente = input.next();

        Sucursal nuevaSucursal = new Sucursal(sucId, longitud, latitud, direccion, continente, true);
        sucursalController.create(nuevaSucursal);
        System.out.println("La Sucursal: " + nuevaSucursal.getSucId() + "ha sido añadida con exito");

    }

    public void modificarSucursal() {
        System.out.println("\nIngrese el Codigo de la Sucursal a modificar:");
        String sucId = input.next();
        Sucursal sucursalModificar = sucursalController.findOne(sucId);
        if (sucursalModificar != null) {
            System.out.println("Ingrese los nuevos datos de la sucursal");
            System.out.print("Latitud: ");
            Double latitudModificar = input.nextDouble();
            System.out.print("Longitud: ");
            Double longitudModificar = input.nextDouble();
            System.out.print("Dirección: ");
            String direccionModificar = input.next();
            System.out.print("Continente: ");
            String continenteModificar = input.next();
            sucursalModificar.setLatitud(latitudModificar);
            sucursalModificar.setLongitud(longitudModificar);
            sucursalModificar.setDireccion(direccionModificar);
            sucursalModificar.setContinente(continenteModificar);
            System.out.println("La Sucursal " + sucursalModificar.getSucId() + " ha sido modificada con éxito.");
        } else {
            System.out.println("No se encontró ninguna Sucursal con el Codigo proporcionado.");
        }
    }

    public void deshablitarSucursal() {
        System.out.println("\nIngrese el Codido de la sucursal a eliminar:");
        String sucIdEliminar = input.next();
        Sucursal sucursalEliminar = sucursalController.findOne(sucIdEliminar);
        if (sucursalEliminar != null) {
            sucursalController.delete(sucIdEliminar);
            System.out.println("La sucursal " + sucursalEliminar.getSucId() + " ha sido eliminada con éxito.");
        } else {
            System.out.println("No se encontró ninguna sucursal con el codigo proporcionado.");
        }
    }

    public void buscarSucursalPorCodigo() {
        System.out.println("\nIngrese el Codigo de la sucursal a buscar:");
        String sucIdBuscado = input.next();
        Sucursal sucursalBuscado = sucursalController.findOne(sucIdBuscado);
        if (sucursalBuscado != null) {
            System.out.print("El Codigo proporcionado corresponde a la sucursal: " +
                    sucursalBuscado.getSucId() +
                    ",Direccion: " + sucursalBuscado.getDireccion() +
                    ", Continente: " + sucursalBuscado.getContinente() +
                    ", latitud:  " + sucursalBuscado.getLatitud() +
                    ", longitud: " + sucursalBuscado.getLongitud() +
                    ", Estado: ");
            if (sucursalBuscado.getHabilitado()) {
                System.out.print("Habilitado\n");
            } else {
                System.out.print("Inhabilitado\n");
            }
        } else {
            System.out.println("El Codigo proporcionado no pertenece a ninguna sucursal.");
        }
    }

    public void buscarTodasLasSucursales() {
        System.out.println("\n");
        for (Sucursal sucu : sucursalController.findAll()) {
            System.out.println("Sucursal: " + sucu.getSucId() +
                    ", Direccion: " + sucu.getDireccion() +
                    ", Continente: " + sucu.getContinente() +
                    ", Latitud: " + sucu.getLatitud() +
                    ", Longitud " + sucu.getLongitud() + ";");
        }
        System.out.println("\n");
    }

    public int getOption() {
        return option;
    }


}
