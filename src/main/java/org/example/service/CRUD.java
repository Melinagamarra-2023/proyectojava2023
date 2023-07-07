package org.example.service;


import java.util.List;

public interface CRUD<T> {
    void create(T t);

    T findOne(String id);

    List<T> findAll();

    T update(T t);

    void delete(String id);

}
