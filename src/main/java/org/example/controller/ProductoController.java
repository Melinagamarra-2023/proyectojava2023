package org.example.controller;

import org.example.model.Producto;
import org.example.model.Proveedor;
import org.example.service.ProductoService;

import java.util.List;

public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
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

    public void update(Producto prod) {
        productoService.update(prod);
    }

    public void delete(String id) {
        productoService.delete(id);
    }

    public void setCategoria(Producto pro, int opc) {
        productoService.setCategoria(pro, opc);
    }

    public void setProveedor(Producto pro, Proveedor pr) {
        productoService.setProveedor(pro, pr);
    }

}
