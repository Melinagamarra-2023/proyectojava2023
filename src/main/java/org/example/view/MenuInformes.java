package org.example.view;

import org.example.controller.*;
import org.example.model.*;

import javax.swing.event.CaretListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuInformes {

    private final MenuPrincipal menuPrincipal;
    private final MenuCliente menuCliente;
    private final MenuPedido menuPedido;
    private final ClienteController clienteController;
    private final Scanner input;
    private int option;

    public MenuInformes() {
        this.menuPrincipal = new MenuPrincipal();
        this.menuCliente = new MenuCliente();
        this.menuPedido = new MenuPedido();
        this.clienteController = new ClienteController();
        this.input = new Scanner(System.in);
        this.option = 99;
    }


    public int seleccionarOpcion() {
        System.out.println("""
                                
                ------- MENÚ INFORMES -------
                1. Informes de clientes.
                2. Informes de proveedores.
                3. Informes de transportistas.
                4. Informes de pedidos.
                0. Salir.
                """);
        option = input.nextInt();
        return option;
    }

}