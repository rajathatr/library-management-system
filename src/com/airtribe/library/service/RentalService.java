package com.airtribe.library.service;

import com.airtribe.library.dao.Datastore;
import com.airtribe.library.dao.LocalDatastore;
import com.airtribe.library.entity.Book;
import com.airtribe.library.entity.Rent;
import com.airtribe.library.entity.RentStatus;
import com.airtribe.library.entity.User;
import com.airtribe.library.exception.BookStatusException;
import com.airtribe.library.exception.DataNotFoundExcpetion;
import com.airtribe.library.strategy.FeeCalculator;
import com.airtribe.library.utils.TimeUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class RentalService {
    private final UserService userService;
    private final BookService bookService;
    private final Datastore<Rent> rentDB;
    private FeeCalculator feeCalculator;

    public RentalService(UserService u, BookService b, FeeCalculator feeCalculator){
        this.bookService = b;
        this.userService = u;
        this.rentDB = new LocalDatastore<>();
        this.feeCalculator = feeCalculator;
    }

    public Rent loanBook(String userId, String bookId){
        User u = userService.getUserById(userId);
        Book b = bookService.getBookById(bookId);
        if(b.isRented()){
            throw new BookStatusException("Book is already rented. Not available now!!");
        }
        Rent r = new Rent(bookId, userId);
        b.setRented(true);
        rentDB.add(r.getId(), r);
        return r;
    }

    private Rent findRentById(String id){
        Optional<Rent> r = rentDB.getItemById(id);
        if(r.isPresent())
            return r.get();
        throw new DataNotFoundExcpetion("Rent not found!!");
    }

    public double returnBook(String rentId, String returnDate){
        Rent r = findRentById(rentId);
        if(r.getStatus() == RentStatus.COMPLETED){
            throw new BookStatusException("Book has been returned already on " + r.getReturnedAt());
        }
        LocalDateTime date = TimeUtils.parse(returnDate);
        long days = TimeUtils.calculateDays(r.getRentedAt(), date);
        Book b = bookService.getBookById(r.getBookId());
        b.setRented(false);
        r.markCompleted(date);
        int bookPrice = b.getPrice();
        return feeCalculator.calculate(days, bookPrice);
    }

    public List<Rent> getUserHistory(String userId){
        return List.copyOf(rentDB.getAllItems().stream().filter(r -> r.getUserId().equals(userId)).toList());
    }

    public FeeCalculator getFeeCalculator() {
        return feeCalculator;
    }

    public void setFeeCalculator(FeeCalculator feeCalculator) {
        this.feeCalculator = feeCalculator;
    }
}
