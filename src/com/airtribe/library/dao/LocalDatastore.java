package com.airtribe.library.dao;

import java.util.*;

public class LocalDatastore<T> implements Datastore<T>{
    protected Map<String,T> db = new HashMap<>();

    @Override
    public void add(String id, T item){
        if(db.containsKey(id))
            throw new IllegalArgumentException("Data already present for id: " + id);
        db.put(id, item);
    }

    public Optional<T> getItemById(String id){
        return Optional.ofNullable(db.get(id));
    }

    public List<T> getAllItems(){
        return List.copyOf(db.values());
    }

    @Override
    public void remove(String id) {
        db.remove(id);
    }
}
