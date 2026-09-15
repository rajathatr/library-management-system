package com.airtribe.library.utils;

import com.airtribe.library.exception.InvalidDataException;

import java.time.LocalDateTime;

public class Validator {
    public static String nonBlank(String str){
        if(str == null || str.trim().isEmpty())
            throw new InvalidDataException("Data cannot be blank");
        return str;
    }

    public static void isReturnedInPast(LocalDateTime from, LocalDateTime to){
        if(from.isAfter(to))
            throw new InvalidDataException("Returned Timestamp cannot be less than the rented timestamp");
    }
}
