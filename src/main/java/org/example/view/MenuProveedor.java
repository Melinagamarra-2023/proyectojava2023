package org.example.view;

import org.example.controller.ProveedorController;
import org.example.model.Proveedor;

import java.util.Scanner;

public class MenuProveedor {

    private final MenuPrincipal menuPrincipal;
    private final ProveedorController proveedorController;
    private final Scanner input;

    public MenuProveedor() {
        this.menuPrincipal = new MenuPrincipal();
        this.proveedorController = new ProveedorController();
        this.input = new Scanner(System.in);
    }

    public int seleccionarOpcion() {
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
        return input.nextInt();
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
        return proveedorController.findOne(cuit) != null;
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
        System.out.print("El cuit proporcionado corresponde al proveedor: " +
                proveedor.getNombre() +
                ", CUIT: " + proveedor.getCuit() +
                ", Correo: " + proveedor.getCorreo() +
                ", Dirección: " + proveedor.getDireccion() +
                ", Teléfono: " + proveedor.getTelefono());
    }

    private void buscarUno() {
        System.out.println("Ingrese el CUIT del proveedor a buscar:");
        String cuitBuscado = input.next();
        while (proveedorController.findOneProv(cuitBuscado) == null && !(cuitBuscado.equals("0"))) {
            cuitBuscado = menuPrincipal.verificarExistencia("cuit");
        }
        if (cuitBuscado.equals("0")) {
            return;
        }
        Proveedor proveedorBuscado = proveedorController.findOneProv(cuitBuscado);
        mostrarInfoProveedor(proveedorBuscado);
        String condicion = proveedorBuscado.getHabilitado() ? "Habilitado" : "Deshabilitado";
        System.out.println("condición: " + condicion + ".");
    }

    private void buscarTodos() {
        for (Proveedor pr : proveedorController.findAllProv()) {
            this.mostrarInfoProveedor(pr);
            System.out.println(".\n");
        }
    }

    private void createProveedor() {
        System.out.println("\nProporcione los datos del nuevo Proveedor: ");
        System.out.print("Cuit: ");
        String cuit = input.next();
        while (proveedorController.findOneProv(cuit) != null && !(cuit.equals("0"))) {
            cuit = menuPrincipal.verificarAusencia("cuit");
        }
        if (cuit.equals("0")) {
            return;
        }
        Proveedor nuevoProveedor = new Proveedor(null, null, cuit, null, null, true);
        modificarDatosProveedor(nuevoProveedor);
        proveedorController.createProv(nuevoProveedor);
        System.out.println("Proveedor " + nuevoProveedor.getNombre() + " creado con éxito");
    }

    private void modificateProveedor() {
        System.out.println("\nIngrese el CUIT del proveedor a modificar:");
        String cuitModificar = input.next();
        while (proveedorController.findOneProv(cuitModificar) == null && !(cuitModificar.equals("0"))) {
            cuitModificar = menuPrincipal.verificarExistencia("cuit");
        }
        if (cuitModificar.equals("0")) {
            return;
        }
        Proveedor proveedorModificar = proveedorController.findOneProv(cuitModificar);
        modificarDatosProveedor(proveedorModificar);
        proveedorController.updateProv(proveedorModificar);
        System.out.println("Proveedor " + proveedorModificar.getNombre() + " Mmdificado con éxito.");
    }

    private void deleteProveedor() {
        System.out.println("\nIngrese el CUIT del proveedor a eliminar:");
        String cuitEliminar = input.next();
        while (proveedorController.findOneProv(cuitEliminar) == null && !(cuitEliminar.equals("0"))) {
            cuitEliminar = menuPrincipal.verificarExistencia("cuit");
        }
        if (cuitEliminar.equals("0")) {
            return;
        }
        Proveedor proveedorEliminar = proveedorController.findOneProv(cuitEliminar);
        proveedorController.deleteProv(cuitEliminar);
        System.out.println("El proveedor " + proveedorEliminar.getNombre() + " ha sido eliminado con éxito.");
    }

}


