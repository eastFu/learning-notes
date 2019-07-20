package pers.east.learning.stream;

import java.util.stream.Stream;

/**
 * @author East.F
 * @ClassName: StreamMatch
 * @Description: TODO
 * @date 2019/7/20 16:34
 */
public class StreamMatch {
    public static void main(String[] args) {

        /**
         *  match : 返回boolean ，常用 allMatch、anyMatch、noneMatch
         */

        // allMatch 所有元素都匹配 >10 为true
        System.out.println(Stream.of(1,2,8,6,7,4,5,2).allMatch(i ->i>10));

        // anyMatch 任意一个满足>6 为true
        System.out.println(Stream.of(1,2,8,6,7,4,5,2).anyMatch(i->i>6));

        // noneMatch 所有的都不满足<0  为true
        System.out.println(Stream.of(1,2,8,6,7,4,5,2).noneMatch(i->i<0));
    }
}
