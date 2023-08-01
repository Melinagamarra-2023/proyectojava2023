package org.example.view;

import org.example.model.Transportista;
import org.example.controller.TransportistaController;

import java.util.Scanner;

public class MenuTransportista {

    private final MenuPrincipal menuPrincipal;
    private final TransportistaController transportistaController;
    private final Scanner input;

    public MenuTransportista() {
        this.menuPrincipal = new MenuPrincipal();
        this.transportistaController = new TransportistaController();
        this.input = new Scanner(System.in);
    }

    public int seleccionarOpcion() {
        System.out.println("""
                                
                ---- MENÚ TRANSPORTISTAS ----
                Seleccione la opción:
                1. Añadir un nuevo transportista.
                2. Modificar un transportista.
                3. Deshabilitar a un transportista.
                4. Buscar transportista por CUIT.
                5. Obtener una lista de todos los transportistas.
                6. Buscar transportistas por tipo de transporte.
                0. Salir.
                """);
        return input.nextInt();
    }

    public void agregarTransportista() {
        System.out.println("\nProporcione los datos del nuevo transportista: ");
        System.out.print("Cuit: ");
        String cuit = input.next();
        while (transportistaController.findOne(cuit) != null && !(cuit.equals("0"))) {
            cuit = menuPrincipal.verificarAusencia("cuit");
        }
        if (cuit.equals("0")) {
            return;
        }
        System.out.print("Nombre: ");
        String nombre = input.next();
        System.out.print("Teléfono: ");
        String telefono = input.next();
        Transportista nuevoTransportista = new Transportista(nombre, cuit, telefono, true, false, false, false);
        this.setTransporte(nuevoTransportista);
        transportistaController.create(nuevoTransportista);
        System.out.println("Transportista: " + nuevoTransportista.getNombre() + " añadido con éxito");
    }

    public void modificarTransportista() {
        System.out.println("Ingrese el CUIT del transportista a modificar:");
        String cuitModificar = input.next();
        while (transportistaController.findOne(cuitModificar) == null && !(cuitModificar.equals("0"))) {
            cuitModificar = menuPrincipal.verificarExistencia("id");
        }
        if (cuitModificar.equals("0")) {
            return;
        }
        Transportista transportistaModificar = transportistaController.findOne(cuitModificar);
        System.out.println("Ingrese los nuevos datos del transportista:");
        System.out.print("Nombre: ");
        String nombreModificar = input.next();
        System.out.print("Teléfono: ");
        String telefonoModificar = input.next();
        transportistaModificar.setNombre(nombreModificar);
        transportistaModificar.setTelefono(telefonoModificar);
        transportistaController.update(transportistaModificar).setTerrestre(false);
        transportistaController.update(transportistaModificar).setMaritimo(false);
        transportistaController.update(transportistaModificar).setAereo(false);
        this.setTransporte(transportistaModificar);
        transportistaController.update(transportistaModificar);
        System.out.println("Transportista " + transportistaModificar.getNombre() + " modificado con éxito.");
    }

    public void eliminarTransportista() {
        System.out.println("Ingrese el CUIT del transportista a eliminar:");
        String cuitEliminar = input.next();
        while (transportistaController.findOne(cuitEliminar) == null && !(cuitEliminar.equals("0"))) {
            cuitEliminar = menuPrincipal.verificarExistencia("cuit");
        }
        if (cuitEliminar.equals("0")) {
            return;
        }
        Transportista transportistaEliminar = transportistaController.findOne(cuitEliminar);
        transportistaController.delete(cuitEliminar);
        System.out.println("Transportista " + transportistaEliminar.getNombre() + " eliminado con éxito.");
    }

    public void buscarPorCuit() {
        System.out.println("Ingrese el CUIT del transportista a buscar:");
        String cuitBuscado = input.next();
        while (transportistaController.findOne(cuitBuscado) == null && !(cuitBuscado.equals("0"))) {
            cuitBuscado = menuPrincipal.verificarExistencia("id");
        }
        if (cuitBuscado.equals("0")) {
            return;
        }
        Transportista transportistaBuscado = transportistaController.findOne(cuitBuscado);
        String estado = (transportistaBuscado.getHabilitado() ? "Habilitado." : "Inhabilitado.");
        this.mostrarDatos(transportistaBuscado);
        this.Transportes(transportistaBuscado);
        System.out.print(", Estado: " + estado + ".\n");
    }

    public void buscarTransportistas() {
        for (Transportista transportista : transportistaController.findAll()){
            this.mostrarDatos(transportista);
            System.out.print(".\n");
        }
    }

    private void mostrarDatos(Transportista transportista) {
        System.out.print("Transportista: " +
                transportista.getNombre() +
                ", CUIT: " + transportista.getCuit() +
                ", Teléfono: " + transportista.getTelefono());
        this.Transportes(transportista);
    }


    public void buscarTransportistasPorTipo() {
        int opc;
            System.out.println("""
                    Seleccione el tipo de transporte:
                    1. Terrestre.
                    2. Marítimo.
                    3. Aéreo.
                    """);
            opc = input.nextInt();
        while (opc != 1 && opc != 2 && opc != 3) {
            menuPrincipal.invalido();
            opc = input.nextInt();
        }
        for (Transportista tr : transportistaController.buscarTransportistasPorTipo(opc)) {
            this.mostrarDatos(tr);
            System.out.print(".\n");
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
    }

}
