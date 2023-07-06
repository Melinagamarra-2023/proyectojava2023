package org.example.service;


import java.util.List;

public interface CRUD<T> {
    public void create(T t);

    public T findOne(String id);

    public List<T> findAll();

    public T update(T t);

    public void delete(String id);

}
