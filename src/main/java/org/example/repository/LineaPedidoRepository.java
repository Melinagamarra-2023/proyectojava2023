package org.example.repository;

import org.example.model.LineaPedido;

import java.util.ArrayList;
import java.util.List;

public class LineaPedidoRepository implements CRUD<LineaPedido> {

    private final List<LineaPedido> lineaPedidos;
    private int codigo = 0;

    public LineaPedidoRepository() {
        this.lineaPedidos = new ArrayList<>();
    }

    public void create(LineaPedido lineaPedido) {
        codigo= codigo + 1;
        lineaPedido.setCodigo(String.valueOf(codigo));
        lineaPedidos.add(lineaPedido);
    }

    public LineaPedido findOne(String id) {
        for (LineaPedido lp : lineaPedidos) {
            if (lp.getCodigo().equals(id)) {
                return lp;
            }
        }
        return null;
    }

    public List<LineaPedido> findAll() {
        return lineaPedidos;
    }

    public LineaPedido update(LineaPedido lineaPedido) {
        lineaPedido.setCantidad(lineaPedido.getCantidad());
        return lineaPedido;
    }

    @Override
    public void upload() {

    }

    public void delete(String id) {
        findOne(id).setCodigo(null);
    }

    public void calificarProveedor(LineaPedido lineaPedido, int star) {
        lineaPedido.setCalificaProveedor(star);
    }

    public void calificarTransportista(LineaPedido lineaPedido, int star) {
        lineaPedido.setCalificaTransportista(star);
    }
}
