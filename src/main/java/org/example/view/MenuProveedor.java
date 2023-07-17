package org.example.view;

import org.example.controller.ProductoController;
import org.example.model.Proveedor;

import java.util.Scanner;

public class MenuProveedor {

    private final ProductoController productoController;
    private final Scanner input;
    private int option;

    public MenuProveedor() {
        this.productoController = new ProductoController();
        this.input = new Scanner(System.in);
        this.option = 99;
    }

    public int seleccionarOpcion() {
        option = 99;
        System.out.println("""
                                
                ----- MENÚ PROVEEDORES -----
                Seleccione la opción:
                1. Añadir proveedor.
                2. Modificar Proveedor.
                3. Gestionar productos de proveedores.
                4. Eliminar Proveedor.
                5. Buscar Proveedor por CUIT.
                6. Obtener lista de todos los Proveedores.
                0. Salir.
                """);
        option = input.nextInt();
        return option;
    }

    public void crearProveedor() {
        createProveedor();
    }

    public void modificarProveedor() {
        modificateProveedor();
    }

    public void eliminarProveerdor() {
        deleteProveedor();
    }


    public void buscarProveedorPorCuit() {
        buscarUno();
    }

    public void buscarProveedores() {
        buscarTodos();
    }

    private boolean existeProveedor(String cuit) {
        return productoController.findOne(cuit) != null;
    }

    private void modificarDatosProveedor(Proveedor proveedor) {
        input.nextLine();
        System.out.print("Nombre: ");
        String nombreModificar = input.nextLine();
        System.out.print("Dirección: ");
        String direccionModificar = input.nextLine();
        System.out.print("Correo: ");
        String correoModificar = input.nextLine();
        System.out.print("Teléfono: ");
        String telefonoModificar = input.nextLine();
        proveedor.setNombre(nombreModificar);
        proveedor.setDireccion(direccionModificar);
        proveedor.setCorreo(correoModificar);
        proveedor.setTelefono(telefonoModificar);
    }

    private void mostrarInfoProveedor(Proveedor proveedor) {
        String condicion = proveedor.getHabilitado() ? "Habilitado" : "Deshabilitado";
        System.out.print("El cuit proporcionado corresponde al proveedor: " +
                proveedor.getNombre() +
                ", CUIT: " + proveedor.getCuit() +
                ", Correo: " + proveedor.getCorreo() +
                ", Dirección: " + proveedor.getDireccion() +
                ", Teléfono: " + proveedor.getTelefono() +
                ", condición: " + condicion);
    }

    private void buscarUno() {
        System.out.println("\nIngrese el CUIT del proveedor a buscar:");
        String cuitBuscado = input.next();
        Proveedor proveedorBuscado = productoController.findOnePr(cuitBuscado);
        if (proveedorBuscado != null) {
            mostrarInfoProveedor(proveedorBuscado);
        } else {
            System.out.println("El cuit proporcionado no corresponde a ningún proveedor.");
        }
    }

    private void buscarTodos() {
        System.out.println("\n");
        for (Proveedor pr : productoController.findAllPr()) {
            System.out.println("Proveedor: " + pr.getNombre() +
                    ", CUIT: " + pr.getCuit() +
                    ", Correo: " + pr.getCorreo() +
                    ", Dirección: " + pr.getDireccion() +
                    ", Teléfono: " + pr.getTelefono() + ";");
        }
        System.out.println("\n");
    }

    private void createProveedor() {
        System.out.println("\nProporcione los datos del nuevo Proveedor: ");
        System.out.print("Cuit: ");
        String cuit = input.next();
        while (existeProveedor(cuit)) {
            System.out.println("Este cuit ya existe, intentelo de nuevo. (0 para cancelar)");
            System.out.print("Cuit: ");
            cuit = input.next();
            if (cuit.equals("0")) {
                System.out.println("Operación cancelada.");
                return;
            } else {
                existeProveedor(cuit);
            }
        }
        Proveedor nuevoProveedor = new Proveedor(null, null, cuit, null, null, true);
        modificarDatosProveedor(nuevoProveedor);
        productoController.createPr(nuevoProveedor);
        System.out.println("Proveedor " + nuevoProveedor.getNombre() + " Creada con éxito");
    }

    private void modificateProveedor() {
        System.out.println("\nIngrese el CUIT del proveedor a modificar:");
        String cuitModificar = input.next();
        Proveedor proveedorModificar = productoController.findOnePr(cuitModificar);
        while (proveedorModificar == null) {
            System.out.println("Este cuit ya existe, intentelo de nuevo. (0 para cancelar)");
            System.out.print("Cuit: ");
            cuitModificar = input.next();
            if (cuitModificar.equals("0")) {
                System.out.println("Operación cancelada.");
                return;
            } else {
                proveedorModificar = productoController.findOnePr(cuitModificar);
            }
        }
        modificarDatosProveedor(proveedorModificar);
        productoController.updatePr(proveedorModificar);
        System.out.println("Proveedor " + proveedorModificar.getCuit() + " Modificada con éxito.");
    }

    private void deleteProveedor() {
        System.out.println("\nIngrese el CUIT del proveedor a eliminar:");
        String cuitEliminar = input.next();
        Proveedor proveedorEliminar = productoController.findOnePr(cuitEliminar);
        if (proveedorEliminar != null) {
            productoController.deletePr(cuitEliminar);
            System.out.println("El proveedor " + proveedorEliminar.getCuit() + " ha sido eliminado con éxito.");
        } else {
            System.out.println("No se encontró ningún proveedor con el CUIT proporcionado.");
        }
    }

    public int getOption() {
        return option;
    }

}


