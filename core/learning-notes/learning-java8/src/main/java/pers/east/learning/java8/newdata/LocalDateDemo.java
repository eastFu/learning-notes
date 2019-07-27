package pers.east.learning.java8.newdata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * @author East.F
 * @ClassName: LocalDateDemo
 * @Description: TODO
 * @date 2019/7/27 15:19
 */
public class LocalDateDemo {
    public static void main(String[] args) {
        oldDate();
        testLocalDate();
        testLocalTime();
        testLocalDateTime();
        testInstant();
        testDuration();
        testPeriod();
    }

    /**
     * 问题1：定义奇怪，年从1900年开始，月份从0开始
     * 问题2：Date 即是date 也是time
     * 问题3： 非线程安全
     */
    private static void oldDate(){
        Date date = new Date(114,2,16);
        System.out.println(date);

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        for(int i=0;i<10;i++){
            new Thread(()->{
                for(int y=0;y<10;y++){
                    Date date1 =null;
                    try {
                        date1= format.parse("20160505");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(date1);
                }
            }).start();
        }
    }

    /**
     *  LocalDate 很好的解决上面的三个问题，而且还提供了很多好用的API 方法
     */
    private static void testLocalDate(){
        LocalDate localDate = LocalDate.of(2014, 2, 16);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());
        System.out.println(localDate.getDayOfYear());

        localDate.get(ChronoField.DAY_OF_WEEK);
    }

    /**
     *  时分秒
     */
    private static void testLocalTime(){
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime.getHour());
        System.out.println(localTime.getMinute());
        System.out.println(localTime.getSecond());
        System.out.println(localTime.getNano());
    }

    /**
     *  年月日时分秒
     */
    private static void testLocalDateTime(){
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        System.out.println(localDateTime);
        System.out.println(LocalDateTime.now());
    }

    /**
     *  时间点-时分秒
     */
    private static void testInstant(){
        Instant start = Instant.now();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Duration duration = Duration.between(Instant.now() ,start);
        System.out.println(duration.getNano());
    }

    /**
     *  时间间隔
     */
    private static void testDuration(){
        LocalTime localTime = LocalTime.now();
        LocalTime before = localTime.minusHours(1);
        Duration duration = Duration.between(localTime,before);
        System.out.println(duration.toHours());
    }

    /**
     *  Period表示宽范围的对象：时期，时代，世纪
     */
    private static void testPeriod(){
        Period period =Period.between(LocalDate.of(2015,5,12),LocalDate.of(2019,7,27));
        System.out.println(period.getYears());
    }

}
