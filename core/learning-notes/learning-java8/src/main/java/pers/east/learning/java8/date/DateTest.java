package pers.east.learning.java8.date;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateTest {

    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(1599097664922L/1000,0, ZoneOffset.ofHours(8));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println(dateTime.format(formatter));
    }
}
