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

    public int seleccionarOpcion() {
        System.out.println("""
                                
                --- MENÚ LINEAS DE PEDIDO ---
                Seleccione la opción:
                1. Crear linea pedido.
                2. Modificar linea pedido.
                3. Eliminar linea pedido.
                4. Mostrar lineas de pedido creadas.
                5. Generar Pedido.
                6. Calificar un proveedor.
                0. Cancelar.
                """);
        return input.nextInt();
    }

    public void seleccionarProductos() {
        int option = 99;
        while (option != 0) {
            System.out.println("""
                                    
                    Ingrese el producto para añadir a la linea pedido:
                    1. Mostrar todos los productos.
                    2. Buscar productos por nombre.
                    3. Buscar productos por categoría.
                    4. Añadir articulo a linea pedido.
                    0. Cancelar.
                    """);
            option = input.nextInt();
            switch (option) {
                case 1 -> menuProducto.buscarTodosLosProductos();
                case 2 -> menuProducto.buscarPorNombre(); //CAMBIAR...
                case 3 -> menuProducto.buscarPorCategoria();
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
        while (proveedorController.findOne(id) == null && !(id.equals("0"))) {
            id = menuPrincipal.verificarExistencia("id");
        }
        if (id.equals("0")) {
            return;
        }
        LineaPedido lineaPedido = new LineaPedido(null, 0, proveedorController.findOne(id), true, true, 0);
        System.out.print("Determine la cantidad que desea de este producto: ");
        int cantidad = input.nextInt();
        lineaPedido.setCantidad(cantidad);
        pedidoController.createLP(lineaPedido);
    }

    private void buscarTodos() {
        for (LineaPedido lp : pedidoController.findAllLP()) {
            System.out.println("LineaPedido N°" + lp.getCodigo() +
                    " Producto: " + lp.getProducto().getNombre() +
                    ", Cantidad: " + lp.getCantidad() + ";");
        }
    }

    private void modificateLineaPedido() {
        System.out.print("Ingrese el id de la linea pedido a modificar: ");
        String id = input.next();
        while (pedidoController.findOneLP(id) == null && !(id.equals("0"))) {
            id = menuPrincipal.verificarExistencia("id");
        }
        if (id.equals("0")) {
            return;
        }
        LineaPedido lineaPedidoModificar = pedidoController.findOneLP(id);
        System.out.print("Determine la nueva cantidad para este producto: ");
        int cantidad = input.nextInt();
        lineaPedidoModificar.setCantidad(cantidad);
        pedidoController.updateLP(lineaPedidoModificar);
        System.out.println("Linea pedido " + lineaPedidoModificar.getCodigo() + " modificada con exito.");
    }

    private void deleteLineaPedido() {
        System.out.print("Ingrese el id de la linea pedido a eliminar: ");
        String id = input.next();
        while (pedidoController.findOneLP(id) == null && !(id.equals("0"))) {
            id = menuPrincipal.verificarExistencia("id");
        }
        if (id.equals("0")) {
            return;
        }
        pedidoController.deleteLP(id);
        System.out.println("La linea pedido " + id + "Ha sido eliminada.");
    }

    private void calificacion() {
        System.out.print("Ingrese el id de la linea pedido: ");
        String id = input.next();
        while (pedidoController.findOneLP(id) == null && !(id.equals("0"))) {
            id = menuPrincipal.verificarExistencia("id");
        }
        if (id.equals("0")) {
            return;
        }
        System.out.println("Ingrese una calificación, del 1 al 5 para este proveedor: ");
        int star = input.nextInt();
        while (star < 0 || 5 < star) {
            System.out.print("Ingrese una calificación válida (0 para cancelar): ");
            star = input.nextInt();
        }
        if (star == 0) {
            return;
        }
        pedidoController.calificarProveedor(pedidoController.findOneLP(id), star);
    }

}
