package org.example.view;

import org.example.controller.ProductoController;
import org.example.model.Proveedor;

import java.util.Scanner;

public class MenuProveedor {

    private final ProductoController productoController;
    private final Scanner input;

    public MenuProveedor() {
        this.productoController = new ProductoController();
        this.input = new Scanner(System.in);
    }

    private int option = 99;

    public int seleccionarOpcion() {
        option = 99;
        System.out.println("""
                                
                ----- MENÚ PROVEEDORES -----
                Seleccione la opción:
                1. Añadir proveedor.
                2. Modificar Proveedor.
                3. Eliminar Proveedor.
                4. Buscar Proveedor por CUIT.
                5. Obtener lista de todos los Proveedores.
                0. Salir.
                """);
        option = input.nextInt();
        return option;
    }

    public void crearProveedor() {
        System.out.println("\nProporcione los datos del nuevo Proveedor: ");
        System.out.print("Cuit: ");
        String cuit = input.next();
        Proveedor proveedorExiste = productoController.findOnePr(cuit);
        while (proveedorExiste != null) {
            System.out.println("Este cuit ya existe, intentelo de nuevo. (0 para cancelar)");
            System.out.print("Cuit: ");
            cuit = input.next();
            if (cuit.equals("0")) {
                System.out.println("Operación cancelada.");
                seleccionarOpcion();
            } else {
                proveedorExiste = productoController.findOnePr(cuit);
            }
        }
        System.out.print("Nombre: ");
        String nombre = input.next();
        System.out.print("Dirección: ");
        String direccion = input.next();
        input.nextLine();
        System.out.print("Correo: ");
        String correo = input.next();
        System.out.print("Teléfono: ");
        String telefono = input.next();
        Proveedor nuevoProveedor = new Proveedor(nombre, direccion, cuit, correo, telefono, true);
        productoController.createPr(nuevoProveedor);
        System.out.println("Proveedor " + nuevoProveedor.getNombre() + " Creada con éxito");
    }

    public void modificarProveedor() {
        System.out.println("\nIngrese el CUIT del proveedor a modificar:");
        String cuitModificar = input.next();
        Proveedor proveedorModificar = productoController.findOnePr(cuitModificar);
        while (proveedorModificar == null) {
            System.out.println("Este cuit ya existe, intentelo de nuevo. (0 para cancelar)");
            System.out.print("Cuit: ");
            cuitModificar = input.next();
            if (cuitModificar.equals("0")) {
                System.out.println("Operación cancelada.");
                seleccionarOpcion();
            } else {
                proveedorModificar = productoController.findOnePr(cuitModificar);
            }
        }
        System.out.println("Ingrese los nuevos datos del proveedor:");
        System.out.print("Nombre: ");
        String nombreModificar = input.next();
        System.out.print("Dirección: ");
        String direccionModificar = input.next();
        System.out.print("Correo: ");
        String correoModificar = input.next();
        System.out.print("Teléfono: ");
        String telefonoModificar = input.next();
        proveedorModificar.setNombre(nombreModificar);
        proveedorModificar.setDireccion(direccionModificar);
        proveedorModificar.setCorreo(correoModificar);
        proveedorModificar.setTelefono(telefonoModificar);
        productoController.updatePr(proveedorModificar);
        System.out.println("Proveedor " + proveedorModificar.getCuit() + " Modificada con éxito.");
    }

    public void eliminarProveerdor() {
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


    public void buscarProveedorPorCuit() {
        System.out.println("\nIngrese el CUIT del proveedor a buscar:");
        String cuitBuscado = input.next();
        Proveedor proveedorBuscado = productoController.findOnePr(cuitBuscado);
        if (proveedorBuscado != null) {
            System.out.print("El cuit proporcionado corresponde al proveedor: " +
                    proveedorBuscado.getNombre() +
                    ", CUIT: " + proveedorBuscado.getCuit() +
                    ", Correo: " + proveedorBuscado.getCorreo() +
                    ", Dirección: " + proveedorBuscado.getDireccion() +
                    ", Teléfono: " + proveedorBuscado.getTelefono() +
                    ", condición: ");
            if (proveedorBuscado.getHabilitado()) {
                System.out.print("Habilitado\n");
            } else {
                System.out.print("Inhabilitado\n");
            }
        } else {
            System.out.println("El cuit proporcionado no corresponde a ningún proveedor.");
        }
    }

    public void buscarProveedores() {
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


    public int getOption() {
        return option;
    }

}


