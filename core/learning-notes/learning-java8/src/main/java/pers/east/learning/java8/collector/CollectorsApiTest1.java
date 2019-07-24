package pers.east.learning.java8.collector;

import pers.east.learning.java8.stream.Dish;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author East.F
 * @ClassName: CollectorsApiTest1
 * @Description: Collectors 工具方法的练习1
 * @date 2019/7/21 10:52
 */
public class CollectorsApiTest1 {
    public static final List<Dish> model = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH),
            new Dish("salmon", false, 470, Dish.Type.FISH));

    public static void main(String[] args) {
        System.out.println("========AveragingDouble===========");
        testAveragingDouble();
        System.out.println("========AveragingInt===========");
        testAveragingInt();
        System.out.println("========AveragingLong===========");
        testAveragingLong();
        System.out.println("========CollectingAndThen===========");
        testCollectingAndThen();
        System.out.println("========Counting===========");
        testCounting();
        System.out.println("=========SummingInt==========");
        testSummingInt();
        System.out.println("=========GroupBy1==========");
        testGroupBy1();
        System.out.println("=========GroupBy2==========");
        testGroupBy2();
        System.out.println("=========GroupBy3==========");
        testGroupBy3();
    }

    private static void testAveragingDouble(){
        Optional.ofNullable(model.stream()
                .collect(Collectors.averagingDouble(Dish::getCalories)))
                .ifPresent(d->System.out.println("【averagingDouble】求的卡路里平局值："+d));
    }

    private static void testAveragingInt(){
        Optional.ofNullable(model.stream()
                .collect(Collectors.averagingInt(Dish::getCalories)))
                .ifPresent(d->System.out.println("【averagingInt】求的卡路里平局值："+d));
    }

    private static void testAveragingLong(){
        Optional.ofNullable(model.stream()
                .collect(Collectors.averagingLong(Dish::getCalories)))
                .ifPresent(d->System.out.println("【averagingLong】求的卡路里平局值："+d));
    }

    private static void testCollectingAndThen(){
        String avg = model.stream().collect(
                Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories)
                , a->"【collectingAndThen】求的卡路里平局值"+a)
        );
        Optional.ofNullable(avg).ifPresent(System.out::println);
    }

    private static void testCounting(){
        Optional.ofNullable(model.stream().collect(Collectors.counting()))
                .ifPresent(d->System.out.println("【counting】求的Dish个数："+d));
    }

    private static void testSummingInt(){
        Optional.ofNullable(model.stream().collect(Collectors.summingInt(Dish::getCalories)))
                .ifPresent(d->System.out.println("【summingInt】求的卡路里和："+d));
    }

    private static void testGroupBy1(){
        Optional.ofNullable(model.stream().collect(Collectors.groupingBy(Dish::getType)))
                .ifPresent(d->System.out.println("【groupingBy1】分组："+d));
    }

    private static void testGroupBy2(){
        Optional.ofNullable(model.stream()
                .collect(Collectors.groupingBy(Dish::getType,Collectors.summarizingInt(Dish::getCalories))))
                .ifPresent(d->System.out.println("【groupingBy2】分组："+d));
    }

    private static void testGroupBy3(){
        Map<Dish.Type, Double> collect = model.stream().collect(
                Collectors.groupingBy(
                        Dish::getType,
                        TreeMap::new,
                        Collectors.averagingInt(Dish::getCalories)
                )
        );
        Optional.ofNullable(collect).ifPresent(d->System.out.println("【groupingBy3】分组："+d));
    }

}
