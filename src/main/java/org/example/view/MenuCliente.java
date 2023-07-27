package org.example.view;

import org.example.controller.ClienteController;
import org.example.model.Cliente;

import java.util.Scanner;

public class MenuCliente {

    private final MenuPrincipal menuPrincipal;
    private final ClienteController clienteController;
    private final Scanner input;

    public MenuCliente() {
        this.menuPrincipal = new MenuPrincipal();
        this.clienteController = new ClienteController();
        this.input = new Scanner(System.in);
    }

    public int seleccionarOpcion() {
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
        return input.nextInt();
    }

    public void crearNuevoCliente() {
        crearCliente();
    }

    public void modificarCliente() {
        modificateCliente();
    }

    public void eliminarCliente() {
        deleteCliente();
    }

    public void buscarClientePorCuit() {
        buscarUno();
    }

    public void buscarTodosLosClientes() {
        buscarTodos();
    }


    private void crearCliente() {
        System.out.println("Proporcione los datos para la nueva cuenta: ");
        System.out.print("Cuit: ");
        String cuit = input.next();
        while (clienteController.findOne(cuit) != null && !(cuit.equals("0"))) {
            cuit = menuPrincipal.verificarAusencia("cuit");
        }
        if (cuit.equals("0")) {
            return;
        }
        Cliente nuevoCliente = new Cliente(null, null, cuit, null, null, null, true);
        this.modificarDatosCliente(nuevoCliente);
        clienteController.create(nuevoCliente);
        System.out.println("Cuenta: " + nuevoCliente.getApellido() + " Creada con éxito");
    }

    private void modificateCliente() {
        System.out.println("Ingrese el CUIT del cliente a modificar:");
        String cuitModificar = input.next();
        while (clienteController.findOne(cuitModificar) == null && !(cuitModificar.equals("0"))) {
            cuitModificar = menuPrincipal.verificarExistencia("cuit");
        }
        if (cuitModificar.equals("0")) {
            return;
        }
        Cliente clienteModificar = clienteController.findOne(cuitModificar);
        System.out.println("Ingrese los nuevos datos para este cliente:");
        this.modificarDatosCliente(clienteModificar);
        clienteController.update(clienteModificar);
        System.out.println("Cuenta: " + clienteModificar.getCuit() + " modificada con éxito.");
    }


    private void modificarDatosCliente(Cliente cliente) {
        input.nextLine();
        System.out.print("Nombre: ");
        String nombreModificar = input.nextLine();
        System.out.print("Apellido: ");
        String apellidoModificar = input.nextLine();
        System.out.print("Dirección: ");
        String direccionModificar = input.nextLine();
        System.out.print("Correo: ");
        String correoModificar = input.nextLine();
        System.out.print("Teléfono: ");
        String telefonoModificar = input.nextLine();
        cliente.setNombre(nombreModificar);
        cliente.setApellido(apellidoModificar);
        cliente.setDireccion(direccionModificar);
        cliente.setCorreo(correoModificar);
        cliente.setTelefono(telefonoModificar);
    }

    private void deleteCliente() {
        System.out.println("Ingrese el CUIT del cliente a eliminar:");
        String cuitEliminar = input.next();
        while (clienteController.findOne(cuitEliminar) == null && !(cuitEliminar.equals("0"))) {
            cuitEliminar = menuPrincipal.verificarExistencia("cuit");
        }
        if (cuitEliminar.equals("0")) {
            return;
        }
        Cliente clienteEliminar = clienteController.findOne(cuitEliminar);
        clienteController.delete(cuitEliminar);
        System.out.println("La cuenta del cliente " + clienteEliminar.getApellido() + " ha sido eliminada con éxito.");
    }

    private void mostrarInfoCliente(Cliente cliente) {
        System.out.print("El cuit proporcionado corresponde al Cliente: " +
                cliente.getApellido() + " " + cliente.getNombre() +
                ", CUIT: " + cliente.getCuit() +
                ", Correo: " + cliente.getCorreo() +
                ", Dirección: " + cliente.getDireccion() +
                ", Teléfono: " + cliente.getTelefono());
    }

    private void buscarUno() {
        System.out.println("Ingrese el CUIT de la cuenta a buscar:");
        String cuitBuscado = input.next();
        while (clienteController.findOne(cuitBuscado) == null && !(cuitBuscado.equals("0"))) {
            cuitBuscado = menuPrincipal.verificarExistencia("cuit");
        }
        if (cuitBuscado.equals("0")) {
            return;
        }
        Cliente clienteBuscado = clienteController.findOne(cuitBuscado);
        this.mostrarInfoCliente(clienteBuscado);
        String estado = clienteBuscado.getHabilitado() ? "Habilitado" : "Deshabilitado";
        System.out.println(",Estado: " + estado);
    }

    private void buscarTodos() {
        for (Cliente cl : clienteController.findAll()) {
            this.mostrarInfoCliente(cl);
            System.out.print("\n");
        }
    }

}
