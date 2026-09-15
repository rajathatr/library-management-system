package com.airtribe.library.service;

import com.airtribe.library.dao.Datastore;
import com.airtribe.library.dao.LocalDatastore;
import com.airtribe.library.entity.Book;
import com.airtribe.library.exception.DataNotFoundException;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class BookService {
    private final Datastore<Book> bookDB;

    public BookService(){
        this.bookDB = new LocalDatastore<>();
    }

    public Book createBook(String name, String author, String isbn, int publicationYear, int price){
        if (bookDB.getAllItems().stream().anyMatch(book -> book.getIsbn().equals(isbn))) {
            throw new IllegalArgumentException("A book with ISBN " + isbn + " already exists");
        }
        Book b = new Book(name, author, isbn, publicationYear, price);
        bookDB.add(b.getId(), b);
        return b;
    }

    public Book getBookById(String id){
        Optional<Book> book = bookDB.getItemById(id);
        if(book.isPresent())
            return book.get();
        throw new DataNotFoundException("Book not found: " + id);
    }

    public void updateBook(String id, String name, String author, int publicationYear, int price) {
        Book book = getBookById(id);
        book.setTitle(name);
        book.setAuthor(author);
        book.setPublicationYear(publicationYear);
        book.setPrice(price);
    }

    public void removeBook(String id) {
        Book book = getBookById(id);
        if (book.isRented()) throw new IllegalStateException("Return the book before removing it");
        bookDB.remove(id);
    }

    public List<Book> searchBooks(String query) {
        String term = query.toLowerCase(Locale.ROOT).trim();
        return bookDB.getAllItems().stream()
                .filter(book -> book.getTitle().toLowerCase(Locale.ROOT).contains(term)
                        || book.getAuthor().toLowerCase(Locale.ROOT).contains(term)
                        || book.getIsbn().toLowerCase(Locale.ROOT).contains(term))
                .toList();
    }

    public List<Book> getAvailableBooks() {
        return bookDB.getAllItems().stream().filter(book -> !book.isRented()).toList();
    }

    public List<Book> getBorrowedBooks() {
        return bookDB.getAllItems().stream().filter(Book::isRented).toList();
    }
}
