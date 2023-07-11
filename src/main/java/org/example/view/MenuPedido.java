package org.example.view;

import org.example.controller.*;
import org.example.model.Pedido;
import org.example.model.Remito;


import java.util.Scanner;

public class MenuPedido {

    private final MenuLineaPedido menuLineaPedido;
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
        this.menuLineaPedido = new MenuLineaPedido();
        this.menuSucursal = new MenuSucursal();
        this.menuTransportista = new MenuTransportista();
        this.transportistaController = new TransportistaController();
        this.input = new Scanner(System.in);
        this.option = 99;
    }

    public int seleccionarOpcion() {
        option = 99;
        System.out.println("""
                                
                ----- MENÚ PRODUCTOS -----
                Seleccione la opción:
                1. Agregar un producto.
                2. Modificar un producto.
                3. Eliminar un producto.
                4. Buscar por ID un producto.
                5. Obtener lista de todos los productos.
                0. Salir.
                """);
        option = input.nextInt();
        return option;
    }

    public void generarPedido() {
        Pedido nuevoPedido = new Pedido("0", null, null, null, null, null, null);
        System.out.println("Ingrese el cuit del cliente:");
        String id = input.next();
        nuevoPedido.setCliente(clienteController.findOne(id));
        int salir = 1;
        menuLineaPedido.seleccionarOpcion();
        do {
        //arma el carrito (varias LineaPedido)
            System.out.println("Ingrese los artículos del carrito a añadir al pedido:");
            id = input.next();
            if (pedidoController.findOneLP(id) != null) {
                pedidoController.agregarLineaPedido(nuevoPedido, pedidoController.findOneLP(id));
            } else {
                System.out.println("Ingrese una línea de pedido válida.");
            }
            System.out.println("¿Desea añadir una línea de pedido nueva? (s/n)");
            String resp = input.next();
            if (resp.contains("n")) {
                salir = 0;
            }
        } while (salir == 0);
        //elige origen y destino
        System.out.println("Seleccione la sucursal de origen.");
        menuSucursal.buscarTodasLasSucursales();
        System.out.println("Ingrese el ID de la sucursal de origen para el pedido:");
        String idOrigen = input.next();
        pedidoController.setSectorOrigen(nuevoPedido, idOrigen);
        System.out.println("Ingrese el ID de la surcusal de destino para el pedido:");
        String idDestino = input.next();
        pedidoController.setSectorDestino(nuevoPedido, idDestino);
        //seleccionar transportista
        System.out.println("Seleccione el transporte que desea para su envio:");
        menuTransportista.buscarTransportistasPorTipo();
        System.out.println("Ingrese el ID del transportista elegido:");
        id = input.next();
        pedidoController.createRemito(null, null, null, null, null, null);
    }

    public int getOption() {
        return option;
    }

}
