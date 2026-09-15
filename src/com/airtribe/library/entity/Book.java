package com.airtribe.library.entity;

import com.airtribe.library.utils.IDGenerator;
import com.airtribe.library.utils.Validator;

public class Book {
    private final String id;
    private String title;
    private String author;
    private int price;
    private final String isbn;
    private int publicationYear;
    private boolean isRented;

    public Book(String title, String author, String isbn, int publicationYear, int price){
        this.title = Validator.nonBlank(title);
        this.author = Validator.nonBlank(author);
        this.isbn = Validator.nonBlank(isbn);
        this.publicationYear = Validator.positive(publicationYear, "Publication year");
        this.price = Validator.positive(price, "Price");
        this.isRented = false;
        this.id = IDGenerator.getNextBookID();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = Validator.nonBlank(title);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = Validator.nonBlank(author);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = Validator.positive(price, "Price");
    }

    public String getId(){
        return this.id;
    }

    public String getIsbn() { return isbn; }
    public int getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = Validator.positive(publicationYear, "Publication year");
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }
}
