package org.example.repository;

import java.util.List;

public interface CRUD<T> {

    public T findOne(String id);

    public List<T> findAll();

    public void create(T t);

    public void delete(String id);

    public T update(T t);

    public void upload();


}

