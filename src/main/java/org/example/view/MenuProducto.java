package org.example.view;

import org.example.controller.ProductoController;
import org.example.model.Producto;


import java.util.List;
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
                5. Buscar productos por categoría.
                6. Buscar productos por nombre.
                7. Obtener lista de todos los productos.
                0. Salir.
                """);
        option = input.nextInt();
        return option;
    }

    public void crearProducto() {
        System.out.println("\nProporcione los datos para el nuevo producto: ");
        System.out.print("Id: ");
        String id = input.next();
        while (this.existeProducto(id)) {
            System.out.println("Este ID ya existe, intentelo de nuevo. (0 para cancelar)");
            System.out.print("ID: ");
            id = input.next();
            if (id.equals("0")) {
                System.out.println("Operación cancelada.");
                return;
            } else {
                this.existeProducto(id);
            }
        }
        Producto nuevoProducto = new Producto(id, null, null, null, null, null, null, null, null, true);
        this.modificarDatosProducto(nuevoProducto);
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
            System.out.println("Este ID no existe, intentelo de nuevo. (0 para cancelar)");
            idModificar = input.next();
            if (idModificar.equals("0")) {
                System.out.println("Operación cancelada.");
                return;
            } else {
                productoModificar = productoController.findOne(idModificar);
            }
        }
        this.modificarDatosProducto(productoModificar);
        System.out.println("Producto modificado con éxito.");
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
            this.mostrarInformacionProducto(productoBuscado);
        } else {
            System.out.println("El ID proporcionado no corresponde a ningún producto.");
        }
    }

    public void buscarTodosLosProductos() {
        System.out.println("\n");
        for (Producto producto : productoController.findAll()) {
            this.mostrarInformacionProducto(producto);
        }
        System.out.println("\n");
    }

    public void buscarPorCategoria() {
        this.seleccionarCategoria();
    }

    public void buscarPorNombre() {
        this.buscarProductoPorNombre();
    }

    //private
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
        while (opc < 1 || opc > 5) {
            System.out.println("Ingrese una opción válida.");
            opc = input.nextInt();
        }
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


    private void modificarDatosProducto(Producto producto) {
        input.nextLine();
        System.out.print("Nombre: ");
        String nombreModificar = input.nextLine();
        System.out.print("Descripción: ");
        String descripcionModificar = input.nextLine();
        System.out.print("Ancho: ");
        Double anchoModificar = Double.valueOf(input.nextLine());
        System.out.print("Alto: ");
        Double altoModificar = Double.valueOf(input.nextLine());
        System.out.print("Profundidad: ");
        Double profundidadModificar = Double.valueOf(input.nextLine());
        System.out.println("Peso: ");
        Double pesoModificar = Double.valueOf(input.nextLine());
        producto.setNombre(nombreModificar);
        producto.setDescripcion(descripcionModificar);
        producto.setAncho(anchoModificar);
        producto.setAlto(altoModificar);
        producto.setProfundidad(profundidadModificar);
        producto.setPeso(pesoModificar);
    }


    private void seleccionarCategoria() {
        System.out.println("\nSeleccione la categoría del producto que desea buscar:");
        System.out.println("1. Electrónica");
        System.out.println("2. Hogar");
        System.out.println("3. Oficina");
        System.out.println("4. Jardín");
        System.out.println("5. Gaming");
        int opcionCategoria = input.nextInt();
        String categoriaBuscada = "";
        switch (opcionCategoria) {
            case 1 -> categoriaBuscada = "Electrónica";
            case 2 -> categoriaBuscada = "Hogar";
            case 3 -> categoriaBuscada = "Oficina";
            case 4 -> categoriaBuscada = "Jardín";
            case 5 -> categoriaBuscada = "Gaming";
            default -> System.out.println("Opción inválida.");
        }
        List<Producto> productosEncontrados = productoController.buscarPorCategoria(categoriaBuscada);
        if (!(productosEncontrados.isEmpty())) {
            System.out.println("\nProductos encontrados:");
            for (Producto producto : productosEncontrados) {
                mostrarInformacionProducto(producto);
            }
        } else {
            System.out.println("No se encontraron productos en la categoría proporcionada.");
        }
    }

    private void buscarProductoPorNombre() {
        System.out.println("\nIngrese el nombre del producto que desea buscar:");
        String nombreBuscado = input.next();
        List<Producto> productosEncontrados = productoController.buscarPorNombre(nombreBuscado);
        if (!(productosEncontrados.isEmpty())) {
            System.out.println("\nProductos encontrados:");
            for (Producto producto : productosEncontrados) {
                mostrarInformacionProducto(producto);
            }
        } else {
            System.out.println("No se encontraron productos con el nombre proporcionado.");
        }
    }

    public int getOption() {
        return option;
    }
}


