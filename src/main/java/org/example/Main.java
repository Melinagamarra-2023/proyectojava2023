package org.example;
import org.example.view.*;

public class Main {
    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        MenuCliente menuCliente = new MenuCliente();
        MenuProveedor menuProveedor = new MenuProveedor();
        MenuProducto menuProducto = new MenuProducto();
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
                case 5 -> {
                    while (menuProducto.atras() != 0) {
                        switch (menuProducto.seleccionarOpcion()) {
                            case 1 -> menuProducto.crearProducto();
                            case 2 -> menuProducto.modificarProducto();
                            case 3 -> menuProducto.eliminarProducto();
                            case 4 -> menuProducto.buscarProductoPorId();
                            case 5 -> menuProducto.buscarTodosLosProductos();
                            case 0 -> menuPrincipal.regresar();
                            default -> menuPrincipal.invalido();
                        }
                    }
                }

            }
        }
    }
}