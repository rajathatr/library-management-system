package com.airtribe.library.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class IDGenerator {
    private static final AtomicInteger bookID = new AtomicInteger(0);
    private static final AtomicInteger userID = new AtomicInteger(0);
    private static final AtomicInteger rentID = new AtomicInteger(0);

    public static String getNextBookID(){
        return "Book-" + bookID.addAndGet(1);
    }

    public static String getNextUserID(){
        return "User-" + userID.addAndGet(1);
    }

    public static String getNextRentID(){
        return "Rent-" + rentID.addAndGet(1);
    }
}
