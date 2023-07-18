package org.example.view;

import org.example.controller.PedidoController;
import org.example.model.Remito;
import org.example.model.Transportista;
import org.example.controller.TransportistaController;

import java.util.Scanner;

public class MenuTransportista {

    private final TransportistaController transportistaController;
    private final PedidoController pedidoController;
    private final Scanner input;
    private int option;

    public MenuTransportista() {
        this.transportistaController = new TransportistaController();
        this.pedidoController = new PedidoController();
        this.input = new Scanner(System.in);
        this.option = 99;
    }

    public int seleccionarOpcion() {
        option = 99;
        System.out.println("""
                                
                ---- MENÚ TRANSPORTISTAS ----
                Seleccione la opción:
                1. Añadir un nuevo transportista.
                2. Modificar un transportista.
                3. Deshabilitar a un transportista.
                4. Buscar transportista por CUIT.
                5. Obtener una lista de todos los transportistas.
                6. Buscar transportistas por tipo de transporte.
                7. Informar ubicación de transportista.
                0. Salir.
                """);
        option = input.nextInt();
        return option;
    }

    public void agregarTransportista() {
        System.out.println("\nProporcione los datos del nuevo transportista: ");
        System.out.print("Cuit: ");
        String id = input.next();
        Transportista transportistaExiste = transportistaController.findOne(id);
        while (transportistaExiste != null) {
            System.out.println("Este ID ya existe, intentelo de nuevo. (0 para cancelar)");
            System.out.print("ID: ");
            id = input.next();
            if (id.equals("0")) {
                System.out.println("Operación cancelada.");
                return;
            } else {
                transportistaExiste = transportistaController.findOne(id);
            }
        }
        System.out.print("Nombre: ");
        String nombre = input.next();
        System.out.print("Teléfono: ");
        String telefono = input.next();
        Transportista nuevoTransportista = new Transportista(nombre, id, telefono, true, false, false, false);
        transportistaController.create(nuevoTransportista);
        this.setTransporte(nuevoTransportista);
        System.out.println("Transportista: " + nuevoTransportista.getNombre() + " añadido con éxito");
    }

    public void modificarTransportista() {
        System.out.println("\nIngrese el CUIT del transportista a modificar:");
        String cuitModificar = input.next();
        Transportista transportistaModificar = transportistaController.findOne(cuitModificar);
        while (transportistaModificar == null) {
            System.out.println("Este ID ya existe, intentelo de nuevo. (0 para cancelar)");
            System.out.print("ID: ");
            cuitModificar = input.next();
            if (cuitModificar.equals("0")) {
                System.out.println("Operación cancelada.");
                return;
            } else {
                transportistaModificar = transportistaController.findOne(cuitModificar);
            }
        }
        System.out.println("Ingrese los nuevos datos del transportista:");
        System.out.print("Nombre: ");
        String nombreModificar = input.next();
        System.out.print("Teléfono: ");
        String telefonoModificar = input.next();
        transportistaController.update(transportistaModificar).setTerrestre(false);
        transportistaController.update(transportistaModificar).setMaritimo(false);
        transportistaController.update(transportistaModificar).setAereo(false);
        this.setTransporte(transportistaModificar);
        transportistaModificar.setNombre(nombreModificar);
        transportistaModificar.setTelefono(telefonoModificar);
        transportistaController.update(transportistaModificar);
        System.out.println("Transportista " + transportistaModificar.getNombre() + " modificado con éxito.");
    }

    public void eliminarTransportista() {
        System.out.println("\nIngrese el CUIT del transportista a eliminar:");
        String cuitEliminar = input.next();
        Transportista transportistaEliminar = transportistaController.findOne(cuitEliminar);
        if (transportistaEliminar != null) {
            transportistaController.delete(cuitEliminar);
            System.out.println("Transportista " + transportistaEliminar.getNombre() + " eliminado con éxito.");
        } else {
            System.out.println("No se encontró ningún transportista con el CUIT proporcionado.");
        }
    }

    public void buscarPorCuit() {
        System.out.println("\nIngrese el CUIT del transportista a buscar:");
        String cuitBuscado = input.next();
        Transportista transportistaBuscado = transportistaController.findOne(cuitBuscado);
        if (transportistaBuscado != null) {
            this.mostrarDatos(transportistaBuscado.getCuit());
            if (transportistaBuscado.getHabilitado()) {
                System.out.print("Habilitado");
            } else {
                System.out.print("Inhabilitado");
            }
            this.Transportes(transportistaBuscado);
        } else {
            System.out.println("El cuit proporcionado no corresponde a ningún transportista.");
        }
    }

    public void buscarTransportistas() {
        System.out.println("\n");
        this.mostrarDatos(null);
        System.out.print("\n");
    }

    private void mostrarDatos(String cuit) {
        for (Transportista tr : transportistaController.findAll()) {
            if (cuit == null) {
                System.out.print("Transportista: " +
                        tr.getNombre() +
                        ", CUIT: " + tr.getCuit() +
                        ", Teléfono: " + tr.getTelefono());
                this.Transportes(tr);
            } else {
                if (tr.getCuit().equals(cuit)) {
                    System.out.print("Transportista: " +
                            tr.getNombre() +
                            ", CUIT: " + tr.getCuit() +
                            ", Teléfono: " + tr.getTelefono());
                }
            }
        }
    }


    public void buscarTransportistasPorTipo() {
        int opc;
        do {
            System.out.println("""
                    Seleccione el tipo de transporte:
                    1. Terrestre.
                    2. Marítimo.
                    3. Aéreo.""");
            opc = input.nextInt();
        } while (opc != 1 && opc != 2 && opc != 3);
        for (Transportista tr : transportistaController.buscarTransportistasPorTipo(opc)) {
            System.out.print("Transportista: " + tr.getNombre() +
                    ", CUIT: " + tr.getCuit() +
                    ", Telefono: " + tr.getTelefono() + "\n");
        }
    }

    public void informarUbicacion() {
        input.nextLine();
        System.out.print("Ingrese su id: ");
        String cuit = input.nextLine();
        System.out.print("Ingrese su latitud: ");
        Double latitud = input.nextDouble();
        System.out.print("Ingrese su longitud: ");
        Double longitud = input.nextDouble();
        for (Remito remito : pedidoController.verRemitosPorTransportista(cuit)) {
            pedidoController.nuevoTransito(remito.getDetalle(), latitud, longitud);
        }
    }

    private void setTransporte(Transportista tr) {
        System.out.println("""
                Ingrese los numeros correspondientes a los transportes que utilizara el transportista:
                1. Terrestre.
                2. Marítimo.
                3. Aéreo.""");
        String opc = input.next();
        if (opc.contains("1")) {
            transportistaController.update(tr).setTerrestre(true);
        }
        if (opc.contains("2")) {
            transportistaController.update(tr).setMaritimo(true);
        }
        if (opc.contains("3")) {
            transportistaController.update(tr).setAereo(true);
        }
    }

    private void Transportes(Transportista tr) {
        System.out.print(", Transportes habilitados: ");
        if (tr.getTerrestre()) {
            System.out.print("terrestre");
        }
        if (tr.getMaritimo()) {
            System.out.print(tr.getTerrestre() ? ", marítimo" : "marítimo");
        }
        if (tr.getAereo()) {
            System.out.print((tr.getTerrestre() || tr.getMaritimo()) ? ", Aéreo" : "Aéreo");
        }
        System.out.print(".\n");
    }


    public int getOption() {
        return option;
    }
}
