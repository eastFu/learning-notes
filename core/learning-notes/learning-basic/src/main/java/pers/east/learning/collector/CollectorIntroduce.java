package pers.east.learning.collector;

import pers.east.learning.methodreference.Apple;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author East.F
 * @ClassName: CollectorIntroduce
 * @Description: TODO
 * @date 2019/7/21 10:15
 */
public class CollectorIntroduce {
    private static List<Apple> list;

    public static void main(String[] args) {
        list = Arrays.asList(new Apple(150, "red", "usa")
                , new Apple(120, "red", "china")
                , new Apple(160, "pink", "japan")
                , new Apple(110, "yellow", "korea")
                , new Apple(170, "green", "russia")
                , new Apple(100, "green", "england"));

        List<Apple> greenList = list.stream().filter(a -> "green".equals(a.getColor())).collect(Collectors.toList());
        Optional.ofNullable(greenList).ifPresent(System.out::println);

        System.out.println("==============================");
        Optional.ofNullable(groupAppleByNormal()).ifPresent(System.out::println);

        System.out.println("==============================");
        Optional.ofNullable(groupAppleByFunction()).ifPresent(System.out::println);

        System.out.println("==============================");
        Optional.ofNullable(groupAppleByCollector()).ifPresent(System.out::println);
    }

    //传统方法分组实现
    private static Map<String, List<Apple>> groupAppleByNormal() {
        Map<String, List<Apple>> groupData = new HashMap<>();
        for (Apple apple : list) {
            if (!groupData.keySet().contains(apple.getColor())) {
                List<Apple> colorList = new ArrayList<>();
                colorList.add(apple);
                groupData.put(apple.getColor(), colorList);
            } else {
                groupData.get(apple.getColor()).add(apple);
            }
        }
        return groupData;
    }

    // 函数调用的分组实现
    private static Map<String, List<Apple>> groupAppleByFunction() {
        Map<String, List<Apple>> groupData = new HashMap<>();
        list.stream().forEach(a -> {
            List<Apple> colorList = Optional.ofNullable(groupData.get(a.getColor())).orElseGet(()->{
                List<Apple> list = new ArrayList<>();
                groupData.put(a.getColor(),list);
                return list;
            });
            colorList.add(a);
        });
        return groupData;
    }

    // 使用 java 8 collector 来进行分组
    private static Map<String, List<Apple>> groupAppleByCollector() {
        return list.stream().collect(Collectors.groupingBy(Apple::getColor));
    }
}
