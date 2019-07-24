package pers.east.learning.java8.stream;

import java.util.stream.Stream;

/**
 * @author East.F
 * @ClassName: StreamFind
 * @Description: TODO
 * @date 2019/7/20 16:48
 */
public class StreamFind {
    public static void main(String[] args) {

        /**
         *  find : findAny、findFirst
         *  获得 Optional 可以避免空指针异常 NullPointException
         *  可以利用 Optional 做各种判断业务
         */
        System.out.println(Stream.of(1,2,8,6,7,4,5,2).filter(i->i%2==0).findAny().get());

        // orElse 避免空指针异常
        System.out.println(Stream.of(1,2,8,6,7,4,5,2).filter(i->i>10).findFirst().orElse(-1));

        // isPresent 判断是否为null
        System.out.println(Stream.of(1,2,8,6,7,4,5,2).filter(i->i>100).findFirst().isPresent());

        // ifPresent 如果true执行
        Stream.of(1,2,8,6,7,4,5,2).filter(i->i%2==0).findFirst().ifPresent(System.out::println);



    }
}
