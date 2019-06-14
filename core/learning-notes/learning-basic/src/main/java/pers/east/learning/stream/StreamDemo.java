package pers.east.learning.stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author East.F
 * @ClassName: StreamDemo
 * @Description: TODO
 * @date 2019/6/14 9:18
 */
public class StreamDemo {
    public static void main(String[] args) {

        /**
         * 测试结果： 测试数据1个亿
         * test list size : 100000000
         * for cost time : 15554
         * stream cost time : 10764
         * parallel Stream cost time : 3803
         *
         * 当数据量到达一定量的时候， 使用stream 或者并行的 stream 会快一点，
         * 数据量很少的时候 还是 for 更快。
         *
         * 并行化：因为并行并一定意味着性能会更好。 应该是缺少一个不字。
         * stream 在代码上更优雅。
         */

        List<Dish> model = Arrays.asList(
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

        List<Dish> menu = new ArrayList<>();
        for(int i = 0;i< 10000000 ;i++){
            menu.addAll(model);
        }
        System.out.println("test list size : "+menu.size());


        //java 7 for if
        long start1 = System.currentTimeMillis();
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: menu){
            if(d.getCalories() < 400){
                lowCaloricDishes.add(d);
            }
        }
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        List<String> lowCaloricDishesName = new ArrayList<>();
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
//        System.out.println("for data : "+lowCaloricDishesName);
        System.out.println("for cost time : "+(System.currentTimeMillis()-start1));

        //java 8 stream
        long start2 = System.currentTimeMillis();
        List<String> lowCaloricDishesName2= menu.stream()
                .filter(d ->d.getCalories()<400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
//        System.out.println("stream data : "+lowCaloricDishesName2);
        System.out.println("stream cost time : "+(System.currentTimeMillis()-start2));

        //java 8 parallel Stream
        long start3 = System.currentTimeMillis();
        List<String> lowCaloricDishesName3= menu.parallelStream()
                .filter(d ->d.getCalories()<400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
//        System.out.println("parallel stream data : "+lowCaloricDishesName3);
        System.out.println("parallel Stream cost time : "+(System.currentTimeMillis()-start3));
    }

}
