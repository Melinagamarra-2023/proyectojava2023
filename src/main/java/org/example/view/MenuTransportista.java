package org.example.view;

import org.example.controller.TransportistaController;
import org.example.model.Transportista;
import org.example.repository.TransportistaRepository;
import org.example.service.TransportistaService;

import java.util.Scanner;

public class MenuTransportista {
    TransportistaRepository transportistaRepository = new TransportistaRepository();
    TransportistaService transportistaService = new TransportistaService(transportistaRepository);
    TransportistaController transportistaController = new TransportistaController(transportistaService, transportistaRepository);
    Scanner input = new Scanner(System.in);
    int option = 99;

    public int seleccionarOpcion() {
        System.out.println("""
                                
                Seleccione la opción:
                1. Crear cuenta de transportista.
                2. Modificar cuenta de transportista.
                3. Eliminar cuenta de transportista.
                4. Buscar por CUIT a un transportista.
                5. Obtener lista de todos los transportistas.
                6. Buscar por  tipo de transporte.
                0. Salir.
                """);
        option = input.nextInt();
        return option;
    }

    public void crearNuevoTransportista() {
        System.out.println("\nProporcione los datos para la nueva cuenta: ");
        System.out.print("Nombre: ");
        String nombre = input.next();
        System.out.print("Apellido: ");
        String apellido = input.next();
        System.out.print("Cuit: ");
        String cuit = input.next();
        System.out.print("Dirección: ");
        String direccion = input.next();
        input.nextLine();
        System.out.print("Correo: ");
        String correo = input.next();
        System.out.print("Teléfono: ");
        String telefono = input.next();
        Transportista nuevoTransportista = new Transportista(nombre, apellido, cuit, direccion, correo, telefono, null,true);
        this.setTransporte(nuevoTransportista);
        Transportista transportistaExistente = transportistaController.buscarPorID(cuit);
        if (transportistaExistente != null) {
            System.out.println("Ya existe un transportista con el mismo CUIT.");
        } else {
            transportistaController.crear(nuevoTransportista);
            System.out.println("Cuenta: " + nuevoTransportista.getApellido() + " Creada con éxito");
        }

    }

    public void modificarTransportista() {
        System.out.println("\nIngrese el CUIT del transportista a modificar:");
        String cuitModificar = input.next();
        Transportista transportistaModificar = transportistaController.buscarPorID(cuitModificar);
        if (transportistaModificar != null) {
            System.out.println("Ingrese los nuevos datos del transportista:");
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
            this.setTransporte(transportistaModificar);
            transportistaModificar.setNombre(nombreModificar);
            transportistaModificar.setApellido(apellidoModificar);
            transportistaModificar.setDireccion(direccionModificar);
            transportistaModificar.setCorreo(correoModificar);
            transportistaModificar.setTelefono(telefonoModificar);
            transportistaController.modificar(transportistaModificar);
            System.out.println("Transportista: " + transportistaModificar.getCuit() + " Modificado con éxito.");
        } else {
            System.out.println("No se encontró ningún transportista con el CUIT proporcionado.");
        }
    }

    public void eliminarTransportista() {
        System.out.println("\nIngrese el CUIT del cliente a eliminar:");
        String cuitEliminar = input.next();
        Transportista transportistaEliminar = transportistaController.buscarPorID(cuitEliminar);
        if (transportistaEliminar != null) {
            transportistaController.eliminar(cuitEliminar);
            System.out.println("La cuenta del cliente " + transportistaEliminar.getApellido() + " ha sido eliminada con éxito.");
        } else {
            System.out.println("No se encontró ningún cliente con el CUIT proporcionado.");
        }
    }

    public void buscarPorCuit() {
        System.out.println("\nIngrese el CUIT de la cuenta a buscar:");
        String cuitBuscado = input.next();
        Transportista transportistaBuscado = transportistaController.buscarPorID(cuitBuscado);
        if (transportistaBuscado != null) {
            System.out.print("El cuit proporcionado corresponde al Cliente: " +
                    transportistaBuscado.getApellido() + " " + transportistaBuscado.getNombre() +
                    ", CUIT: " + transportistaBuscado.getCuit() +
                    ", Correo: " + transportistaBuscado.getCorreo() +
                    ", Dirección: " + transportistaBuscado.getDireccion() +
                    ", Teléfono: " + transportistaBuscado.getTelefono() +
                    ", Transporte: " + transportistaBuscado.getTipoDeTransporte().getDescripcion() +
                    ", condición: ");
            if (transportistaBuscado.getHabilitado()) {
                System.out.print("Habilitado\n");
            } else {
                System.out.print("Inhabilitado\n");
            }
        } else {
            System.out.println("El cuit proporcionado no corresponde a ningún transportista.");
        }
    }

    public void buscarTransportistas() {
        System.out.println("\n");
        for (Transportista tr : transportistaController.buscarTodos()) {
            System.out.println("Transportista: " + tr.getApellido() + " " + tr.getNombre() +
                    ", CUIT: " + tr.getCuit() +
                    ", Correo: " + tr.getCorreo() +
                    ", Dirección: " + tr.getDireccion() +
                    ", Teléfono: " + tr.getTelefono() +
                    ", Transporte: " + tr.getTipoDeTransporte().getDescripcion() + ";");
        }
        System.out.println("\n");
    }

    public void buscarTransportistasPorTipo() {
        System.out.println("""
                Seleccione el tipo de transporte:
                1. Terrestre.
                2. Aéreo.
                3. Marítimo.""");
        int opc = input.nextInt();
        for (Transportista tr : transportistaController.buscarTransportistasPorTipo(opc)) {
            System.out.println("Transportista: " + tr.getApellido() + " Tipo de transporte: " + tr.getTipoDeTransporte().getDescripcion());
        }
    }

    private void setTransporte(Transportista tr) {
        System.out.println("""
                Seleccione el tipo de transporte:
                1. Terrestre.
                2. Aéreo.
                3. Marítimo.""");
        int opc = input.nextInt();
        transportistaController.setTransporte(tr, opc);
    }

    public int atras(){
        return option;
    }
}
