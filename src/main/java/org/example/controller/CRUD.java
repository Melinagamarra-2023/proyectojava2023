package org.example.controller;


import java.util.List;

public interface CRUD<R, P> {


    public void create(P p);

    public List<R> findAll();

    public R findOne(String id);

    public R update(P p);

    public R delete(String id);

}
