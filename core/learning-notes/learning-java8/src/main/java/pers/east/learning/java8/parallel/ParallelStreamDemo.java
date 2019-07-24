package pers.east.learning.java8.parallel;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author East.F
 * @ClassName: ParallelStreamDemo
 * @Description: 并行流的练习
 * @date 2019/7/22 18:18
 */
public class ParallelStreamDemo {

    public static void main(String[] args) {

        /**
         * 这是一个全局设置，因此它将影响代码中所有的并行流。反过来说，目前还无法专为某个并行流指定这个值
         * 一般而言，让ForkJoinPool的大小等于处理器数量是个不错的默认值，除非你有很好的理由，否则我们强烈建议你不要修改它
         *
         * 流的数据源和可分解性:
         *      ArrayList 极佳
         *      LinkedList 差
         *      IntStream.range 极佳
         *      Stream.iterate 差
         *      HashSet 好
         *      TreeSet 好
         */
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","12");

        System.out.println(test(ParallelStreamDemo::normalAdd,100_000_000) +"ms");
//        System.out.println(test(ParallelStreamDemo::iterateStream,100_000_000) +"ms");
//        System.out.println(test(ParallelStreamDemo::iterateParallelStream,100_000_000) +"ms");
//        System.out.println(test(ParallelStreamDemo::iterateParallelStream2,100_000_000) +"ms");
        System.out.println(test(ParallelStreamDemo::iterateParallelStream3,100_000_000) +"ms");
    }

    private static long test(Function<Long,Long> adder,long limit){
        long fastest = Long.MAX_VALUE;
        for (int i=0; i <10;i++){
            long start = System.currentTimeMillis();
            long result = adder.apply(limit);
            long cost = System.currentTimeMillis()-start;
//            System.out.println("result:"+result);
            fastest = cost<fastest?cost:fastest;
        }
        return fastest;
    }

    private static long iterateStream(long limit){
        return Stream.iterate(1L,i->i+1).limit(limit).reduce(0L,Long::sum);
    }

    private static long iterateParallelStream(long limit){
        return Stream.iterate(1L,i->i+1).parallel().limit(limit).reduce(0L,Long::sum);
    }
    private static long iterateParallelStream2(long limit){
        return Stream.iterate(1L,i->i+1).parallel().limit(limit).mapToLong(Long::longValue).reduce(0L,Long::sum);
    }
    private static long iterateParallelStream3(long limit){
        //LongStream.rangeClosed直接产生原始类型的long数字，没有装箱拆箱的开销
        return LongStream.rangeClosed(1L, limit).parallel().reduce(0L,Long::sum);
    }


    private static long normalAdd(long limit){
        long result = 0L;
        for(long i=1L;i<limit;i++){
            result+=i;
        }
        return result;
    }



}
