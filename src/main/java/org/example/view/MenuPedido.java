package org.example.view;

import org.example.controller.*;
import org.example.model.Pedido;

import java.util.Scanner;

public class MenuPedido {

    private final MenuLineaPedido menuLineaPedido;
    private final MenuSucursal menuSucursal;
    private final MenuTransportista menuTransportista;
    private final ClienteController clienteController;
    private final PedidoController pedidoController;
    private final LineaPedidoController lineaPedidoController;
    private final SucursalController sucursalController;
    private final TransportistaController transportistaController;
    private final Scanner input;
    private int option;

    public MenuPedido() {
        this.menuLineaPedido = new MenuLineaPedido();
        this.menuSucursal = new MenuSucursal();
        this.menuTransportista = new MenuTransportista();
        this.clienteController = new ClienteController();
        this.pedidoController = new PedidoController();
        this.lineaPedidoController = new LineaPedidoController();
        this.sucursalController = new SucursalController();
        this.transportistaController = new TransportistaController();
        this.input = new Scanner(System.in);
        this.option = 99;
    }

    public int seleccionarOpcion() {
        option = 99;
        System.out.println("""
                                
                ------- MENÚ PEDIDO -------
                Seleccione la opción:
                1. Crear nuevo pedido.
                2. Modificar destino de un pedido.
                3. Cancelar pedido.
                4. Mostrar todos los pedidos.
                5. Mostrar pedidos por cliente.
                6. Buscar pedido por ID.
                0. Cancelar.
                """);
        option = input.nextInt();
        return option;
    }

    public void generarPedido() {
        //
        //se crea un pedido vacío y primero se setea al cliente.
        //
        Pedido nuevoPedido = new Pedido("0", null, null, null, null, null, null, null);
        System.out.println("Ingrese el cuit del cliente:");
        String id = input.next();
        int salir = 1;
        do {
            if (clienteController.findOne(id) == null) {
                nuevoPedido.setCliente(clienteController.findOne(id));
                salir = 0;
            } else {
                System.out.println("Este cliente no existe, intentelo de nuevo. (0 para cancelar)");
                System.out.print("cuit: ");
                id = input.next();
                if (id.equals("0")) {
                    System.out.println("Operación cancelada.");
                    seleccionarOpcion();
                }
            }
        } while (salir == 0);
        //
        //se arma el carrito.
        //
        menuLineaPedido.seleccionarOpcion();
        do {
            System.out.println("Ingrese los artículos del carrito a añadir al pedido:");
            id = input.next();
            if (lineaPedidoController.findOne(id) != null) {
                pedidoController.agregarLineaPedido(nuevoPedido, lineaPedidoController.findOne(id));
            } else {
                System.out.println("Ingrese una línea de pedido válida.");
            }
            System.out.println("¿Desea añadir una línea de pedido nueva? (s/n)");
            String resp = input.next();
            if (resp.contains("s")) {
                salir = 0;
            }
        } while (salir == 0);
        //
        //se elige origen y destino, adicionalmente se setea al empleado encargado (sucursal origen).
        //
        System.out.println("Seleccione la sucursal de origen.");
        menuSucursal.buscarTodasLasSucursales();
        System.out.println("Ingrese el ID de la sucursal de origen para el pedido:");
        String idOrigen = input.next();
        pedidoController.setSectorOrigen(nuevoPedido, sucursalController.findOne(id).getSucId() + "1");
        System.out.println("Ingrese el ID de la surcusal de destino para el pedido:");
        String idDestino = input.next();
        pedidoController.setSectorDestino(nuevoPedido, sucursalController.findOne(id).getSucId() + "9");
        //
        //seleccionar transportista.
        //
        System.out.println("Seleccione el transporte que desea para su envio:");
        menuTransportista.buscarTransportistasPorTipo();
        System.out.println("Ingrese el ID del transportista elegido:");
        id = input.next();
        pedidoController.createRemito(nuevoPedido, sucursalController.findOne(idOrigen), sucursalController.findOne(idDestino), transportistaController.findOne(id));
        //
        //pedido creado con exito.
        //
    }

    public int getOption() {
        return option;
    }
}
