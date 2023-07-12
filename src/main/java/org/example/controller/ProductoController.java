package org.example.controller;

import org.example.model.Producto;
import org.example.model.Proveedor;
import org.example.service.ProductoService;
import org.example.service.ProveedorService;

import java.util.List;

public class ProductoController implements CRUD<Producto> {
    private final ProductoService productoService;
    private final ProveedorService proveedorService;

    public ProductoController() {
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
    public void createPr(Proveedor nuevoProveedor) {
        proveedorService.create(nuevoProveedor);
    }

    public List<Proveedor> findAllPr() {
        return proveedorService.findAll();
    }

    public Proveedor findOnePr(String cuit) {
        return proveedorService.findOne(cuit);
    }

    public Proveedor updatePr(Proveedor pr) { //solucionar warning
        return proveedorService.update(pr);
    }

    public void deletePr(String cuit) {
        proveedorService.delete(cuit);
    }

    public void setProveedor(Producto pro, String id) {
        productoService.setProveedor(pro, id);
    }

}
