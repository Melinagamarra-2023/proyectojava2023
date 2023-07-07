package org.example.view;

import org.example.controller.LineaPedidoController;
import org.example.controller.ProductoController;
import org.example.model.LineaPedido;

import java.util.Scanner;

public class MenuLineaPedido {

    LineaPedidoController lineaPedidoController = new LineaPedidoController();
    MenuProducto menuProducto = new MenuProducto();
    ProductoController productoController = new ProductoController();
    Scanner input = new Scanner(System.in);
    int option = 99;

    public int seleccionarOpcion() {
        option = 99;
        System.out.println("""
                
                --- MENÚ LINEAS DE PEDIDO ---
                Seleccione la opción:
                1. Crear linea pedido.
                2. Modificar linea pedido.
                3. Eliminar linea pedido.
                4. Mostrar lineas de pedido creadas.
                5. Generar Pedido.
                0. Cancelar.
                """);
        option = input.nextInt();
        return option;
    }

    public void seleccionarProductos() {
        System.out.println("""
                Seleccione la opción:
                1. Mostrar todos los productos.
                2. Buscar productos por nombre.
                3. Buscar productos por categoria.
                4. Crear linea pedido.
                0. Cancelar.
                """);
        int opc = input.nextInt();
        switch (opc) {
            case 1 -> menuProducto.buscarTodosLosProductos();
            case 2 -> menuProducto.buscarProductoPorId(); //CAMBIAR...
            case 3 -> menuProducto.buscarProductoPorId(); //CAMBIAR...
            case 4 -> añadirLineaPedido();
        }
    }

    private void añadirLineaPedido() {
        System.out.print("Ingrese el id del producto que desea añadir: ");
        String id = input.next();
        LineaPedido lineaPedido = new LineaPedido(null, 0, productoController.findOne(id), 0, 0);
        System.out.print("Determine la cantidad que desea de este producto: ");
        int cantidad = input.nextInt();
        lineaPedido.setCantidad(cantidad);
        lineaPedidoController.create(lineaPedido);
    }

    public void buscarLineasPedido() {
        System.out.println("\n");
        for (LineaPedido lp : lineaPedidoController.findAll()) {
            System.out.println("LineaPedido N°" + lp.getCodigo() +
                    " Producto: " + lp.getProducto().getNombre() +
                    " Cantidad: " + lp.getCantidad() + ";");
        }
        System.out.println("\n");
    }

    public void modificarLineaPedido() {
        System.out.print("Ingrese el id de la linea pedido a modificar: ");
        String codigo = input.next();
        LineaPedido lineaPedidoModificar = lineaPedidoController.findOne(codigo);
        if (lineaPedidoModificar != null) {
            System.out.print("Determine la cantidad que desea de este producto: ");
            int cantidad = input.nextInt();
            lineaPedidoModificar.setCantidad(cantidad);
            lineaPedidoController.update(lineaPedidoModificar);
            System.out.println("Linea pedido modificada con exito.");
        } else {
            System.out.println("Esta linea pedido no existe.");
        }
    }

    public void eliminarLineaPedido() {
        System.out.print("Ingrese el id de la linea pedido a eliminar: ");
        String codigo = input.next();
        LineaPedido lineaPedidoEliminar = lineaPedidoController.findOne(codigo);
        if (lineaPedidoEliminar != null) {
            lineaPedidoController.delete(lineaPedidoEliminar.getCodigo());
        } else {
            System.out.println("Esta linea pedido no existe.");
        }
    }

    public void calificarProveedor() {
        System.out.print("Ingrese el id de la linea pedido: ");
        String codigo = input.next();
        if (lineaPedidoController.findOne(codigo) != null) {
            System.out.println("Ingrese una calificación, del 1 al 5 para este proveedor: ");
            int star = input.nextInt();
            lineaPedidoController.calificarProveedor(lineaPedidoController.findOne(codigo), star);
        } else {
            System.out.println("Esta linea pedido no existe.");
        }
    }

    public void calificarTransportista() {
        System.out.print("Ingrese el id de la linea pedido: ");
        String codigo = input.next();
        if (lineaPedidoController.findOne(codigo) != null) {
            System.out.println("Ingrese una calificación, del 1 al 5 para este proveedor: ");
            int star = input.nextInt();
            lineaPedidoController.calificarTransportista(lineaPedidoController.findOne(codigo), star);
        } else {
            System.out.println("Esta linea pedido no existe.");
        }
    }

    public int getOption() {
        return option;
    }
}
