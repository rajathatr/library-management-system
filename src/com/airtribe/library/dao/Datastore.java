package com.airtribe.library.dao;

import java.util.List;
import java.util.Optional;

public interface Datastore<T> {
    void add(String id,T item);
    Optional<T> getItemById(String id);
    List<T> getAllItems();
    void remove(String id);
}
