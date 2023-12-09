package com.patika.vet.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {
    public static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static LocalDateTime StringToDate(String date){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(date,formatter);
    }
}
