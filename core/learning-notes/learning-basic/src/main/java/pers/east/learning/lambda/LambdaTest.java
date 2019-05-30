package pers.east.learning.lambda;

import java.util.function.Supplier;

/**
 * @author East.F
 * @ClassName: LambdaTest
 * @Description: lambda 表达式练习
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

        go("America", who ->"America".equalsIgnoreCase(who));


        new Thread(()-> System.out.println("helo lambda")).start();

        Supplier<LambdaTest> lambdaTest = LambdaTest::new;
    }
}
