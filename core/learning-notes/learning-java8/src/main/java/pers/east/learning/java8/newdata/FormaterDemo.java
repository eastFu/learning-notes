package pers.east.learning.java8.newdata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author East.F
 * @ClassName: FormaterDemo
 * @Description: TODO
 * @date 2019/7/27 15:54
 */
public class FormaterDemo {
    public static void main(String[] args) {
        testFormater();
        testParse();

    }

    private static void testFormater(){
        LocalDate localDate =LocalDate.now();
        System.out.println(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(localDate.format(formatter));
    }

    private static void testParse(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse("2009-04-26", formatter);
        System.out.println(localDate);
    }
}
