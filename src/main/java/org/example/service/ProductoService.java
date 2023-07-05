package org.example.service;

import org.example.model.Producto;
import org.example.model.Proveedor;
import org.example.repository.ProductoRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductoService {
    ProductoRepository productoRepository = new ProductoRepository();

    public void create(Producto nuevoProducto) {
        Producto productoExiste = productoRepository.findOne(nuevoProducto.getId());
        if (productoExiste == null) {
            productoRepository.create(nuevoProducto);
        }
    }

    public void update(Producto prod) {
        Producto productoAnterior = productoRepository.findOne(prod.getId());
        if (productoAnterior != null) {
            productoRepository.update(prod);
            productoRepository.findOne(prod.getId());
        }
    }

    public void delete(String id) {
        if (productoRepository.findOne(id) != null) {
            productoRepository.delete(id);
        }
    }

    public Producto findOne(String id) {
        for (Producto prod : productoRepository.findAll()) {
            if (prod.getId().equals(id)) {
                return prod;
            }
        }
        return null;
    }

    public List<Producto> findAll() {
        List<Producto> resultado = new ArrayList<>();
        for (Producto pr : this.productoRepository.findAll()) {
            if (pr.getHabilitado()) {
                resultado.add(pr);
            }
        }
        return resultado;
    }

    public void setCategoria(Producto pro, int opc) {
        productoRepository.setCategoria(pro, opc);
    }

    public void setProveedor(Producto pro, Proveedor pr) {
        productoRepository.setProveedor(pro, pr);
    }

}
