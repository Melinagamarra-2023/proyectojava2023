package org.example.view;

import org.example.controller.*;
import org.example.model.Pedido;
import org.example.model.Remito;
import org.example.model.SeguimientoPedido;


import java.util.ArrayList;
import java.util.Scanner;

public class MenuPedido {


    private final MenuPrincipal menuPrincipal;
    private final MenuSucursal menuSucursal;
    private final MenuTransportista menuTransportista;
    private final ClienteController clienteController;
    private final PedidoController pedidoController;
    private final SucursalController sucursalController;
    private final TransportistaController transportistaController;
    private final Scanner input;
    private int option;

    public MenuPedido() {
        this.menuPrincipal = new MenuPrincipal();
        this.menuSucursal = new MenuSucursal();
        this.menuTransportista = new MenuTransportista();
        this.clienteController = new ClienteController();
        this.pedidoController = new PedidoController();
        this.sucursalController = new SucursalController();
        this.transportistaController = new TransportistaController();
        this.input = new Scanner(System.in);
        this.option = 99;
    }

    public int seleccionarOpcion() {
        System.out.println("""
                                
                ----- MENÚ PEDIDOS -----
                Seleccione la opción:
                1. Gestionar linea pedido.
                2. Generar un pedido
                3. Cancelar un pedido.
                4. Buscar pedido por ID.
                5. Obtener lista de todos los pedidos.
                6. Cambiar estado de un pedido.
                7. Ver estados de un pedido.
                0. Salir.
                """);
        option = input.nextInt();
        return option;
    }

    public void generarPedido() {
        Pedido nuevoPedido = new Pedido("0", null, null, new ArrayList<>(), new ArrayList<>(), null, null);
        System.out.println("Ingrese el cuit del cliente:");
        String cuit = input.next();
        while (clienteController.findOne(cuit) == null && !(cuit.equals("0"))) {
            cuit = menuPrincipal.verificarExistencia("cuit");
        }
        if (cuit.equals("0")) {
            return;
        }
        nuevoPedido.setCliente(clienteController.findOne(cuit));
        this.armarCarrito(nuevoPedido);
    }

    private void armarCarrito(Pedido nuevoPedido) {
        while (option != 0) {
            System.out.println("Ingrese el id de la linea de pedido a añadir:");
            String id = input.next();
            while (pedidoController.findOneLP(id) == null && !(id.equals("0"))) {
                id = menuPrincipal.verificarExistencia("id");
            }
            if (id.equals("0")) {
                return;
            }
            pedidoController.agregarLineaPedido(nuevoPedido, id);
            System.out.println("Artículo " + pedidoController.findOneLP(id).getProducto().getNombre() + " agregado correctamente al pedido.");
            System.out.println("¿Desea añadir una línea de pedido nueva? (s/n)");
            String resp = input.next();
            if (resp.contains("n")) {
                option = 0;
            }
        }
        option = 99;
        this.seleccionarSucursales(nuevoPedido);
    }

    private void seleccionarSucursales(Pedido nuevoPedido) {
        System.out.println("Seleccione las sucursales de origen y destino para el pedido.");
        menuSucursal.buscarTodasLasSucursales();
        System.out.print("Ingrese el ID de la sucursal de origen: ");
        String idOrigen = input.next();
        while (sucursalController.findOne(idOrigen) == null && !(idOrigen.equals("0"))) {
            idOrigen = menuPrincipal.verificarExistencia("id");
        }
        if (idOrigen.equals("0")) {
            return;
        }
        nuevoPedido.setEncargado(pedidoController.setEmpleado(idOrigen));
        pedidoController.setSucursalOrigen(nuevoPedido, idOrigen);
        System.out.print("Ingrese el ID de la sucursal de destino: ");
        String idDestino = input.next();
        while (sucursalController.findOne(idDestino) == null && !(idDestino.equals("0"))) {
            idDestino = menuPrincipal.verificarExistencia("id");
        }
        if (idDestino.equals("0")) {
            return;
        }
        pedidoController.create(nuevoPedido);
        pedidoController.setSucursalDestino(nuevoPedido, idDestino);
        this.seleccionarTransportista(nuevoPedido, idOrigen, idDestino);
    }

    private void seleccionarTransportista(Pedido nuevoPedido, String idOrigen, String idDestino) {
        System.out.println("Seleccione el transporte que desea para su envío:");
        menuTransportista.buscarTransportistasPorTipo();
        System.out.println("Ingrese el ID del transportista elegido:");
        String cuit = input.next();
        while (transportistaController.findOne(cuit) == null && !(cuit.equals("0"))) {
            menuPrincipal.verificarExistencia("cuit");
        }
        if (cuit.equals("0")) {
            return;
        }
        pedidoController.createRemito(nuevoPedido, sucursalController.findOne(idOrigen), pedidoController.setEmpleado(idOrigen), sucursalController.findOne(idDestino), pedidoController.setEmpleado(idDestino), transportistaController.findOne(cuit));
        pedidoController.create(nuevoPedido);
        System.out.println("Pedido creado con éxito.");
    }

