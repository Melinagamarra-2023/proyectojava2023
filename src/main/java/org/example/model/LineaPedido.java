package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LineaPedido {
    private int codigo;
    private Integer cantidad;
    private Producto producto;
}
