package pers.east.learning.java8.stream;

import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * @author East.F
 * @ClassName: StreamReduce
 * @Description: TODO
 * @date 2019/7/20 17:09
 */
public class StreamReduce {

    public static void main(String[] args) {
        /**
         *  reduce 做聚合， 求和、求最大值、求最小值
         */

        //求和
        System.out.println(Stream.of(1,2,8,6,7,4,5,2).reduce(0,(i, j)->i+j));
        Stream.of(1,2,8,6,7,4,5,2).reduce((i,j)->i+j).ifPresent(System.out::println);

        //求最小值
        Stream.of(1,2,8,6,7,4,5,2).reduce(BinaryOperator.minBy((a,b)->a.compareTo(b))).ifPresent(System.out::println);
        Stream.of(1,2,8,6,7,4,5,2).reduce(Integer::min).ifPresent(System.out::println);
        Stream.of(1,2,8,6,7,4,5,2).reduce((i,j)->i>j?j:i).ifPresent(System.out::println);

        // 求最大值
        Stream.of(1,2,8,6,7,4,5,2).reduce(BinaryOperator.maxBy((a,b)->a.compareTo(b))).ifPresent(System.out::println);
        Stream.of(1,2,8,6,7,4,5,2).reduce(Integer::max).ifPresent(System.out::println);
        Stream.of(1,2,8,6,7,4,5,2).reduce((i,j)->i>j?i:j).ifPresent(System.out::println);

    }

}
