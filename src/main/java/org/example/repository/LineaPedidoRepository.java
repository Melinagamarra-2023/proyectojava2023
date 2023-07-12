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

    @Override
    public void create(LineaPedido lineaPedido) {
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
            findOne(lineaPedido.getCodigo()).setCantidad(lineaPedido.getCantidad()); //cambiado
        //lineaPedido.setCantidad(lineaPedido.getCantidad());
        }
        return null;
    }

    @Override
    public void delete(String id) {
        findOne(id).setCodigo(null);
    }

    @Override
    public void upload() {
        LineaPedido prueba = new LineaPedido(null, null, null, 0, 0);
        lineaPedidos.add(prueba);
    }

    public void calificarProveedor(LineaPedido lineaPedido, int star) {
        lineaPedido.setCalificaProveedor(star);
    }

    public void calificarTransportista(LineaPedido lineaPedido, int star) {
        lineaPedido.setCalificaTransportista(star);
    }
}
