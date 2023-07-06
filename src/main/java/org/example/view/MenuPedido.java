package org.example.view;

import org.example.controller.ClienteController;
import org.example.controller.LineaPedidoController;
import org.example.controller.PedidoController;
import org.example.controller.SucursalController;
import org.example.model.Pedido;
import org.example.model.Sector;

import java.util.Scanner;

public class MenuPedido {

    ClienteController clienteController = new ClienteController();

    PedidoController pedidoController = new PedidoController();
    LineaPedidoController lineaPedidoController = new LineaPedidoController();
    SucursalController sucursalController = new SucursalController();
    MenuLineaPedido menuLineaPedido = new MenuLineaPedido();
    MenuSucursal menuSucursal = new MenuSucursal();
    MenuTransportista menuTransportista = new MenuTransportista();
    Scanner input = new Scanner(System.in);

    public void añadirLineaPedido() {
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
                pedidoController.añadirLineaPedido(nuevoPedido, lineaPedidoController.findOne(id));
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

    }
}
