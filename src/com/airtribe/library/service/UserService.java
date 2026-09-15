package com.airtribe.library.service;

import com.airtribe.library.dao.Datastore;
import com.airtribe.library.dao.LocalDatastore;
import com.airtribe.library.entity.User;
import com.airtribe.library.exception.DataNotFoundExcpetion;

import java.util.Optional;

public class UserService {
    private final Datastore<User> userDB;

    public UserService(){
        this.userDB = new LocalDatastore<>();
    }

    public User createUser(String name, String email){
        User u = new User(name, email);
        userDB.add(u.getId(), u);
        return u;
    }

    public User getUserById(String id){
        Optional<User> user = userDB.getItemById(id);
        if(user.isPresent())
            return user.get();
        throw new DataNotFoundExcpetion("User not found!!");
    }
}
