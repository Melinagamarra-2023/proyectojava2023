package org.example.controller;


import java.util.List;

public interface CRUD<R, P> {


    public void crear(P p);

    public List<R> buscarTodos();

    public R buscarPorID(String id);

    public R modificar(P p);

    public R eliminar(String id);

}
