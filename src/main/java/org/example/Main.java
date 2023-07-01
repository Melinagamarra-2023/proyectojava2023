package org.example;

import org.example.view.*;


public class Main {
    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        MenuCliente menuCliente = new MenuCliente();
        MenuSucursal menuSucursal = new MenuSucursal();
        MenuProveedor menuProveedor = new MenuProveedor();
        while (menuPrincipal.atras() != 0) {
            switch (menuPrincipal.seleccionarModulo()) {
                case 1 -> {
                    while (menuCliente.atras() != 0) {
                        switch (menuCliente.seleccionarOpcion()) {
                            case 1 -> menuCliente.crearNuevoCliente();
                            case 2 -> menuCliente.modificarCliente();
                            case 3 -> menuCliente.eliminarCliente();
                            case 4 -> menuCliente.buscarClientePorCuit();
                            case 5 -> menuCliente.buscarTodosLosClientes();
                            case 0 -> menuPrincipal.regresar();
                            default -> menuPrincipal.invalido();
                        }
                    }
                }

                case 2 -> {
                    while (menuProveedor.atras() != 0) {
                        switch (menuProveedor.seleccionarOpcion()) {
                            case 1 -> menuProveedor.crearProveedor();
                            case 2 -> menuProveedor.modificarProveedor();
                            case 3 -> menuProveedor.eliminarProveedor();
                            case 4 -> menuProveedor.buscarProveedorPorCuit();
                            case 5 -> menuProveedor.buscarProveedores();
                            case 0 -> menuPrincipal.regresar();
                            default -> menuPrincipal.invalido();
                        }
                    }
                }
                case 3 -> {
                    while (menuSucursal.atras() != 0) {
                        switch (menuSucursal.seleccionarOpcion()) {
                            case 1 -> menuSucursal.agregarUnaSucursal();
                            case 2 -> menuSucursal.modificarSucursal();
                            case 3 -> menuSucursal.deshablitarSucursal();
                            case 4 -> menuSucursal.buscarSucursalPorCodigo();
                            case 5 -> menuSucursal.buscarTodasLasSucursales();
                            case 0 -> menuPrincipal.regresar();
                            default -> menuPrincipal.invalido();
                        }

                    }
                }
            }
        }
    }
}