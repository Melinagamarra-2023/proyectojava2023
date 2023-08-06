package org.example.repository;

import org.example.model.LineaPedido;
import org.example.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class LineaPedidoRepository implements CRUD<LineaPedido> {

    private final List<LineaPedido> lineaPedidos;

    public LineaPedidoRepository() {
        this.lineaPedidos = new ArrayList<>();
    }

    @Override
    public void save(LineaPedido lineaPedido) {;
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
        findOne(lineaPedido.getCodigo()).setCantidad(lineaPedido.getCantidad());
        return lineaPedido;
    }

    @Override
    public void delete(String id) {
        findOne(id).setHabilitado(false);
    }

    @Override
    public void upload() {
        lineaPedidos.add(new LineaPedido(null, null, null, null, true, false,0));
    }

    public void entregado(String id) {
        findOne(id).setPedidoEntregado(true);
    }

    public void calificarProveedor(String id, int star) {
        this.findOne(id).setCalificaProveedor(star);
    }

}
