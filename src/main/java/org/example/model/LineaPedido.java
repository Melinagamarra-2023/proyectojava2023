package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LineaPedido {
    private String codigo;
    private Float cantidad;
    private Producto producto;


}
