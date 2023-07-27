package org.example.view;

import org.example.controller.SucursalController;

import org.example.model.Sucursal;

import java.util.Scanner;

public class MenuSucursal {

    private final MenuPrincipal menuPrincipal;
    private final SucursalController sucursalController;
    private final Scanner input;

    public MenuSucursal() {
        this.menuPrincipal = new MenuPrincipal();
        this.sucursalController = new SucursalController();
        this.input = new Scanner(System.in);
    }

    public int seleccionarOpcion() {
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
        return input.nextInt();
    }

    public void agregarUnaSucursal() {
        System.out.println("Por favor escriba los datos de la sucursal");
        System.out.print("sucId: ");
        String sucId = input.next();
        while (sucursalController.findOne(sucId) != null && !(sucId.equals("0"))) {
            sucId = menuPrincipal.verificarAusencia("id");
        }
        if (sucId.equals("0")) {
            return;
        }
        System.out.println("Latitud: ");
        Double latitud = input.nextDouble();
        System.out.println("Longitud: ");
        Double longitud = input.nextDouble();
        System.out.println("Dirrecion: ");
        String dirrecion = input.next();
        System.out.println("Continente: ");
        String continente = input.next();
        Sucursal nuevaSucursal = new Sucursal(sucId, longitud, latitud, dirrecion, continente, true);
        sucursalController.create(nuevaSucursal);
        System.out.println("La sucursal " + nuevaSucursal.getSucId() + "ha sido añadida con exito");

    }

    public void modificarSucursal() {
        System.out.println("Ingrese el Codigo de la Sucursal a modificar:");
        String sucIdModificar = input.next();
        while (sucursalController.findOne(sucIdModificar) == null && !(sucIdModificar.equals("0"))) {
            sucIdModificar = menuPrincipal.verificarExistencia("id");
        }
        if (sucIdModificar.equals("0")) {
            return;
        }
        Sucursal sucursalModificar = sucursalController.findOne(sucIdModificar);
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
    }

    public void deshablitarSucursal() {
        System.out.println("Ingrese el Codido de la sucursal a eliminar:");
        String sucIdEliminar = input.next();
        while (sucursalController.findOne(sucIdEliminar) == null && !(sucIdEliminar.equals("0"))) {
            sucIdEliminar = menuPrincipal.verificarExistencia("id");
        }
        if (sucIdEliminar.equals("0")) {
            return;
        }
        Sucursal sucursalEliminar = sucursalController.findOne(sucIdEliminar);
        sucursalController.delete(sucIdEliminar);
        System.out.println("La sucursal " + sucursalEliminar.getSucId() + " ha sido eliminada con éxito.");
    }

    public void buscarSucursalPorCodigo() {
        System.out.println("Ingrese el código de la sucursal a buscar: ");
        String sucIdBuscado = input.next();
        while (sucursalController.findOne(sucIdBuscado) == null && !(sucIdBuscado.equals("0"))) {
            sucIdBuscado = menuPrincipal.verificarExistencia("id");
        }
        if (sucIdBuscado.equals("0")) {
            return;
        }
        Sucursal sucursalBuscado = sucursalController.findOne(sucIdBuscado);
        System.out.print("El Codigo proporcionado corresponde a la sucursal: " +
                sucursalBuscado.getSucId() +
                ",Direccion: " + sucursalBuscado.getDireccion() +
                ", Continente: " + sucursalBuscado.getContinente() +
                ", latitud:  " + sucursalBuscado.getLatitud() +
                ", longitud: " + sucursalBuscado.getLongitud() +
                ", Estado: ");
        if (sucursalBuscado.getHabilitado()) {
            System.out.print("Habilitado.");
        } else {
            System.out.print("Inhabilitado.");
        }
    }

    public void buscarTodasLasSucursales() {
        for (Sucursal sucu : sucursalController.findAll()) {
            System.out.println("Sucursal: " + sucu.getSucId() +
                    ", Direccion: " + sucu.getDireccion() +
                    ", Continente: " + sucu.getContinente() +
                    ", Latitud: " + sucu.getLatitud() +
                    ", Longitud " + sucu.getLongitud() + ".");
        }
    }

}
