package pers.east.learning.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author East.F
 * @ClassName: StreamMap
 * @Description: TODO
 * @date 2019/7/20 15:22
 */
public class StreamMap {

    private static List<Dish> model;
    static {
        model = Arrays.asList(
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
    }

    public static void main(String[] args) {

        System.out.println(Stream.of(1,6,8,4,1,8,9,7,2,4).map(i->i*2).collect(Collectors.toList()));

        System.out.println(model.stream().map(d->d.getName()).collect(Collectors.toList()));

        //flatmap flat 扁平化, 取出重复字符练习, 方法生成的各个流会被合并或者扁平化为一个单一的流
        String[] words = {"hello","world"};
        Arrays.stream(words).map(w->w.split("")).flatMap(Arrays::stream).distinct().forEach(System.out::print);
    }
}
