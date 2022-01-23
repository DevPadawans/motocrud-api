package com.devpadawans.motocrudapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface Crud<T, ID> {
    T save(T entity);
    void update(T entity);
    void delete(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    Page<T> paginate(Pageable pageable);
}
