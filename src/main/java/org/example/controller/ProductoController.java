package org.example.controller;

import org.example.model.Producto;
import org.example.service.ProductoService;

import java.util.List;

public class ProductoController implements CRUD<Producto> {
    private final ProductoService productoService;

    public ProductoController() {
        this.productoService = new ProductoService();
    }

    public void create(Producto nuevoProducto) {
        productoService.create(nuevoProducto);
    }

    public List<Producto> findAll() {
        return productoService.findAll();
    }

    public Producto findOne(String id) {
        return productoService.findOne(id);
    }

    public Producto update(Producto prod) {
        return productoService.update(prod);
    }

    public void delete(String id) {
        productoService.delete(id);
    }

    public void setCategoria(Producto pro, int opc) {
        productoService.setCategoria(pro, opc);
    }

    public void setProveedor(Producto pro, String id) {
        productoService.setProveedor(pro, id);
    }

}
