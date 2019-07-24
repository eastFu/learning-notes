package pers.east.learning.java8.collector;

import pers.east.learning.java8.stream.Dish;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static pers.east.learning.java8.collector.CollectorsApiTest1.model;

/**
 * @author East.F
 * @ClassName: CollectorsApiTest2
 * @Description: Collectors 工具方法的练习2
 * @date 2019/7/21 11:48
 */
public class CollectorsApiTest3 {
    public static void main(String[] args) {
        System.out.println("========PartitioningBy1===========");
        testPartitioningBy1();
        System.out.println("========PartitioningBy2===========");
        testPartitioningBy2();
        System.out.println("========Reducing1===========");
        testReducing1();
        System.out.println("========Reducing2===========");
        testReducing2();
        System.out.println("========Reducing3===========");
        testReducing3();
    }


    private static void testPartitioningBy1(){
        Optional.ofNullable(model.stream().collect(Collectors.partitioningBy(Dish::isVegetarian))).ifPresent(System.out::println);
    }

    private static void testPartitioningBy2(){
        Optional.ofNullable(model.stream().collect(Collectors
                .partitioningBy(Dish::isVegetarian,Collectors.summingInt(Dish::getCalories)))).ifPresent(System.out::println);
    }

    private static void testReducing1(){
        Optional.ofNullable(model.stream()
                .collect(Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Dish::getCalories)))))
                .ifPresent(System.out::println);
    }

    private static void testReducing2(){
        Optional.ofNullable(model.stream().map(Dish::getCalories)
                .collect(Collectors.reducing(0,(d1,d2)->d1+d2))).ifPresent(System.out::println);
    }

    private static void testReducing3(){
        Optional.ofNullable(model.stream().collect(
                Collectors.reducing(
                        0,
                        Dish::getCalories,
                        (d1,d2)->d1+d2
                )
        )).ifPresent(System.out::println);
    }
}
