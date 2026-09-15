package com.airtribe.library.entity;

import com.airtribe.library.utils.IDGenerator;
import com.airtribe.library.utils.Validator;

public class User {
    private final String id;
    private String name;
    private String email;

    public User(String name, String email){
        this.name = Validator.nonBlank(name);
        this.email = Validator.nonBlank(email);
        this.id = IDGenerator.getNextUserID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId(){
        return this.id;
    }
}
