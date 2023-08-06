package org.example.view;

import org.example.controller.ProveedorController;
import org.example.model.Producto;


import java.util.List;
import java.util.Scanner;

public class MenuProducto {

    private final MenuPrincipal menuPrincipal;
    private final ProveedorController proveedorController;
    private final Scanner input;

    public MenuProducto() {
        this.menuPrincipal = new MenuPrincipal();
        this.proveedorController = new ProveedorController();
        this.input = new Scanner(System.in);
    }

    public int seleccionarOpcion() {
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
        return input.nextInt();
    }

    public void crearProducto() {
        generarProducto();
    }

    public void modificarProducto() {
        modificateProducto();
    }

    public void eliminarProducto() {
        deleteProducto();
    }

    public void buscarProductoPorId() {
        buscarUno();
    }

    public void buscarTodosLosProductos() {
        buscarTodos();
    }

    public void buscarPorCategoria() {
        seleccionarCategoria();
    }

    public void buscarPorNombre() {
        buscarProductoPorNombre();
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
        proveedorController.setCategoria(prod, opc);
    }

    private void generarProducto() {
        System.out.println("Proporcione los datos para el nuevo producto: ");
        System.out.print("Id: ");
        String id = input.next();
        while (proveedorController.findOne(id) == null && !(id.equals("0"))) {
            id = menuPrincipal.verificarExistencia("id");
        }
        if (id.equals("0")) {
            return;
        }
        Producto nuevoProducto = new Producto(id, null, null, null, null, null, null, null, null, true);
        this.modificarDatosProducto(nuevoProducto);
        System.out.println("Proporcione el cuit del proveedor del producto: ");
        String cuitProveedor = input.next();
        if (cuitProveedor != null) {
            proveedorController.setProveedor(nuevoProducto, cuitProveedor);
            this.setCategoria(nuevoProducto);
            proveedorController.create(nuevoProducto);
            System.out.println("Producto: " + nuevoProducto.getNombre() + " Creado con éxito");
        } else {
            System.out.println("Ingrese un cuit que exista.");
        }
    }

    private void mostrarInformacionProducto(Producto producto) {
        System.out.print("Producto: " + producto.getNombre() +
                ", ID: " + producto.getId() +
                ", Nombre: " + producto.getNombre() +
                ", Descripcion: " + producto.getDescripcion() +
                ", Ancho: " + producto.getAncho() +
                ", Alto: " + producto.getAlto() +
                ", Profundidad: " + producto.getProfundidad() +
                ", Peso: " + producto.getPeso() +
                ", Categoria: " + producto.getCategoria().getDescripcion() +
                ", Proveedor: " + producto.getProveedor().getNombre());
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

    private void modificateProducto() {
        System.out.println("Ingrese el ID del producto a modificar:");
        String idModificar = input.next();
        while (proveedorController.findOne(idModificar) == null && !(idModificar.equals("0"))) {
            idModificar = menuPrincipal.verificarExistencia("id");
        }
        if (idModificar.equals("0")) {
            return;
        }
        Producto productoModificar = proveedorController.findOne(idModificar);
        this.modificarDatosProducto(productoModificar);
        System.out.println("Producto " + productoModificar.getNombre() + "modificado con éxito.");
    }

    private void deleteProducto() {
        System.out.println("Ingrese el ID del producto a eliminar:");
        String idEliminar = input.next();
        while (proveedorController.findOne(idEliminar) == null && !(idEliminar.equals("0"))) {
            idEliminar = menuPrincipal.verificarExistencia("id");
        }
        if (idEliminar.equals("0")) {
            return;
        }
        Producto productoEliminar = proveedorController.findOne(idEliminar);
        proveedorController.delete(idEliminar);
        System.out.println("El producto " + productoEliminar.getNombre() + " ha sido eliminado con éxito.");
    }

    private void buscarUno() {
        System.out.println("\nIngrese el ID del producto que desea buscar:");
        String idBuscado = input.next();
        while (proveedorController.findOne(idBuscado) == null && !(idBuscado.equals("0"))) {
            idBuscado = menuPrincipal.verificarExistencia("id");
        }
        if (idBuscado.equals("0")) {
            return;
        }
        Producto productoBuscado = proveedorController.findOne(idBuscado);
        this.mostrarInformacionProducto(productoBuscado);
        String estado = (productoBuscado.getHabilitado() ? "Habilitado" : "Deshabilitado");
        System.out.print(", Estado: " + estado + ".");
    }

    private void buscarTodos() {
        for (Producto producto : proveedorController.findAll()) {
            this.mostrarInformacionProducto(producto);
            System.out.print(".\n");
        }
    }

    private void seleccionarCategoria() {
        System.out.println("Seleccione la categoría del producto que desea buscar:");
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
        List<Producto> productosEncontrados = proveedorController.buscarPorCategoria(categoriaBuscada);
        if (!(productosEncontrados.isEmpty())) {
            System.out.println("Productos encontrados:");
            for (Producto producto : productosEncontrados) {
                mostrarInformacionProducto(producto);
                System.out.print(".\n");
            }
        } else {
            System.out.println("No se encontraron productos en la categoría proporcionada.");
        }
    }

    private void buscarProductoPorNombre() {
        System.out.println("Ingrese el nombre del producto que desea buscar:");
        String nombreBuscado = input.next();
        List<Producto> productosEncontrados = proveedorController.buscarPorNombre(nombreBuscado);
        if (!(productosEncontrados.isEmpty())) {
            System.out.println("Productos encontrados:");
            for (Producto producto : productosEncontrados) {
                mostrarInformacionProducto(producto);
                System.out.print(".\n");
            }
        } else {
            System.out.println("No se encontraron productos con el nombre proporcionado.");
        }
    }

}


