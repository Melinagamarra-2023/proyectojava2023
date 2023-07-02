package org.example.service;

import org.example.model.Transportista;

import java.util.List;

public interface CRUD<T> {
    public void create(T t);

    public T findOne(String id);

    public List<T> findAll();

    public T update(T t);

    public Transportista delete(String id);

}
