package com.airtribe.library;

import com.airtribe.library.dao.LocalDatastore;
import com.airtribe.library.entity.Book;
import com.airtribe.library.entity.Rent;
import com.airtribe.library.entity.User;
import com.airtribe.library.service.BookService;
import com.airtribe.library.service.RentalService;
import com.airtribe.library.service.UserService;
import com.airtribe.library.strategy.BookDependentCalculator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        UserService u = new UserService();
        BookService b = new BookService();
        RentalService r = new RentalService(u, b, new BookDependentCalculator());

        User u1 = u.createUser("Rajath", "rajath@gmail.com");
        User u2 = u.createUser("Rohit", "rohith@gmail.com");

        Book b1 = b.createBook("Da Vinci","Dan Brown", 1000);
        Book b2 = b.createBook("Two States", "Chetan", 350);
        Book b3 = b.createBook("Intelligent Investor", "Forgot his name", 2000);

        Rent r1 = r.loanBook(u1.getId(), b2.getId());
        System.out.println(r.returnBook(r1.getId(), "2026-09-20 18:30"));

        Rent r2 = r.loanBook(u2.getId(), b2.getId());
        System.out.println(r.returnBook(r2.getId(), "2026-09-30 11:50"));
        System.out.println(r.getUserHistory(u1.getId()));
    }
}