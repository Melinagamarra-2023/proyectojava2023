package org.example.view;

import org.example.controller.PedidoController;
import org.example.controller.ProveedorController;
import org.example.model.LineaPedido;

import java.util.Scanner;

public class MenuLineaPedido {

    private final MenuPrincipal menuPrincipal;
    private final MenuProducto menuProducto;
    private final PedidoController pedidoController;
    private final ProveedorController proveedorController;
    private final Scanner input;

    public MenuLineaPedido() {
        this.menuPrincipal = new MenuPrincipal();
        this.menuProducto = new MenuProducto();
        this.pedidoController = new PedidoController();
        this.proveedorController = new ProveedorController();
        this.input = new Scanner(System.in);
    }


}
