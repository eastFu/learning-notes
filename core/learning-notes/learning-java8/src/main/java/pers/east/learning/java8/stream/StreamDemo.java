package pers.east.learning.java8.stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  @author East.F
 *
 *  测试用例描述 ： 准备 1 个亿的 dish 数据， 从中取出 卡路里 低于400 的水果，并进行排序
 *  1. 传统 for if 实现
 *  2. pers.east.learning.java8.lambda 实现
 *  2. pers.east.learning.java8.stream 实现
 *  3. pers.east.learning.java8.parallel Stream 实现
 *
 * 测试结果： 测试数据1个亿
 * test list size : 100000000
 * ==================java7 for and if ====================
 * 【for and if】 data size: 30000000
 * 【for and if】 cost time : 18809
 * ==================java8 pers.east.learning.java8.lambda====================
 * 【for and if】 data size: 30000000
 * 【for and if】 cost time : 13498
 * ==================java 8 pers.east.learning.java8.stream====================
 * 【java 8 pers.east.learning.java8.stream】 data size: 30000000
 * 【java 8 pers.east.learning.java8.stream】 cost time : 4685
 * ==================java 8 pers.east.learning.java8.parallel Stream====================
 * 【java 8 pers.east.learning.java8.parallel pers.east.learning.java8.stream】 data size: 30000000
     * 【java 8 pers.east.learning.java8.parallel pers.east.learning.java8.stream】 cost time : 3685
 *
 *
 * 结论：
 * 当数据量到达一定量的时候， 使用stream 或者并行的 pers.east.learning.java8.stream 会快一点，
 * 数据量很少的时候 还是 for 更快。
 *
 * 并行化：因为并行并一定意味着性能会更好。 应该是缺少一个不字（不一定性能会更好）。
 * pers.east.learning.java8.stream 在代码上更优雅。
 */
public class StreamDemo {
    public static void main(String[] args) {
        testByForIf();
        testByLambda();
        testByStream();
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testByParallelStream();
    }


    private static List<Dish> menu;
    static {
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
        menu = new ArrayList<>();
        for(int i = 0;i< 10000000 ;i++){
            menu.addAll(model);
        }
        System.out.println("test list size : "+menu.size());
    }
    private static void testByForIf(){
        System.out.println("==================java7 for and if ====================");
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
        System.out.println("【for and if】 data size: "+lowCaloricDishesName.size());
        System.out.println("【for and if】 cost time : "+(System.currentTimeMillis()-start1));
    }
    public static void testByLambda(){
        System.out.println("==================java8 pers.east.learning.java8.lambda====================");
        long start2 = System.currentTimeMillis();
        List<Dish> lowCaloricDishes2 = new ArrayList<>();
        for(Dish d: menu){
            if(d.getCalories() < 400){
                lowCaloricDishes2.add(d);
            }
        }
        Collections.sort(lowCaloricDishes2,Comparator.comparing(Dish::getCalories));
        List<String> lowCaloricDishesName2 = new ArrayList<>();
        for(Dish d: lowCaloricDishes2){
            lowCaloricDishesName2.add(d.getName());
        }
        System.out.println("【for and if】 data size: "+lowCaloricDishesName2.size());
        System.out.println("【for and if】 cost time : "+(System.currentTimeMillis()-start2));
    }
    public static void testByStream(){
        System.out.println("==================java 8 pers.east.learning.java8.stream====================");
        long start3 = System.currentTimeMillis();
        List<String> lowCaloricDishesName3= menu.stream()
                .filter(d ->d.getCalories()<400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("【java 8 pers.east.learning.java8.stream】 data size: "+lowCaloricDishesName3.size());
        System.out.println("【java 8 pers.east.learning.java8.stream】 cost time : "+(System.currentTimeMillis()-start3));
    }

    public static void testByParallelStream(){
        System.out.println("==================java 8 pers.east.learning.java8.parallel Stream====================");
        long start4 = System.currentTimeMillis();
        List<String> lowCaloricDishesName4= menu.parallelStream()
                .filter(d ->d.getCalories()<400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("【java 8 pers.east.learning.java8.parallel pers.east.learning.java8.stream】 data size: "+lowCaloricDishesName4.size());
        System.out.println("【java 8 pers.east.learning.java8.parallel pers.east.learning.java8.stream】 cost time : "+(System.currentTimeMillis()-start4));
    }
}
