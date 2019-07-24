package pers.east.learning.java8.lambda;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author East.F
 * @ClassName: LambdaTest
 * @Description: pers.east.learning.java8.lambda 表达式练习
 * @date 2019/4/28 19:03
 */
public class LambdaTest {

    @FunctionalInterface
    public interface Fighting{
        boolean figth(String country);
    }

    public static void go(String country,Fighting fighting){
        System.out.println(fighting.figth(country)?"yes yes yes ...":"no no no ...");
    }

    public static void main(String[] args) {
        /**
         *  java8 pers.east.learning.java8.lambda 表达式，替换匿名内部类，策略模式方法
         *  java.util.function 包下 提供了很多lambda表达式的 Functional interface
         *
         *  常用的：
         *
         *   Predicate
         *   boolean test(T t);
         *
         *   Consumer
         *   void accept(T t)
         *
         *   Function<T,R>
         *   R apply(T t)
         *
         *   Supplier<T>
         *   T get()
         */

        go("America", who ->"America".equalsIgnoreCase(who));


        new Thread(()-> System.out.println("helo pers.east.learning.java8.lambda")).start();

        Supplier<LambdaTest> lambdaTest = LambdaTest::new;
        lambdaTest.get();

        List<LambdaTest> lists = Collections.emptyList();

    }
}
