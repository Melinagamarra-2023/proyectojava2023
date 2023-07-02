package org.example.view;

import org.example.controller.ProductoController;
import org.example.controller.ProveedorController;
import org.example.model.Producto;
import org.example.model.Proveedor;
import org.example.repository.ProductoRepository;
import org.example.repository.ProveedorRepository;
import org.example.service.ProductoService;
import org.example.service.ProveedorService;


import java.util.Scanner;

public class MenuProducto {
    ProductoRepository productoRepository = new ProductoRepository(new ProveedorRepository());
    ProductoService productoService = new ProductoService(productoRepository);
    ProductoController productoController = new ProductoController(productoService);
    ProveedorRepository proveedorRepository = new ProveedorRepository();
    ProveedorService proveedorService = new ProveedorService(proveedorRepository);
    ProveedorController proveedorController = new ProveedorController(proveedorService);


    Scanner input = new Scanner(System.in);
    static int option = 99;

    public int seleccionarOpcion() {
        System.out.println("""
                                
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
        Producto productoExiste = productoController.findOne(id);
        while (productoExiste != null) {
            System.out.println("Ya existe un producto con el mismo ID" +
                    " Ingrese un ID diferente");
            System.out.print("ID: ");
            id = input.next();
            productoExiste = productoController.findOne(id);
        }
        System.out.print("Nombre: ");
        String nombre = input.next();
        System.out.print("Descripcion: ");
        String descripcion = input.next();
        System.out.print("Ancho: ");
        Float ancho = input.nextFloat();
        System.out.print("Alto: ");
        Float alto = input.nextFloat();
        System.out.print("Profundidad: ");
        Float profundidad = input.nextFloat();
        System.out.print("Peso: ");
        Float peso = input.nextFloat();
        Producto nuevoProducto = new Producto(nombre, descripcion, id, ancho, alto, profundidad, peso, null, null, true);
        System.out.println("Proporcione el cuit del proveedor del producto: ");
        String cuit = input.next();
        Proveedor pr = proveedorController.findOne(cuit);
        productoController.setProveedor(nuevoProducto, pr);
        this.setCategoria(nuevoProducto);
        productoController.create(nuevoProducto);
        System.out.println("Producto: " + nuevoProducto.getNombre() + " Creado con éxito");

    }

    public void modificarProducto() {
        System.out.println("\nIngrese el ID del producto a modificar:");
        String idModificar = input.next();
        Producto productoModificar = productoController.findOne(idModificar);
        if (productoModificar != null) {
            System.out.println("Ingrese los nuevos datos del producto:");
            System.out.print("Nombre: ");
            String nombreModificar = input.next();
            System.out.print("Descripcion: ");
            String descripcionModificar = input.next();
            System.out.print("Ancho: ");
            Float anchoModificar = input.nextFloat();
            System.out.print("Alto: ");
            Float altoModificar = input.nextFloat();
            System.out.print("Profundidad: ");
            Float profundidadModificar = input.nextFloat();
            System.out.print("Peso: ");
            Float pesoModificar = input.nextFloat();

            productoModificar.setNombre(nombreModificar);
            productoModificar.setDescripcion(descripcionModificar);
            productoModificar.setAncho(anchoModificar);
            productoModificar.setAlto(altoModificar);
            productoModificar.setProfundidad(profundidadModificar);
            productoModificar.setPeso(pesoModificar);

            System.out.println("Proporcione el cuit del proveedor del producto: ");
            String cuit = input.next();
            Proveedor pr = proveedorController.findOne(cuit);
            productoController.setProveedor(productoModificar, pr);
            this.setCategoria(productoModificar);
            productoController.update(productoModificar);
            System.out.println("Producto: " + productoModificar.getId() + " Modificado con éxito.");
        } else {
            System.out.println("No se encontró ningún producto con el ID proporcionado.");
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
            System.out.print("El ID proporcionado corresponde al producto: " +
                    ", ID: " + productoBuscado.getId() +
                    ", Nombre: " + productoBuscado.getNombre() +
                    ", Descripcion: " + productoBuscado.getDescripcion() +
                    ", Ancho: " + productoBuscado.getAncho() +
                    ", Alto: " + productoBuscado.getAlto() +
                    ", Profundidad: " + productoBuscado.getProfundidad() +
                    ", Peso: " + productoBuscado.getPeso() +
                    ", Categoria: " + productoBuscado.getCategoria() +
                    ", Proveedor: " + productoBuscado.getProveedor());

            if (productoBuscado.getHabilitado()) {
                System.out.print("Habilitado\n");
            } else {
                System.out.print("Inhabilitado\n");
            }
        } else {
            System.out.println("El ID proporcionado no corresponde a ningún producto.");
        }
    }

    public void buscarTodosLosProductos() {
        System.out.println("\n");
        for (Producto prod : productoController.findAll()) {
            System.out.println("Producto: " + prod.getNombre() +
                    ", ID: " + prod.getId() +
                    ", Nombre: " + prod.getNombre() +
                    ", Descripcion: " + prod.getDescripcion() +
                    ", Ancho: " + prod.getAncho() +
                    ", Alto: " + prod.getAlto() +
                    ", Profundidad: " + prod.getProfundidad() +
                    ", Peso: " + prod.getPeso() +
                    ", Categoria: " + prod.getCategoria() +
                    ", Proveedor: " + prod.getProveedor());

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

    public int atras() {
        return option;
    }
}


