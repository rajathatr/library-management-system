package com.airtribe.library.service;

import com.airtribe.library.dao.Datastore;
import com.airtribe.library.dao.LocalDatastore;
import com.airtribe.library.entity.Book;
import com.airtribe.library.exception.DataNotFoundExcpetion;

import java.util.Optional;

public class BookService {
    private final Datastore<Book> bookDB;

    public BookService(){
        this.bookDB = new LocalDatastore<>();
    }

    public Book createBook(String name, String author, int price){
        Book b = new Book(name, author,price);
        bookDB.add(b.getId(), b);
        return b;
    }

    public Book getBookById(String id){
        Optional<Book> book = bookDB.getItemById(id);
        if(book.isPresent())
            return book.get();
        throw new DataNotFoundExcpetion("Book not found!!");
    }
}
