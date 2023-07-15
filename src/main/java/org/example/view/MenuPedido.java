package org.example.view;

import org.example.controller.*;
import org.example.model.Pedido;


import java.util.ArrayList;
import java.util.Scanner;

public class MenuPedido {

    private final MenuSucursal menuSucursal;
    private final MenuTransportista menuTransportista;
    private final ClienteController clienteController;
    private final PedidoController pedidoController;
    private final SucursalController sucursalController;
    private final TransportistaController transportistaController;
    private final Scanner input;
    private int option;

    public MenuPedido() {
        this.clienteController = new ClienteController();
        this.pedidoController = new PedidoController();
        this.sucursalController = new SucursalController();
        this.menuSucursal = new MenuSucursal();
        this.menuTransportista = new MenuTransportista();
        this.transportistaController = new TransportistaController();
        this.input = new Scanner(System.in);
        this.option = 99;
    }

    public int seleccionarOpcion() {
        option = 99;
        System.out.println("""
                                
                ----- MENÚ PEDIDOS -----
                Seleccione la opción:
                1. Gestionar linea pedido.
                2. Generar un pedido
                3. Cancelar un pedido.
                4. Buscar pedido por ID.
                5. Obtener lista de todos los pedidos.
                0. Salir.
                """);
        option = input.nextInt();
        return option;
    }

    public void generarPedido() {
        Pedido nuevoPedido = new Pedido("0", null, null, new ArrayList<>(), new ArrayList<>(), null, null);
        System.out.println("Ingrese el cuit del cliente:");
        String id = input.next();
        while (clienteController.findOne(id) == null && !(id.equals("0"))) {
            id = this.verification();
        }
        this.cancelar(id);
        nuevoPedido.setCliente(clienteController.findOne(id));
        this.armarCarrito(nuevoPedido);
    }

    private void armarCarrito(Pedido nuevoPedido) {
        while (option != 0) {
            System.out.println("Ingrese el id de la linea de pedido a añadir:");
            String id = input.next();
            while ((pedidoController.findOneLP(id) == null && !(id.equals("0")))) {
                id = this.verification();
            }
            this.cancelar(id);
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
            idOrigen = this.verification();
        }
        this.cancelar(idOrigen);
        nuevoPedido.setEncargado(pedidoController.setEmpleado(idOrigen));
        pedidoController.setSucursalOrigen(nuevoPedido, idOrigen);
        System.out.print("Ingrese el ID de la sucursal de destino: ");
        String idDestino = input.next();
        while (sucursalController.findOne(idDestino) == null && !(idOrigen.equals("0"))) {
            idDestino = this.verification();
        }
        this.cancelar(idDestino);
        pedidoController.setSucursalDestino(nuevoPedido, idDestino);
        this.seleccionarTransportista(nuevoPedido, idOrigen, idDestino);
    }

    private void seleccionarTransportista(Pedido nuevoPedido, String idOrigen, String idDestino) {
        System.out.println("Seleccione el transporte que desea para su envío:");
        menuTransportista.buscarTransportistasPorTipo();
        System.out.println("Ingrese el ID del transportista elegido:");
        String id = input.next();
        while (transportistaController.findOne(id) == null && !(id.equals("0"))) {
            this.verification();
        }
        this.cancelar(id);
        pedidoController.createRemito(nuevoPedido, sucursalController.findOne(idOrigen), pedidoController.setEmpleado(idOrigen), sucursalController.findOne(idDestino), pedidoController.setEmpleado(idDestino), transportistaController.findOne(id));
        System.out.println("Pedido creado con éxito.");
    }

    public void mostrarTodosLosPedidos() {
        System.out.println("-----------------------------");
        System.out.println("Los pedidos registrados son: ");
        for (Pedido pe : pedidoController.findAll()) {
            System.out.println("Pedido N°" + pe.getPedidoId() +
                    " de Cliente: " + pe.getCliente().getApellido() + " " + pe.getCliente().getCuit() +
                    ", Sucursal Origen: " + pe.getSucursalOrigen().getSucId() +
                    "(" + pe.getSucursalOrigen().getContinente() + ")" +
                    ", Sucursal Destino: " + pe.getSucursalDestino().getSucId() +
                    "(" + pe.getSucursalDestino().getContinente() + ")" + ";");
        }
    }

    public int getOption() {
        return option;
    }

    private String verification() {
        System.out.println("El id ingresado no existe, intente nuevamente. (0 para cancelar)");
        System.out.print("ID: ");
        return input.next();
    }

    private void cancelar(String respuesta) {
        if (respuesta.equals("0")) {
            System.out.println("Operación cancelada.");
            seleccionarOpcion();
        }
    }

}
