package org.example.repository;

import org.example.model.Categoria;
import org.example.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoRepository implements CRUD<Producto> {
    private final List<Producto> productos;
    private final List<Categoria> categorias;
    private final ProveedorRepository proveedorRepository;

    public ProductoRepository() {
        this.proveedorRepository = new ProveedorRepository();
        this.productos = new ArrayList<>();
        this.categorias = new ArrayList<>();
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

    public void save(Producto producto) {
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
        return productoActualizado;
    }

    public void setCategoria(Producto producto, int opc) {
        switch (opc) {
            case 1 -> producto.setCategoria(categorias.get(1));
            case 2 -> producto.setCategoria(categorias.get(2));
            case 3 -> producto.setCategoria(categorias.get(3));
            case 4 -> producto.setCategoria(categorias.get(4));
            case 5 -> producto.setCategoria(categorias.get(5));
        }
    }

    public List<Categoria> verCategorias() {
        return categorias;
    }

    public List<Producto> findByCategoria(String categoria) {
        List<Producto> productosEncontrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getCategoria().getDescripcion().equals(categoria) && producto.getHabilitado()) {
                productosEncontrados.add(producto);
            }
        }
        return productosEncontrados;
    }

    public List<Producto> findByNombre(String nombre) {
        List<Producto> productoEncontrado = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getNombre().startsWith(nombre) && producto.getHabilitado()) {
                productoEncontrado.add(producto);
            }
        }
        return productoEncontrado;
    }

    public void setProveedor(Producto pro, String id) {
        pro.setProveedor(proveedorRepository.findOne(id));
    }

    @Override
    public void upload() {

        Categoria categoriaElectronica = new Categoria("Electrónica");
        Categoria categoriaHogar = new Categoria("Hogar");
        Categoria categoriaOficina = new Categoria("Oficina");
        Categoria categoriaJardin = new Categoria("Jardín");
        Categoria categoriaGaming = new Categoria("Gaming");

        Producto producto1 = new Producto("0001", "Dell XPS", "potente portátil para trabajo y entretenimiento.", 40., 7., 30., 2500., categoriaElectronica, this.proveedorRepository.findOne("0001"), true);
        Producto producto2 = new Producto("0002", "Lenovo Yoga", "versátil portátil con pantalla táctil.", 40., 7., 30., 2500., categoriaGaming, this.proveedorRepository.findOne("0002"), true);
        Producto producto3 = new Producto("0003", "HP Pavilion", "portátil elegante y potente.", 40., 7., 30., 2500., categoriaOficina, this.proveedorRepository.findOne("0003"), true);
        Producto producto4 = new Producto("0004", "Acer Predator", "portátil gaming de alto rendimiento.", 35., 6., 28., 2200., categoriaElectronica, this.proveedorRepository.findOne("0004"), true);
        Producto producto5 = new Producto("0005", "Asus ZenBook", "portátil ultradelgado y ligero.", 32., 5., 26., 1800., categoriaElectronica, this.proveedorRepository.findOne("0005"), true);
        Producto producto6 = new Producto("0006", "Dell Inspiron", "portátil asequible para uso diario.", 38., 70., 30., 2500., categoriaOficina, this.proveedorRepository.findOne("0006"), true);
        Producto producto7 = new Producto("0007", "HP Envy", "portátil elegante y potente.", 36., 6., 29., 2300., categoriaOficina, this.proveedorRepository.findOne("0007"), true);
        Producto producto8 = new Producto("0008", "Lenovo ThinkPad", "portátil empresarial confiable.", 33., 5., 27., 1900., categoriaGaming, this.proveedorRepository.findOne("0008"), true);
        Producto producto9 = new Producto("0009", "Acer Swift", "portátil ultradelgado y ligero.", 39., 7., 31., 2600., categoriaGaming, this.proveedorRepository.findOne("0009"), true);

        categorias.add(categoriaElectronica);
        categorias.add(categoriaHogar);
        categorias.add(categoriaOficina);
        categorias.add(categoriaJardin);
        categorias.add(categoriaGaming);

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

