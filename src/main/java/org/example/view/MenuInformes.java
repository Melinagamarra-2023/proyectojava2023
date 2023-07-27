package org.example.view;

import org.example.controller.*;
import org.example.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuInformes {

    private final PedidoController pedidoController;

    private final ClienteController clienteController;
    private final Scanner input;

    public MenuInformes() {
        this.pedidoController = new PedidoController();
        this.clienteController = new ClienteController();
        this.input = new Scanner(System.in);
    }


    public int seleccionarOpcion() {
        System.out.println("""
                                
                ------- MENÚ INFORMES -------
                1. Informes de clientes.
                2. Informes de proveedores.
                3. Informes de transportistas.
                4. Informes de pedidos.
                """);
        return input.nextInt();
    }

    public void informeCliente() {
        input.nextLine();
        System.out.println("La aplicación posee " + clienteController.findAll().size() + " clientes registrados.");
        System.out.println("¿Desea obtener un informe detallado de uno de ellos? (s/n)");
        String respuesta = input.nextLine();
        if (respuesta.contains("s")) {
            String id = input.nextLine();
            Cliente cliente = clienteController.findOne(id);
            System.out.println("Cliente: " +
                    cliente.getApellido() + " " + cliente.getNombre() +
                    ", CUIT: " + cliente.getCuit() +
                    ", Correo: " + cliente.getCorreo() +
                    ", Dirección: " + cliente.getDireccion() +
                    ", Teléfono: " + cliente.getTelefono());
            List<Pedido> pedidos = new ArrayList<>();
            for (Pedido pedido : pedidoController.findAll()) {
                if (pedido.getCliente().equals(cliente)) {
                    pedidos.add(pedido);
                }
            }
            System.out.println("Este cliente ha realizado " + pedidos.size() + "pedidos.");
            List<Producto> productos = new ArrayList<>();
            for (Pedido pedido : pedidos) {
                for (int i = 0; i < pedido.getDetalle().size(); i++) {
                    productos.add(pedido.getDetalle().get(i).getProducto());
                }
            }
            for (Producto producto : productos) {
                String categoria = producto.getCategoria().getDescripcion();
            }
            System.out.println("Preferencias de este cliente: ");
        }
    }

}
