package com.bookish.bs.services;

import java.util.List;

public interface IService<T, R, ID> {
    T save(R object);
    T update(ID id, R object);
    T findById(ID id);
    List<T> findAll();
}
