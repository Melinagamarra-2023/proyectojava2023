package org.example.repository;

import org.example.model.LineaPedido;
import org.example.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class LineaPedidoRepository implements CRUD<LineaPedido> {

    private final ProductoRepository productoRepository;
    private final List<LineaPedido> lineaPedidos;
    private int codigo = 0;

    public LineaPedidoRepository() {
        this.productoRepository = new ProductoRepository();
        this.lineaPedidos = new ArrayList<>();
        this.upload();
    }

    @Override
    public void save(LineaPedido lineaPedido) {
        codigo++;
        lineaPedido.setCodigo(String.valueOf(codigo));
        lineaPedidos.add(lineaPedido);
    }

    @Override
    public LineaPedido findOne(String id) {
        for (LineaPedido lp : lineaPedidos) {
            if (lp.getCodigo().equals(id)) {
                return lp;
            }
        }
        return null;
    }

    @Override
    public List<LineaPedido> findAll() {
        return lineaPedidos;
    }

    @Override
    public LineaPedido update(LineaPedido lineaPedido) {
        if (findOne(lineaPedido.getCodigo()) != null) {
            findOne(lineaPedido.getCodigo()).setCantidad(lineaPedido.getCantidad());
        }
        return null;
    }

    @Override
    public void delete(String id) {
        findOne(id).setHabilitado(false);
    }

    @Override
    public void upload() {
        for (Producto producto : productoRepository.findAll()) {
            codigo++;
            lineaPedidos.add(new LineaPedido(String.valueOf(codigo), 2, producto, true, true, (codigo > 5 ? codigo : codigo - 4)));
        }
    }

    public void agregado(LineaPedido lineaPedido) {
        lineaPedido.setPedido(false);
    }

    public void calificarProveedor(LineaPedido lineaPedido, int star) {
        lineaPedido.setCalificaProveedor(star);
    }

}
