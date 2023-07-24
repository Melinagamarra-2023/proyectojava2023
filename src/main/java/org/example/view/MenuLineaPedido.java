package org.example.view;

import org.example.controller.PedidoController;
import org.example.controller.ProductoController;
import org.example.model.LineaPedido;

import java.util.Scanner;

public class MenuLineaPedido {

    private final PedidoController pedidoController;
    private final MenuProducto menuProducto;
    private final ProductoController productoController;
    private final Scanner input;
    private int option;

    public MenuLineaPedido() {
        this.pedidoController = new PedidoController();
        this.menuProducto = new MenuProducto();
        this.productoController = new ProductoController();
        this.input = new Scanner(System.in);
        this.option = 99;
    }

    public int seleccionarOpcion() {
        System.out.println("""
                                
                --- MENÚ LINEAS DE PEDIDO ---
                Seleccione la opción:
                1. Crear linea pedido.
                2. Modificar linea pedido.
                3. Eliminar linea pedido.
                4. Mostrar lineas de pedido creadas.
                5. Generar Pedido.
                6. Calificar un provedor.
                0. Cancelar.
                """);
        option = input.nextInt();
        return option;
    }

    public void seleccionarProductos() {
        while (option != 0) {
            System.out.println("""
                                    
                    Seleccione la opción:
                    1. Mostrar todos los productos.
                    2. Buscar productos por nombre.
                    3. Buscar productos por categoría.
                    4. Añadir articulo a linea pedido.
                    0. Cancelar.
                    """);
            option = input.nextInt();
            switch (option) {
                case 1 -> menuProducto.buscarTodosLosProductos();
                case 2 -> menuProducto.buscarProductoPorId(); //CAMBIAR...
                case 3 -> menuProducto.buscarPorCategoria(); //CAMBIAR CASO DUPLICADO...
                case 4 -> generarLineaPedido();
                default -> System.out.println("Ingrese una opción correcta.");
            }
        }
    }

    private void generarLineaPedido() {
        crearLineaPedido();
    }

    public void buscarLineasPedido() {
        buscarTodos();
    }

    public void modificarLineaPedido() {
        modificateLineaPedido();
    }

    public void eliminarLineaPedido() {
        deleteLineaPedido();
    }

    public void calificarProveedor() {
        calificacion();
    }

    private void crearLineaPedido() {
        System.out.print("Ingrese el id del producto que desea añadir: ");
        String id = input.next();
        LineaPedido lineaPedido = new LineaPedido(null, 0, productoController.findOne(id), true, true, 0);
        System.out.print("Determine la cantidad que desea de este producto: ");
        int cantidad = input.nextInt();
        lineaPedido.setCantidad(cantidad);
        pedidoController.createLP(lineaPedido);
    }

    private void buscarTodos() {
        System.out.println("\n");
        for (LineaPedido lp : pedidoController.findAllLP()) {
            System.out.println("LineaPedido N°" + lp.getCodigo() +
                    " Producto: " + lp.getProducto().getNombre() +
                    ", Cantidad: " + lp.getCantidad() + ";");
        }
        System.out.println("\n");
    }

    private void modificateLineaPedido() {
        System.out.print("Ingrese el id de la linea pedido a modificar: ");
        String codigo = input.next();
        LineaPedido lineaPedidoModificar = pedidoController.findOneLP(codigo);
        while (lineaPedidoModificar == null) {
            System.out.println("Este codigo no existe, intentelo de nuevo. (0 para cancelar)");
            System.out.print("ID: ");
            codigo = input.next();
            if (codigo.equals("0")) {
                System.out.println("Operación cancelada.");
                seleccionarOpcion();
            } else {
                lineaPedidoModificar = pedidoController.findOneLP(codigo);
            }
        }
        System.out.print("Determine la cantidad que desea de este producto: ");
        int cantidad = input.nextInt();
        lineaPedidoModificar.setCantidad(cantidad);
        pedidoController.updateLP(lineaPedidoModificar);
        System.out.println("Linea pedido modificada con exito.");
    }

    private void deleteLineaPedido() {
        System.out.print("Ingrese el id de la linea pedido a eliminar: ");
        String codigo = input.next();
        LineaPedido lineaPedidoEliminar = pedidoController.findOneLP(codigo);
        if (lineaPedidoEliminar != null) {
            pedidoController.deleteLP(codigo);
            System.out.println("La linea pedido " + lineaPedidoEliminar.getCodigo() + "Ha sido eliminada.");
        } else {
            System.out.println("Esta linea pedido no existe.");
        }
    }

    private void calificacion() {
        System.out.print("Ingrese el id de la linea pedido: ");
        String codigo = input.next();
        if (pedidoController.findOne(codigo) != null) {
            System.out.println("Ingrese una calificación, del 1 al 5 para este proveedor: ");
            int star = input.nextInt();
            pedidoController.calificarProveedor(pedidoController.findOneLP(codigo), star);
        } else {
            System.out.println("Esta linea pedido no existe.");
        }
    }

    public int getOption() {
        return option;
    }
}
