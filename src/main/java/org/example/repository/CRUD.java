package org.example.repository;

import java.util.List;

public interface CRUD<T> {
    T findOne(String id);

    List<T> findAll();

    void save(T t);

    void delete(String id);

    T update(T t);

    void upload();


}

