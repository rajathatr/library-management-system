package com.airtribe.library.entity;

import com.airtribe.library.utils.IDGenerator;
import com.airtribe.library.utils.Validator;

/** A registered library member. */
public class Patron {
    private final String id;
    private String name;
    private String email;

    public Patron(String name, String email) {
        this.id = IDGenerator.getNextPatronID();
        this.name = Validator.nonBlank(name);
        this.email = Validator.email(email);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void updateDetails(String name, String email) {
        this.name = Validator.nonBlank(name);
        this.email = Validator.email(email);
    }
}
