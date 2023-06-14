package com.doderasoft.onlinebooking.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CrudOperations<T> {

    int create(T entity);

    Optional<T> read(UUID id);

    List<T> readAll();

    int update(UUID id, T entity);

    int delete(UUID id);


}
