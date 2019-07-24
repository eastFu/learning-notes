package pers.east.learning.java8.collector;

import pers.east.learning.java8.stream.Dish;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static pers.east.learning.java8.collector.CollectorsApiTest1.model;

/**
 * @author East.F
 * @ClassName: CollectorsApiTest2
 * @Description: Collectors 工具方法的练习2
 * @date 2019/7/21 11:48
 */
public class CollectorsApiTest4 {
    public static void main(String[] args) {
        System.out.println("========toCollection===========");
        testToCollection();
        System.out.println("========toConcurrentMap1===========");
        testToConcurrentMap1();
        System.out.println("========toConcurrentMap2===========");
        testToConcurrentMap2();
        System.out.println("========toConcurrentMap3===========");
        testToConcurrentMap3();
        System.out.println("========toList===========");
        testToList();
        System.out.println("========toSet===========");
        testToSet();
        System.out.println("========toMap1===========");
        testToMap1();
        System.out.println("========toMap2===========");
        testToMap2();
        System.out.println("========toMap3===========");
        testToMap3();
    }


    private static void testToCollection(){
        Optional.ofNullable(model.stream().collect(Collectors.toCollection(LinkedList::new))).ifPresent(System.out::println);
    }

    private static void testToConcurrentMap1(){
        Optional.ofNullable(model.stream().collect(Collectors.toConcurrentMap(Dish::getName,Dish::getCalories))).ifPresent(System.out::println);
    }
    private static void testToConcurrentMap2(){
        Optional.ofNullable(model.stream().collect(Collectors.toConcurrentMap(Dish::getType,v->1L,(a,b)->a+b))).ifPresent(System.out::println);
    }

    private static void testToConcurrentMap3(){
        Optional.ofNullable(model.stream().collect(Collectors.toConcurrentMap(Dish::getType,v->1L,(a,b)-> a+b, ConcurrentSkipListMap::new))).ifPresent(System.out::println);
    }

    private static void testToList(){
        Optional.ofNullable(model.stream().filter(Dish::isVegetarian).collect(Collectors.toList())).ifPresent(System.out::println);
    }

    private static void testToSet(){
        Optional.ofNullable(model.stream().filter(Dish::isVegetarian).collect(Collectors.toList())).ifPresent(System.out::println);
    }

    private static void testToMap1(){
        Optional.ofNullable(model.stream().collect(Collectors.toMap(Dish::getName,Dish::getType))).ifPresent(System.out::println);
    }

    private static void testToMap2(){
        Optional.ofNullable(model.stream().collect(Collectors.toMap(Dish::getType,v->1L,(a,b)-> a+b, LinkedHashMap::new))).ifPresent(System.out::println);
    }

    private static void testToMap3(){
        Optional.ofNullable(model.stream().collect(
                        Collectors.collectingAndThen(
                                Collectors.toMap(Dish::getType,v->1L,(a,b)-> a+b, LinkedHashMap::new),
                                Collections::synchronizedMap
                        )
            )
        ).ifPresent(System.out::println);
    }

}
