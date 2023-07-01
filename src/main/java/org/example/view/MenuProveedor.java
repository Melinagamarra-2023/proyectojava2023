package org.example.view;

import org.example.controller.ProveedorController;
import org.example.model.Proveedor;
import org.example.repository.ProveedorRepository;
import org.example.service.ProveedorService;

import java.util.Scanner;

public class MenuProveedor {
    ProveedorRepository proveedorRepository = new ProveedorRepository();
    ProveedorService proveedorService = new ProveedorService(proveedorRepository);
    ProveedorController proveedorController = new ProveedorController(proveedorService, proveedorRepository);
    Scanner input = new Scanner(System.in);
    int option = 99;

    public int seleccionarOpcion() {
        System.out.println("""
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
        Proveedor proveedorExiste = proveedorController.findOne(cuit);
        while (proveedorExiste != null) {
            System.out.println("Ya existe un Proveedor con el mismo CUIT." +
                    " Ingrese un CUIT diferente");
            System.out.print("Cuit: ");
            cuit = input.next();
            proveedorExiste = proveedorController.findOne(cuit);
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
        proveedorController.create(nuevoProveedor);
        System.out.println("Proveedor " + nuevoProveedor.getNombre() + " Creada con éxito");
    }

    public void modificarProveedor() {
        System.out.println("\nIngrese el CUIT del proveedor a modificar:");
        String cuitModificar = input.next();
        Proveedor proveedorModificar = proveedorController.findOne(cuitModificar);
        while (proveedorModificar != null && proveedorModificar.getHabilitado() == false) {
            System.out.println("Ya existe un Proveedor con el mismo CUIT." +
                    " Ingrese un CUIT diferente");
            System.out.print("Cuit: ");
            cuitModificar = input.next();
            /*if (proveedorModificar == null) {
                System.out.println("No se encontró ningún proveedor con el CUIT proporcionado.");
            }

             */
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
        proveedorController.update(proveedorModificar);
        System.out.println("Proveedor " + proveedorModificar.getCuit() + " Modificada con éxito.");
    }

    public void eliminarProveedor() {
        System.out.println("\nIngrese el CUIT del proveedor a eliminar:");
        String cuitEliminar = input.next();
        Proveedor proveedorEliminar = proveedorController.findOne(cuitEliminar);
        if (proveedorEliminar != null) {
            proveedorController.delete(cuitEliminar);
            System.out.println("El proveedor " + proveedorEliminar.getCuit() + " ha sido eliminado con éxito.");
        } else {
            System.out.println("No se encontró ningún proveedor con el CUIT proporcionado.");
        }
    }

    public void buscarProveedorPorCuit() {
        System.out.println("\nIngrese el CUIT del proveedor a buscar:");
        String cuitBuscado = input.next();
        Proveedor proveedorBuscado = proveedorController.findOne(cuitBuscado);
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
        for (Proveedor pr : proveedorController.findAll()) {
            System.out.println("Proveedor: " + pr.getNombre() +
                    ", CUIT: " + pr.getCuit() +
                    ", Correo: " + pr.getCorreo() +
                    ", Dirección: " + pr.getDireccion() +
                    ", Teléfono: " + pr.getTelefono() + ";");
        }
        System.out.println("\n");
    }

    public int atras() {
        return option;
    }
}
