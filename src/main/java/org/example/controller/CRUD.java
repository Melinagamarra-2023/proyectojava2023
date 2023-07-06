package org.example.controller;


import org.example.model.Transportista;

import java.util.List;

public interface CRUD<P> {


    public void create(P p);

    public List<P> findAll();

    public P findOne(String id);

    public P update(P p);

    public void delete(String id);

}