    public void mostrarPedidoPorId() {
        System.out.println("----------------------");
        System.out.println("Ingrese el id del pedido que desea buscar: ");
        String id = input.next();
        while (pedidoController.findOne(id) == null && !(id.equals("0"))) {
            menuPrincipal.verificarExistencia("id");
        }
        if (id.equals("0")) {
            return;
        }
        Pedido pedidoBuscado = pedidoController.findOne(id);
        System.out.println("El pedido asociado al ID ingresado es del "
                + "Cliente: " + pedidoBuscado.getCliente().getNombre()
                + ", SucursalOrigen: " + pedidoBuscado.getSucursalOrigen().getSucId()
                + ", SucursaldeDestino: " + pedidoBuscado.getSucursalDestino().getSucId());
    }

    public void mostrarTodosLosPedidos() {
        System.out.println("-----------------------------");
        System.out.println("Los pedidos registrados son: ");
        for (Pedido pe : pedidoController.findAll()) {
            System.out.println("Pedido N°" + pe.getPedidoId() +
                    " de Cliente: " + pe.getCliente().getApellido() + " " + pe.getCliente().getCuit() +
                    ", Sucursal Origen: " + pe.getSucursalOrigen().getSucId() +
                    " (" + pe.getSucursalOrigen().getContinente() + ")" +
                    ", Sucursal Destino: " + pe.getSucursalDestino().getSucId() +
                    " (" + pe.getSucursalDestino().getContinente() + ");");
        }

    }

    public void cancelarPedido() {
        System.out.println("--------------------------");
        System.out.println("Ingrese el ID del pedido que desea borrar");
        String id = input.next();
        while (pedidoController.findOne(id) == null && !(id.equals("0"))) {
            menuPrincipal.verificarExistencia("id");
        }
        if (id.equals("0")) {
            return;
        }
        Pedido pedidoEliminado = pedidoController.findOne(id);
            System.out.println("El pedido N°" + pedidoEliminado.getPedidoId() +
                    " de Cliente:  " + pedidoEliminado.getCliente().getNombre() +
                    " ha sido eliminada con exito");
        pedidoController.delete(id);
    }

    public void siguienteEstado() {
        input.nextLine();
        System.out.print("Ingrese el id del pedido: ");
        String id = input.nextLine();
        while (pedidoController.findOne(id) == null && !(id.equals("0"))) {
            menuPrincipal.verificarExistencia("id");
        }
        if (id.equals("0")) {
            return;
        }
        System.out.println("¿Desea cambiarlo de estado?");
        String resp = input.nextLine();
        if (resp.contains("s")) {
            pedidoController.siguienteEstado(pedidoController.findOne(id));
        }
    }

    public void verEstados() {
        input.nextLine();
        System.out.print("Ingrese el id del pedido: ");
        String id = input.nextLine();
        while (pedidoController.findOne(id) == null && !(id.equals("0"))) {
            id = menuPrincipal.verificarExistencia("id");
        }
        if (id.equals("0")) {
            return;
        }
        int estados = pedidoController.findOne(id).getSeguimientoPedido().size();
        for (int i = 0; i < estados; i++) {
            SeguimientoPedido seguimiento = pedidoController.findOne(id).getSeguimientoPedido().get(i);
            System.out.print((i + 1) + "° estado: " +
                    seguimiento.getEstado().getNombre() + " " +
                    seguimiento.getFechaYhora().getDayOfMonth() + "/" +
                    seguimiento.getFechaYhora().getMonthValue() + " - " +
                    seguimiento.getFechaYhora().getHour() + ":" +
                    seguimiento.getFechaYhora().getMinute());
            if (i < 5) {
                System.out.println(", En sucursal Origen.");
            } else if (seguimiento.getEstado().getNombre().equals("En transito")) {
                System.out.println(" (latitud: " + seguimiento.getLatitud() +
                        ", longitud: " + seguimiento.getLongitud() + ").");
            } else {
                System.out.println(", En sucursal de Destino.");
            }
        }
    }

    public void informarUbicacion() {
        input.nextLine();
        System.out.print("Ingrese su id: ");
        String cuit = input.nextLine();
        while (transportistaController.findOne(cuit) == null && !(cuit.equals("0"))) {
            cuit = menuPrincipal.verificarExistencia("cuit");
        }
        if (cuit.equals("0")) {
            return;
        }
        System.out.print("Ingrese su latitud: ");
        Double latitud = input.nextDouble();
        System.out.print("Ingrese su longitud: ");
        Double longitud = input.nextDouble();
        for (Remito remito : pedidoController.verRemitosPorTransportista(cuit)) {
            pedidoController.nuevoTransito(remito.getDetalle(), latitud, longitud);
        }
        System.out.println(pedidoController.verRemitosPorTransportista(cuit));
    }

    public int getOption() {
        return option;
    }

}
