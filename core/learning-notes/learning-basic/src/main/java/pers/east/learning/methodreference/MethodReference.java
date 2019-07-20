package pers.east.learning.methodreference;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author East.F
 * @ClassName: MethodReference
 * @Description: TODO
 * @date 2019/7/19 20:25
 */
public class MethodReference {

    public static void main(String[] args) {

        Function<String,Integer> f = Integer::parseInt;
        Integer result = f.apply("123");
        System.out.println(result);

        BiFunction<String,Integer,Character> f2 = String::charAt;
        Character c = f2.apply("hello",3);
        System.out.println(c);

        Function<Integer,Character> f3 = "hello"::charAt;
        Character c2 = f3.apply(4);
        System.out.println(c2);

        System.out.println("=======================自定义三个参数的 funciton ==========================");
        ThreeFunction<Long,String,String,Apple> f4 = Apple::new;
        Apple apple = f4.applay(150L,"red","china");
        System.out.println(apple);

        System.out.println("=======================lambda实现==========================");


        List<Apple> list = Arrays.asList(new Apple(120L,"red","japan"),new Apple(190L,"red","china"),new Apple(160L,"red","usa"));
        list.sort((a,b) -> a.getColor().compareTo(b.getColor()));
        list.stream().forEach(a-> System.out.println(a));



        System.out.println("==========================方法推导实现=======================");
        /**
         * (Comparator<T> & Serializable) c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2))
         */
        List<Apple> list2 = Arrays.asList(new Apple(120L,"red","japan"),new Apple(190L,"red","china"),new Apple(160L,"red","usa"));
        list2.sort(Comparator.comparing(Apple::getColor));
        list2.stream().forEach(a-> System.out.println(a));
    }

}
