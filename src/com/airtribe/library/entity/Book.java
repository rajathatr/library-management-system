package com.airtribe.library.entity;

import com.airtribe.library.utils.IDGenerator;
import com.airtribe.library.utils.Validator;

public class Book {
    private final String id;
    private String name;
    private String author;
    private int price;
    private boolean isRented;

    public Book(String name, String author, int price){
        this.name = Validator.nonBlank(name);
        this.author = Validator.nonBlank(author);
        this.price = price;
        this.isRented = false;
        this.id = IDGenerator.getNextBookID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId(){
        return this.id;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }
}
