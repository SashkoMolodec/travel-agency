package com.kravchenko.agency.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> findById(long id);

    List<T> getAll();

    void save(T t);

    void update(T t);

}
