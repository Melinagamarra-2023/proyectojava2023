package org.example.view;

import org.example.controller.*;
import org.example.model.Pedido;


import java.util.Scanner;

public class MenuPedido {

    private final ClienteController clienteController;
    private final PedidoController pedidoController;
    private final TransportistaController transportistaController;
    private final LineaPedidoController lineaPedidoController;
    private final SucursalController sucursalController;
    private final MenuLineaPedido menuLineaPedido;
    private final MenuSucursal menuSucursal;
    private final MenuTransportista menuTransportista;

    public MenuPedido() {
        this.clienteController = new ClienteController();
        this.pedidoController = new PedidoController();
        this.lineaPedidoController = new LineaPedidoController();
        this.sucursalController = new SucursalController();
        this.menuLineaPedido = new MenuLineaPedido();
        this.menuSucursal = new MenuSucursal();
        this.menuTransportista = new MenuTransportista();
        this.transportistaController = new TransportistaController();
    }

    Scanner input = new Scanner(System.in);

    public void agregarLineaPedido() {
        Pedido nuevoPedido = new Pedido("0", null, null, null, null, null, null, null);
        System.out.println("Ingrese el cuit del cliente:");
        String id = input.next();
        nuevoPedido.setCliente(clienteController.findOne(id));
        int salir = 1;
        menuLineaPedido.seleccionarOpcion();
        //arma el carrito
        while (salir == 0) {
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
        }
        //elige origen y destino
        System.out.println("Seleccione la sucursal de origen.");
        menuSucursal.buscarTodasLasSucursales();
        System.out.println("Ingrese el ID de la sucursal de origen para el pedido:");
        id = input.next();
        pedidoController.setSectorOrigen(nuevoPedido, sucursalController.findOne(id).getSucId() + "1");
        System.out.println("Ingrese el ID de la surcusal de destino para el pedido:");
        id = input.next();
        pedidoController.setSectorDestino(nuevoPedido, sucursalController.findOne(id).getSucId() + "9");
        //seleccionar transportista
        System.out.println("Seleccione el transporte que desea para su envio:");
        menuTransportista.buscarTransportistasPorTipo();
        System.out.println("Ingrese el ID del transportista elegido:");
        id = input.next();
    }
}
