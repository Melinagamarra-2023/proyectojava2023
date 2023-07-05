package org.example.view;

import org.example.controller.LineaPedidoController;
import org.example.controller.ProductoController;
import org.example.model.LineaPedido;

import java.util.Scanner;

public class MenuLineaPedido {

    LineaPedidoController lineaPedidoController = new LineaPedidoController();
    ProductoController productoController = new ProductoController();
    Scanner input = new Scanner(System.in);
    int option = 99;

    public int seleccionarOpcion() {
        System.out.println("""             
                Seleccione la opción:
                1. Crear linea pedido.
                2. Modificar line pedido.
                3. Eliminar linea pedido.
                5. Obtener lista de lineas de pedido creadas.
                6. Generar Pedido.
                0. Salir.
                """);
        option = input.nextInt();
        return option;
    }

    public void añadirALineaPedido() {
        System.out.print("Seleccione la categoria del producto que desea añadir: ");
        productoController.findAll(); //CAMBIAR, COMO TE VAS A OLVIDAR DE BUSCAR POR CATEGORIA..... ESO NO SE USA MAS.
        System.out.print("Ingrese el id del producto que desea añadir: ");
        String id = input.next();
        LineaPedido lineaPedido = new LineaPedido(0, 0, productoController.findOne(id));
        System.out.print("Determine la cantidad que desea de este producto: ");
        int cantidad = input.nextInt();
        lineaPedido.setCantidad(cantidad);
        lineaPedidoController.create(lineaPedido);
    }

    public void modificarLineaPedido() {
        System.out.print("Ingrese el id de la linea pedido a modificar: ");
        int codigo = input.nextInt();
        LineaPedido lineaPedidoModificar = lineaPedidoController.findOne(codigo);
        if (lineaPedidoModificar != null) {
            System.out.print("Seleccione la categoria del producto que desea añadir: ");
            productoController.findAll(); //CAMBIAR, COMO TE VAS A OLVIDAR DE BUSCAR POR CATEGORIA..... ESO NO SE USA MAS.
            System.out.print("Ingrese el id del producto que desea añadir: ");
            String id = input.next();
            lineaPedidoModificar.setProducto(productoController.findOne(id));
            System.out.print("Determine la cantidad que desea de este producto: ");
            int cantidad = input.nextInt();
            lineaPedidoModificar.setCantidad(cantidad);
            lineaPedidoController.update(lineaPedidoModificar);
            System.out.println("Linea pedido modificada con exito.");
        }else{
            System.out.println("Esta linea pedido no existe.");
        }
    }

    public void eliminarLineaPedido() {
        System.out.print("Ingrese el id de la linea pedido a eliminar: ");
        int codigo = input.nextInt();
        LineaPedido lineaPedidoEliminar = lineaPedidoController.findOne(codigo);
        if (lineaPedidoEliminar != null) {
            lineaPedidoController.delete(lineaPedidoEliminar);
        }else{
            System.out.println("Esta linea pedido no existe.");
        }
    }
}
