package com.airtribe.library.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeUtils {
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public static LocalDateTime parse(String time){
        try{
            return LocalDateTime.parse(time, formatter);
        }catch (DateTimeParseException e){
            System.out.println(e.getMessage());
            throw new RuntimeException("Invalid Date time format!!");
        }
    }

    public static long calculateDays(LocalDateTime from, LocalDateTime to){
        Duration d = Duration.between(from, to);
        return d.toDays();
    }
}
