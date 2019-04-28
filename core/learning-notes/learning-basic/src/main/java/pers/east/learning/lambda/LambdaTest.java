package pers.east.learning.lambda;

/**
 * @author East.F
 * @ClassName: LambdaTest
 * @Description: lambda 表达式练习
 * @date 2019/4/28 19:03
 */
public class LambdaTest {

    public static void main(String[] args) {
        new Thread(()-> System.out.println("helo lambda")).start();
    }
}
