package pers.east.learning.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author East.F
 * @ClassName: StreamFilter
 * @Description: TODO
 * @date 2019/7/20 15:02
 */
public class StreamFilter {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(8,3,4,1,2,8,7,9,5,8,4);
        System.out.println(list.stream().filter(a->a%2==0).collect(Collectors.toList()));
        System.out.println(list.stream().distinct().collect(Collectors.toList()));
        System.out.println(list.stream().skip(3).collect(Collectors.toList()));
        System.out.println(list.stream().limit(6).collect(Collectors.toList()));
        System.out.println(list.stream().max(Integer::compare).get());
        Stream.of(8,3,4,1,2).forEach(System.out::println);

    }
}
