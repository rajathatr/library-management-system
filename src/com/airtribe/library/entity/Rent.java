package com.airtribe.library.entity;

import com.airtribe.library.utils.IDGenerator;
import com.airtribe.library.utils.Validator;

import java.time.LocalDateTime;

public class Rent {
    private final String id;
    private final String bookId;
    private final String userId;
    private final LocalDateTime rentedAt;
    private LocalDateTime returnedAt;
    private RentStatus status;

    public Rent(String bookId, String userId){
        this.bookId = bookId;
        this.userId = userId;
        this.rentedAt = LocalDateTime.now();
        this.id = IDGenerator.getNextRentID();
        this.status = RentStatus.ACTIVE;
    }

    public LocalDateTime getReturnedAt() {
        return returnedAt;
    }

    public String getId() {
        return id;
    }

    public String getBookId() {
        return bookId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getRentedAt() {
        return rentedAt;
    }

    public RentStatus getStatus(){
        return this.status;
    }

    public void markCompleted(LocalDateTime returnedAt){
        if(this.status.equals(RentStatus.ACTIVE)){
            Validator.isReturnedInPast(rentedAt, returnedAt);
            this.status = RentStatus.COMPLETED;
            this.returnedAt = returnedAt;
        }
    }

    @Override
    public String toString(){
        return "Rent ID : " + this.id
                + " User ID: " + this.userId
                + " Book ID: " + this.bookId
                + " Rented At: " + this.rentedAt.toString()
                + " Returned At: " + this.returnedAt.toString();
    }
}
