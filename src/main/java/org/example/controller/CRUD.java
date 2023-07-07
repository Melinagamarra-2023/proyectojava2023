package org.example.controller;

import java.util.List;

public interface CRUD<P> {


    void create(P p);

    List<P> findAll();

    P findOne(String id);

    P update(P p);

    void delete(String id);

}
