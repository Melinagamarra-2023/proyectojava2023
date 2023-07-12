package org.example.view;

import org.example.controller.ProductoController;
import org.example.model.Producto;


import java.util.Scanner;

public class MenuProducto {

    private final ProductoController productoController;
    private final Scanner input;
    private int option;

    public MenuProducto() {
        this.productoController = new ProductoController();
        this.input = new Scanner(System.in);
        this.option = 99;
    }

    public int seleccionarOpcion() {
        option = 99;
        System.out.println("""
                                
                ----- MENÚ PRODUCTOS -----
                Seleccione la opción:
                1. Agregar un producto.
                2. Modificar un producto.
                3. Eliminar un producto.
                4. Buscar por ID un producto.
                5. Obtener lista de todos los productos.
                0. Salir.
                """);
        option = input.nextInt();
        return option;
    }

    public void crearProducto() {
        System.out.println("\nProporcione los datos para el nuevo producto: ");
        System.out.print("Id: ");
        String id = input.next();
        while (existeProducto(id)) {
            System.out.println("Este ID ya existe, intentelo de nuevo. (0 para cancelar)");
            System.out.print("ID: ");
            id = input.next();
            if (id.equals("0")) {
                System.out.println("Operación cancelada.");
                seleccionarOpcion();
            } else {
                existeProducto(id);
            }
        }
        System.out.print("Nombre: ");
        String nombre = input.next();
        System.out.print("Descripcion: ");
        String descripcion = input.next();
        input.nextLine();
        System.out.print("Ancho: ");
        Double ancho = input.nextDouble();
        System.out.print("Alto: ");
        Double alto = input.nextDouble();
        System.out.print("Profundidad: ");
        Double profundidad = input.nextDouble();
        System.out.print("Peso: ");
        Double peso = input.nextDouble();
        Producto nuevoProducto = new Producto(id, nombre, descripcion, ancho, alto, profundidad, peso, null, null, true);
        System.out.println("Proporcione el cuit del proveedor del producto: ");
        String cuitProveedor = input.next();
        if (cuitProveedor != null) {
            productoController.setProveedor(nuevoProducto, cuitProveedor);
            this.setCategoria(nuevoProducto);
            productoController.create(nuevoProducto);
            System.out.println("Producto: " + nuevoProducto.getNombre() + " Creado con éxito");
        } else {
            System.out.println("Ingrese un cuit que exista.");
        }
    }

    public void modificarProducto() {
        System.out.println("\nIngrese el ID del producto a modificar:");
        String idModificar = input.next();
        Producto productoModificar = productoController.findOne(idModificar);
        while (productoModificar == null) {
            System.out.println("Este ID ya existe, intentelo de nuevo. (0 para cancelar)");
            System.out.print("ID: ");
            idModificar = input.next();
            if (idModificar.equals("0")) {
                System.out.println("Operación cancelada.");
                seleccionarOpcion();
            } else {
                productoModificar = productoController.findOne(idModificar);
            }
        }
        System.out.println("Ingrese los nuevos datos para este producto:");
        System.out.print("Nombre: ");
        String nombreModificar = input.next();
        System.out.print("Descripcion: ");
        String descripcionModificar = input.next();
        System.out.print("Ancho: ");
        Double anchoModificar = input.nextDouble();
        System.out.print("Alto: ");
        Double altoModificar = input.nextDouble();
        System.out.print("Profundidad: ");
        Double profundidadModificar = input.nextDouble();
        System.out.print("Peso: ");
        Double pesoModificar = input.nextDouble();
        productoModificar.setNombre(nombreModificar);
        productoModificar.setDescripcion(descripcionModificar);
        productoModificar.setAncho(anchoModificar);
        productoModificar.setAlto(altoModificar);
        productoModificar.setProfundidad(profundidadModificar);
        productoModificar.setPeso(pesoModificar);
        System.out.println("Proporcione el cuit del proveedor del producto: ");
        String cuitProveedor = input.next();
        if (productoController.findOnePr(cuitProveedor) != null) {
            productoController.setProveedor(productoModificar, cuitProveedor);
            this.setCategoria(productoModificar);
            productoController.update(productoModificar);
            System.out.println("Producto: " + productoModificar.getId() + " Modificado con éxito.");
        } else {
            System.out.println("Proporcione un cuit que exista.");
        }
    }

    public void eliminarProducto() {
        System.out.println("\nIngrese el ID del producto a eliminar:");
        String idEliminar = input.next();
        Producto productoEliminar = productoController.findOne(idEliminar);
        if (productoEliminar != null) {
            productoController.delete(idEliminar);
            System.out.println("El producto " + productoEliminar.getNombre() + " ha sido eliminado con éxito.");
        } else {
            System.out.println("No se encontró ningún producto con el ID proporcionado.");
        }
    }

    public void buscarProductoPorId() {
        System.out.println("\nIngrese el ID del producto que desea buscar:");
        String idBuscado = input.next();
        Producto productoBuscado = productoController.findOne(idBuscado);
        if (productoBuscado != null) {
            mostrarInformacionProducto(productoBuscado);
        } else {
            System.out.println("El ID proporcionado no corresponde a ningún producto.");
        }
    }

    public void buscarTodosLosProductos() {
        System.out.println("\n");
        for (Producto producto : productoController.findAll()) {
            mostrarInformacionProducto(producto);
        }
        System.out.println("\n");
    }

    private void setCategoria(Producto prod) {
        System.out.println("""
                Seleccione la categoría del producto:
                1. .Electrónica.
                2. .Hogar.
                3. .Oficina.
                4. .Jardín.
                5. .Gaming.
                """);
        int opc = input.nextInt();
        productoController.setCategoria(prod, opc);
    }

    private boolean existeProducto(String id) {
        return productoController.findOne(id) != null;
    }

    private void mostrarInformacionProducto(Producto producto) {
        String estado = producto.getHabilitado() ? "Habilitado" : "Deshabilitado";
        //utilicé el operador ternario (?:) para mostrar el estado del producto de manera más concisa.
        System.out.println("Producto: " + producto.getNombre() +
                ", ID: " + producto.getId() +
                ", Nombre: " + producto.getNombre() +
                ", Descripcion: " + producto.getDescripcion() +
                ", Ancho: " + producto.getAncho() +
                ", Alto: " + producto.getAlto() +
                ", Profundidad: " + producto.getProfundidad() +
                ", Peso: " + producto.getPeso() +
                ", Categoria: " + producto.getCategoria().getDescripcion() +
                ", Proveedor: " + producto.getProveedor().getNombre() +
                ", Estado: " + estado);
    }

    public int getOption() {
        return option;
    }
}


