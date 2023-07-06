package org.example;

import org.example.view.MenuPrincipal;
import org.example.view.MenuCliente;
import org.example.view.MenuTransportista;
import org.example.view.MenuProveedor;


public class Main {
    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        MenuCliente menuCliente = new MenuCliente();
        //MenuTransportista menuTransportista = new MenuTransportista();
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
            }
        }
    }
}