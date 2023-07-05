package org.example.service;

import org.example.model.LineaPedido;

import java.util.ArrayList;
import java.util.List;

public class LineaPedidoService {

    private final List<LineaPedido> LineaPedido;
    private int codigo = 0;

    public LineaPedidoService() {
        this.LineaPedido = new ArrayList<>();
    }

    public void create(LineaPedido lineaPedido) {
        codigo++;
        lineaPedido.setCodigo(codigo);
        LineaPedido.add(lineaPedido);
    }

    public LineaPedido findOne(int id) {
        for (LineaPedido lp : LineaPedido) {
            if (lp.getCodigo() == id) {
                return lp;
            }
        }
        return null;
    }

    public List<LineaPedido> findAll() {
        return LineaPedido;
    }

    public void update(LineaPedido lineaPedido) {
        lineaPedido.setProducto(lineaPedido.getProducto());
        lineaPedido.setCantidad(lineaPedido.getCantidad());
    }

    public void delete(LineaPedido lineaPedido) {
        lineaPedido.setCodigo(0);
        lineaPedido.setCantidad(0);
        lineaPedido.setProducto(null);
    }
}
