package org.example.repository;

import org.example.model.Categoria;
import org.example.model.Producto;

import java.util.ArrayList;

import java.util.List;

public class ProductoRepository implements CRUD<Producto> {
    private List<Producto> productos;
    ProveedorRepository proveedorRepository;

    public ProductoRepository(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
        this.productos = new ArrayList<>();
        this.upload();
    }

    @Override
    public Producto findOne(String id) {
        return null;
    }

    @Override
    public List<Producto> findAll() {
        return null;
    }

    @Override
    public void save(Producto producto) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Producto update(Producto producto) {
        return null;
    }

    @Override
    public void upload() {

        Categoria categoriaProductoA = new Categoria("Electrónica");
        Categoria categoriaProductoB = new Categoria("Hogar");
        Categoria categoriaProductoC = new Categoria("Oficina");
        Categoria categoriaProductoD = new Categoria("Jardín");
        Categoria categoriaProductoE = new Categoria("Gaming");

        Producto producto1 = new Producto("0001", "Dell XPS", "potente portátil para trabajo y entretenimiento.", 40F, 7F, 30F, 2500F, categoriaProductoA, this.proveedorRepository.findOne("43263547457"));
        Producto producto2 = new Producto("0002", "Lenovo Yoga", "versátil portátil con pantalla táctil.", 40F, 7F, 30F, 2500F, categoriaProductoB, this.proveedorRepository.findOne("64458407589"));
        Producto producto3 = new Producto("0003", "HP Pavilion", "portátil elegante y potente.", 40F, 7F, 30F, 2500F, categoriaProductoC, this.proveedorRepository.findOne("64458407589"));
        Producto producto4 = new Producto("0004", "Acer Predator", "portátil gaming de alto rendimiento.", 35F, 6F, 28F, 2200F, categoriaProductoE, this.proveedorRepository.findOne("12458407577"));
        Producto producto5 = new Producto("0005", "Asus ZenBook", "portátil ultradelgado y ligero.", 32F, 5F, 26F, 1800F, categoriaProductoD, this.proveedorRepository.findOne("12458407577"));
        Producto producto6 = new Producto("0006", "Dell Inspiron", "portátil asequible para uso diario.", 38F, 7F, 30F, 2500F, categoriaProductoD, this.proveedorRepository.findOne("12458407577"));
        Producto producto7 = new Producto("0007", "HP Envy", "portátil elegante y potente.", 36F, 6F, 29F, 2300F, categoriaProductoD, this.proveedorRepository.findOne("12458407577"));
        Producto producto8 = new Producto("0008", "Lenovo ThinkPad", "portátil empresarial confiable.", 33F, 5F, 27F, 1900F, categoriaProductoD, this.proveedorRepository.findOne("12458407577"));
        Producto producto9 = new Producto("0009", "Acer Swift", "portátil ultradelgado y ligero.", 39F, 7F, 31F, 2600F, categoriaProductoD, this.proveedorRepository.findOne("12458407577"));
        Producto producto10 = new Producto("0010", "Asus VivoBook", "portátil versátil y asequible.", 37F, 6F, 30F, 2400F, categoriaProductoD, this.proveedorRepository.findOne("12458407577"));
        Producto producto11 = new Producto("0011", "Dell Latitude", "portátil empresarial confiable.", 34F, 5F, 28F, 2000F, categoriaProductoD, this.proveedorRepository.findOne("12458407577"));
        Producto producto12 = new Producto("0012", "HP Spectre", "portátil premium y ultradelgado.", 40F, 7F, 32F, 2700F, categoriaProductoD, this.proveedorRepository.findOne("12458407577"));
        Producto producto13 = new Producto("0013", "Lenovo Legion", "portátil gaming de alto rendimiento.", 38F, 6F, 31F, 2500F, categoriaProductoE, this.proveedorRepository.findOne("12458407577"));
        Producto producto14 = new Producto("0014", "Acer Aspire", "portátil asequible para uso diario.", 35F, 5F, 29F, 2100F, categoriaProductoD, this.proveedorRepository.findOne("64458407589"));
        Producto producto15 = new Producto("0015", "Asus ROG", "portátil gaming de alto rendimiento.", 41F, 7F, 33F, 2800F, categoriaProductoE, this.proveedorRepository.findOne("12458407577"));
        Producto producto16 = new Producto("0016", "Dell Vostro", "portátil empresarial confiable.", 39F, 6F, 32F, 2600F, categoriaProductoD, this.proveedorRepository.findOne("12458407577"));
        Producto producto17 = new Producto("0017", "HP ProBook", "portátil empresarial confiable.", 36F, 5F, 30F, 2200F, categoriaProductoD, this.proveedorRepository.findOne("64458407589"));
        Producto producto18 = new Producto("0018", "Samsung Galaxy S21", "smartphone de gama alta.", 6.2F, 2.8F, 0.3F, 169F, categoriaProductoA, this.proveedorRepository.findOne("64458407589"));
        Producto producto19 = new Producto("0019", "HP i5", "notebook de escritorio.", 40F, 6F, 33F, 2700F, categoriaProductoD, this.proveedorRepository.findOne("64458407589"));
        Producto producto20 = new Producto("0020", "Lenovo i3", "notebook de escritorio.", 37F, 5F, 31F, 2300F, categoriaProductoD, this.proveedorRepository.findOne("64458407589"));

        productos.add(producto1);
        productos.add(producto2);
        productos.add(producto3);
        productos.add(producto4);
        productos.add(producto5);
        productos.add(producto6);
        productos.add(producto7);
        productos.add(producto8);
        productos.add(producto9);
        productos.add(producto10);
        productos.add(producto11);
        productos.add(producto12);
        productos.add(producto13);
        productos.add(producto14);
        productos.add(producto15);
        productos.add(producto16);
        productos.add(producto17);
        productos.add(producto18);
        productos.add(producto19);
        productos.add(producto20);

    }

}

