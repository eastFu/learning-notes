package pers.east.learning.java8.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;

/**
 * @author East.F
 * @ClassName: ForkJoinSumCalculator
 * @Description: 分支/并行框架练习 ，核心接口ForkJoinPool
 * @date 2019/7/23 8:39
 */
public class ForkJoinSumCalculator2 extends RecursiveAction {

    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator2(long[] numbers) {
        this( 0, numbers.length,numbers);
    }

    private ForkJoinSumCalculator2(int start, int end,long[] numbers) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    public static long forkJoinSum2(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinSumCalculator2 task = new ForkJoinSumCalculator2(numbers);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(task);
        return ForkJoinSumCalculator2.Helper.getResult();
    }


    @Override
    protected void compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            for(int i=start;i<end;i++){
                Helper.accumulate(numbers[i]);
            }
        }else{
            int mid = (start+end)/2;
            ForkJoinSumCalculator2 leftTask = new ForkJoinSumCalculator2(start, mid,numbers);
            ForkJoinSumCalculator2 rightTask = new ForkJoinSumCalculator2(mid, end, numbers);
            leftTask.fork();
            rightTask.fork();
            leftTask.join();
            leftTask.join();
        }

    }

    static class Helper{
        private static final AtomicLong result = new AtomicLong(0);
        static void accumulate(long value){
            result.addAndGet(value);
        }
        public static long getResult(){
            return result.get();
        }
        void clear(){
            result.set(0L);
        }
    }

}
