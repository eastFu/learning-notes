package pers.east.learning.methodreference;

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

        
    }
}
