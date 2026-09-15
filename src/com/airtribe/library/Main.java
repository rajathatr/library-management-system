package com.airtribe.library;

import com.airtribe.library.entity.Book;
import com.airtribe.library.entity.Rent;
import com.airtribe.library.entity.Patron;
import com.airtribe.library.service.BookService;
import com.airtribe.library.service.RentalService;
import com.airtribe.library.service.PatronService;
import com.airtribe.library.strategy.BookDependentCalculator;


public class Main {
    public static void main(String[] args){
        PatronService u = new PatronService();
        BookService b = new BookService();
        RentalService r = new RentalService(u, b, new BookDependentCalculator());

        Patron u1 = u.createPatron("Rajath", "rajath@gmail.com");
        Patron u2 = u.createPatron("Rohit", "rohith@gmail.com");

        Book b1 = b.createBook("Da Vinci","Dan Brown", "9780307474278", 2003, 1000);
        Book b2 = b.createBook("Two States", "Chetan", "9788129135533", 2009, 350);
        Book b3 = b.createBook("The Intelligent Investor", "Benjamin Graham", "9780062312686", 1949, 2000);

        Rent r1 = r.loanBook(u1.getId(), b2.getId());
        System.out.println(r.returnBook(r1.getId(), "2026-09-20 18:30"));

        Rent r2 = r.loanBook(u2.getId(), b2.getId());
        System.out.println(r.returnBook(r2.getId(), "2026-09-30 11:50"));
        System.out.println(r.getUserHistory(u1.getId()));
        System.out.println("Available books: " + b.getAvailableBooks().size());
        System.out.println("Search result: " + b.searchBooks("Graham").size());
    }
}
