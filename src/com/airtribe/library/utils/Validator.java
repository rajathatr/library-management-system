package com.airtribe.library.utils;

import com.airtribe.library.exception.InvalidDataException;

import java.time.LocalDateTime;

public class Validator {
    public static String nonBlank(String str){
        if(str == null || str.trim().isEmpty())
            throw new InvalidDataException("Data cannot be blank");
        return str.trim();
    }

    public static int positive(int value, String fieldName) {
        if (value <= 0) throw new InvalidDataException(fieldName + " must be positive");
        return value;
    }

    public static String email(String value) {
        String email = nonBlank(value);
        if (!email.contains("@") || email.startsWith("@") || email.endsWith("@")) {
            throw new InvalidDataException("Email is invalid");
        }
        return email;
    }

    public static void isReturnedInPast(LocalDateTime from, LocalDateTime to){
        if(from.isAfter(to))
            throw new InvalidDataException("Returned Timestamp cannot be less than the rented timestamp");
    }
}
