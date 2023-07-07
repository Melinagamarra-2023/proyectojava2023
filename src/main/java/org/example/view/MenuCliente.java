package org.example.view;

import org.example.controller.ClienteController;
import org.example.model.Cliente;

import java.util.Scanner;

public class MenuCliente {


    ClienteController clienteController = new ClienteController();
    Scanner input = new Scanner(System.in);
    int option = 99;

    public int seleccionarOpcion() {
        option = 99;
        System.out.println("""
                
                ------ MENÚ CLIENTES ------
                Seleccione la opción:
                1. Crear cuenta de cliente.
                2. Modificar cuenta de cliente.
                3. Eliminar cuenta de cliente.
                4. Buscar por CUIT a un cliente.
                5. Obtener lista de todos los clientes.
                0. Salir.
                """);
        option = input.nextInt();
        return option;
    }

    public void crearNuevoCliente() {
        System.out.println("\nProporcione los datos para la nueva cuenta: ");
        System.out.print("Cuit: ");
        String cuit = input.next();
        Cliente clienteExistente = clienteController.findOne(cuit);
        while (clienteExistente != null) {
            System.out.println("Ya existe un cliente con el mismo CUIT." +
                    " Ingrese un CUIT diferente");
            System.out.print("Cuit: ");
            cuit = input.next();
            clienteExistente = clienteController.findOne(cuit);
        }
        System.out.print("Nombre: ");
        String nombre = input.next();
        System.out.print("Apellido: ");
        String apellido = input.next();
        System.out.print("Dirección: ");
        String direccion = input.next();
        input.nextLine();
        System.out.print("Correo: ");
        String correo = input.next();
        System.out.print("Teléfono: ");
        String telefono = input.next();
        Cliente nuevoCliente = new Cliente(nombre, apellido, cuit, direccion, correo, telefono, true);
        clienteController.create(nuevoCliente);
        System.out.println("Cuenta: " + nuevoCliente.getApellido() + " Creada con éxito");
    }

    public void modificarCliente() {
        System.out.println("\nIngrese el CUIT del cliente a modificar:");
        String cuitModificar = input.next();
        Cliente clienteModificar = clienteController.findOne(cuitModificar);
        if (clienteModificar != null) {
            System.out.println("Ingrese los nuevos datos del cliente:");
            System.out.print("Nombre: ");
            String nombreModificar = input.next();
            System.out.print("Apellido: ");
            String apellidoModificar = input.next();
            System.out.print("Dirección: ");
            String direccionModificar = input.next();
            System.out.print("Correo: ");
            String correoModificar = input.next();
            System.out.print("Teléfono: ");
            String telefonoModificar = input.next();
            clienteModificar.setNombre(nombreModificar);
            clienteModificar.setApellido(apellidoModificar);
            clienteModificar.setDireccion(direccionModificar);
            clienteModificar.setCorreo(correoModificar);
            clienteModificar.setTelefono(telefonoModificar);
            clienteController.update(clienteModificar);
            System.out.println("Cuenta: " + clienteModificar.getCuit() + " Modificada con éxito.");
        } else {
            System.out.println("No se encontró ningún cliente con el CUIT proporcionado.");
        }
    }

    public void eliminarCliente() {
        System.out.println("\nIngrese el CUIT del cliente a eliminar:");
        String cuitEliminar = input.next();
        Cliente clienteEliminar = clienteController.findOne(cuitEliminar);
        if (clienteEliminar != null) {
            clienteController.delete(cuitEliminar);
            System.out.println("La cuenta del cliente " + clienteEliminar.getApellido() + " ha sido eliminada con éxito.");
        } else {
            System.out.println("No se encontró ningún cliente con el CUIT proporcionado.");
        }
    }

    public void buscarClientePorCuit() {
        System.out.println("\nIngrese el CUIT de la cuenta a buscar:");
        String cuitBuscado = input.next();
        Cliente clienteBuscado = clienteController.findOne(cuitBuscado);
        if (clienteBuscado != null) {
            System.out.print("El cuit proporcionado corresponde al Cliente: " +
                    clienteBuscado.getApellido() + " " + clienteBuscado.getNombre() +
                    ", CUIT: " + clienteBuscado.getCuit() +
                    ", Correo: " + clienteBuscado.getCorreo() +
                    ", Dirección: " + clienteBuscado.getDireccion() +
                    ", Teléfono: " + clienteBuscado.getTelefono() +
                    ", Estado: ");
            if (clienteBuscado.getHabilitado()) {
                System.out.print("Habilitado\n");
            } else {
                System.out.print("Inhabilitado\n");
            }
        } else {
            System.out.println("El cuit proporcionado no corresponde a ningún cliente.");
        }
    }

    public void buscarTodosLosClientes() {
        System.out.println("\n");
        for (Cliente cl : clienteController.findAll()) {
            System.out.println("Cliente: " + cl.getApellido() + " " + cl.getNombre() +
                    ", CUIT: " + cl.getCuit() +
                    ", Correo: " + cl.getCorreo() +
                    ", Dirección: " + cl.getDireccion() +
                    ", Teléfono: " + cl.getTelefono() +
                    ", Estado: " + cl.getHabilitado() + ";");
        }
        System.out.println("\n");
    }

    public int getOption() {
        return option;
    }
}
