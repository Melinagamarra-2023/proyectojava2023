package org.example.repository;

import org.example.model.Categoria;
import org.example.model.Producto;
import org.example.model.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class ProductoRepository implements CRUD<Producto> {
    private final List<Producto> productos;
    private final ProveedorRepository proveedorRepository;

    public ProductoRepository(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
        this.productos = new ArrayList<>();
        this.upload();
    }

    @Override
    public Producto findOne(String id) {
        for (Producto prod : productos) {
            if (prod.getId().equals(id)) {
                return prod;
            }
        }
        return null;
    }

    @Override
    public List<Producto> findAll() {
        return productos;
    }


    @Override

    public void create(Producto producto) {
        productos.add(producto);
    }

    @Override
    public void delete(String id) {
        for (Producto prod : productos) {
            if (prod.getId().equals(id) && prod.getHabilitado()) {
                prod.setHabilitado(false);
            }
        }
    }

    @Override
    public Producto update(Producto productoActualizado) {
        if (findOne(productoActualizado.getId()) != null) {
            findOne(productoActualizado.getId()).setNombre(productoActualizado.getNombre());
            findOne(productoActualizado.getId()).setAlto(productoActualizado.getAlto());
            findOne(productoActualizado.getId()).setAncho(productoActualizado.getAncho());
            findOne(productoActualizado.getId()).setProfundidad(productoActualizado.getProfundidad());
            findOne(productoActualizado.getId()).setPeso(productoActualizado.getPeso());
            findOne(productoActualizado.getId()).setCategoria(productoActualizado.getCategoria());
            findOne(productoActualizado.getId()).setProveedor(productoActualizado.getProveedor());
            findOne(productoActualizado.getId()).setDescripcion(productoActualizado.getDescripcion());
            findOne(productoActualizado.getId()).setHabilitado(productoActualizado.getHabilitado());
        }
        return null;
    }


    Categoria categoriaProductoA = new Categoria("Electrónica");
    Categoria categoriaProductoB = new Categoria("Hogar");
    Categoria categoriaProductoC = new Categoria("Oficina");
    Categoria categoriaProductoD = new Categoria("Jardín");
    Categoria categoriaProductoE = new Categoria("Gaming");


    public void setCategoria(Producto producto, int opc) {
        if (opc == 1) {
            producto.setCategoria(categoriaProductoA);
        } else if (opc == 2) {
            producto.setCategoria(categoriaProductoB);
        } else if (opc == 3) {
            producto.setCategoria(categoriaProductoC);
        } else if (opc == 4) {
            producto.setCategoria(categoriaProductoD);
        } else if (opc == 5) {
            producto.setCategoria(categoriaProductoE);
        }
    }

    public void setProveedor(Producto pro, Proveedor pr) {
        pro.setProveedor(pr);
    }

    @Override
    public void upload() {

        Producto producto1 = new Producto("0001", "Dell XPS", "potente portátil para trabajo y entretenimiento.", 40F, 7F, 30F, 2500F, categoriaProductoA, this.proveedorRepository.findOne("0001"), true);
        Producto producto2 = new Producto("0002", "Lenovo Yoga", "versátil portátil con pantalla táctil.", 40F, 7F, 30F, 2500F, categoriaProductoB, this.proveedorRepository.findOne("0002"), true);
        Producto producto3 = new Producto("0003", "HP Pavilion", "portátil elegante y potente.", 40F, 7F, 30F, 2500F, categoriaProductoC, this.proveedorRepository.findOne("0003"), true);
        Producto producto4 = new Producto("0004", "Acer Predator", "portátil gaming de alto rendimiento.", 35F, 6F, 28F, 2200F, categoriaProductoE, this.proveedorRepository.findOne("0004"), true);
        Producto producto5 = new Producto("0005", "Asus ZenBook", "portátil ultradelgado y ligero.", 32F, 5F, 26F, 1800F, categoriaProductoD, this.proveedorRepository.findOne("0005"), true);
        Producto producto6 = new Producto("0006", "Dell Inspiron", "portátil asequible para uso diario.", 38F, 7F, 30F, 2500F, categoriaProductoD, this.proveedorRepository.findOne("0006"), true);
        Producto producto7 = new Producto("0007", "HP Envy", "portátil elegante y potente.", 36F, 6F, 29F, 2300F, categoriaProductoD, this.proveedorRepository.findOne("0007"), true);
        Producto producto8 = new Producto("0008", "Lenovo ThinkPad", "portátil empresarial confiable.", 33F, 5F, 27F, 1900F, categoriaProductoD, this.proveedorRepository.findOne("0008"), true);
        Producto producto9 = new Producto("0009", "Acer Swift", "portátil ultradelgado y ligero.", 39F, 7F, 31F, 2600F, categoriaProductoD, this.proveedorRepository.findOne("0009"), true);


        productos.add(producto1);
        productos.add(producto2);
        productos.add(producto3);
        productos.add(producto4);
        productos.add(producto5);
        productos.add(producto6);
        productos.add(producto7);
        productos.add(producto8);
        productos.add(producto9);

    }
}

