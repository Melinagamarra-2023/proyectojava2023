package org.example.view;

import org.example.controller.LineaPedidoController;
import org.example.controller.PedidoController;
import org.example.model.Pedido;

import java.util.Scanner;

public class MenuPedido {
    PedidoController pedidoController = new PedidoController();
    LineaPedidoController lineaPedidoController = new LineaPedidoController();
    MenuLineaPedido menuLineaPedido = new MenuLineaPedido();
    MenuSucursal menuSucursal = new MenuSucursal();
    Scanner input = new Scanner(System.in);

    public void añadirLineaPedido() {
        int salir = 1;
        menuLineaPedido.seleccionarOpcion();
        Pedido nuevoPedido = new Pedido("0", null, null, null, null, null, null, null);
        while (salir == 0) {
            System.out.println("Ingrese los artículos del carrito a añadir al pedido:");
            String id = input.next();
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
        System.out.println("Seleccione la sucursal de origen.");
    }
}
