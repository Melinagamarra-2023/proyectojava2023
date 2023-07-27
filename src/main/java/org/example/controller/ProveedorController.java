package org.example.controller;

import org.example.model.Categoria;
import org.example.model.Producto;
import org.example.model.Proveedor;
import org.example.service.ProductoService;
import org.example.service.ProveedorService;

import java.util.List;

public class ProveedorController implements CRUD<Producto> {
    private final ProductoService productoService;
    private final ProveedorService proveedorService;

    public ProveedorController() {
        this.productoService = new ProductoService();
        this.proveedorService = new ProveedorService();
    }

    @Override
    public void create(Producto nuevoProducto) {
        productoService.create(nuevoProducto);
    }

    @Override
    public List<Producto> findAll() {
        return productoService.findAll();
    }

    @Override
    public Producto findOne(String id) {
        return productoService.findOne(id);
    }

    @Override
    public Producto update(Producto prod) {
        return productoService.update(prod);
    }

    @Override
    public void delete(String id) {
        productoService.delete(id);
    }

    public void setCategoria(Producto pro, int opc) {
        productoService.setCategoria(pro, opc);
    }

    //Proveedores
    public void createProv(Proveedor nuevoProveedor) {
        proveedorService.create(nuevoProveedor);
    }

    public List<Proveedor> findAllProv() {
        return proveedorService.findAll();
    }

    public Proveedor findOneProv(String cuit) {
        return proveedorService.findOne(cuit);
    }

    public Proveedor updateProv(Proveedor pr) { //solucionar warning
        return proveedorService.update(pr);
    }

    public void deleteProv(String cuit) {
        proveedorService.delete(cuit);
    }

    public void setProveedor(Producto pro, String id) {
        productoService.setProveedor(pro, id);
    }

    public List<Categoria> verCategorias() {
        return productoService.verCategorias();
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return productoService.findByCategoria(categoria);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoService.findByNombre(nombre);
    }

}
