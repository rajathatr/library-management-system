package com.airtribe.library.service;

import com.airtribe.library.dao.Datastore;
import com.airtribe.library.dao.LocalDatastore;
import com.airtribe.library.entity.Patron;
import com.airtribe.library.exception.DataNotFoundException;

public class PatronService {
    private final Datastore<Patron> patronDB = new LocalDatastore<>();

    public Patron createPatron(String name, String email) {
        Patron patron = new Patron(name, email);
        patronDB.add(patron.getId(), patron);
        return patron;
    }

    public Patron getPatronById(String id) {
        return patronDB.getItemById(id)
                .orElseThrow(() -> new DataNotFoundException("Patron not found: " + id));
    }

    public void updatePatron(String id, String name, String email) {
        getPatronById(id).updateDetails(name, email);
    }
}
