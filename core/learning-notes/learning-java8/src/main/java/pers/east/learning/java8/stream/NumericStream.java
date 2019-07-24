package pers.east.learning.java8.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author East.F
 * @ClassName: NumericStream
 * @Description: TODO
 * @date 2019/7/20 17:33
 */
public class NumericStream {
    public static void main(String[] args) {

        /**
         * NumericStream ： 进行自动的装箱拆箱，减少内存上开销，Integer（包装类）比 int 占用空间要大
         */
        Integer sum = Stream.of(1,8,4,5,7,3,6,1,2).filter(i->i.intValue()>3).reduce(0,Integer::sum);
        System.out.println(sum);

        // 利用mapToInt 将Integer 转int,降低内存消耗
        System.out.println(Stream.of(1,8,4,5,7,3,6,1,2).mapToInt(i->i.intValue()).filter(i->i>3).reduce(0,Integer::sum));


        // 求100以内 可以和 9 组成三角形的整数
        int a = 9;
        IntStream.rangeClosed(1,100).filter(b-> Math.sqrt(a*a+b*b)%1==0)
                .boxed().map(b->new int[]{a,b, (int) Math.sqrt(a*a+b*b)})
                .forEach(r->System.out.println(r[0]+","+r[1]+","+r[2]));

        IntStream.rangeClosed(1,100).filter(b-> Math.sqrt(a*a+b*b)%1==0)
                .mapToObj(b->new int[]{a,b, (int) Math.sqrt(a*a+b*b)})
                .forEach(r->System.out.println(r[0]+","+r[1]+","+r[2]));

    }
}
