package com.bookish.bs.services;

import com.bookish.bs.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IService<T, R, ID> {
    T save(R object);
    T update(ID id, R object) throws NotFoundException;
    boolean delete(ID id) throws NotFoundException;
    T findById(ID id) throws NotFoundException;
    Page<T> findAll(Pageable pageable);
}
