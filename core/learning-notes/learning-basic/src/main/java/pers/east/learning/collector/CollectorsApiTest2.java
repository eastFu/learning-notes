package pers.east.learning.collector;

import pers.east.learning.stream.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static pers.east.learning.collector.CollectorsApiTest1.model;
/**
 * @author East.F
 * @ClassName: CollectorsApiTest2
 * @Description: Collectors 工具方法的练习2
 * @date 2019/7/21 11:48
 */
public class CollectorsApiTest2 {
    public static void main(String[] args) {
        System.out.println("========GroupingByConcurrent1===========");
        testGroupingByConcurrent1();
        System.out.println("========GroupingByConcurrent2===========");
        testGroupingByConcurrent2();
        System.out.println("========GroupingByConcurrent3===========");
        testGroupingByConcurrent3();
        System.out.println("========Joining1===========");
        testJoining1();
        System.out.println("========Joining2===========");
        testJoining2();
        System.out.println("========Joining3===========");
        testJoining3();
        System.out.println("========Mapping===========");
        testMapping();
        System.out.println("========MaxBy===========");
        testMaxBy();
        System.out.println("========MinBy===========");
        testMinBy();
    }


    private static void testGroupingByConcurrent1(){
        ConcurrentMap<Dish.Type, List<Dish>> collect = model.stream()
                .collect(Collectors.groupingByConcurrent(Dish::getType));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }
    private static void testGroupingByConcurrent2(){
        ConcurrentMap<Dish.Type, Double> collect = model.stream().collect(
                Collectors.groupingByConcurrent(
                        Dish::getType,
                        Collectors.averagingInt(Dish::getCalories)
                )
        );
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testGroupingByConcurrent3(){
        ConcurrentSkipListMap<Dish.Type, Double> collect = model.stream().collect(
                Collectors.groupingByConcurrent(
                        Dish::getType, ConcurrentSkipListMap::new, Collectors.averagingInt(Dish::getCalories)
                )
        );
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testJoining1(){
        Optional.ofNullable(model.stream().map(Dish::getName).collect(
                Collectors.joining())).ifPresent(System.out::println);
    }

    private static void testJoining2(){
        Optional.ofNullable(model.stream().map(Dish::getName).collect(
                Collectors.joining(","))).ifPresent(System.out::println);
    }

    private static void testJoining3(){
        Optional.ofNullable(model.stream().map(Dish::getName).collect(
                Collectors.joining(",","【","】"))).ifPresent(System.out::println);
    }

    private static void testMapping(){
        Optional.ofNullable(model.stream()
                .collect(Collectors.mapping(Dish::getName,Collectors.joining(","))))
                .ifPresent(System.out::println);
    }

    private static void testMaxBy(){
        model.stream().collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories))).ifPresent(System.out::println);
    }

    private static void testMinBy(){
        model.stream().collect(Collectors.minBy(Comparator.comparing(Dish::getCalories))).ifPresent(System.out::println);
    }

}
