package pers.east.learning.java8.future;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author East.F
 * @ClassName: FutureAction3
 * @Description:  completable future 的流水线作业
 * @date 2019/7/25 19:25
 */
public class CompletableFutureAction3 {

    private final static Random RANDOM = new Random(System.currentTimeMillis());
    public static void main(String[] args) throws InterruptedException {
        test1();
    }

    private static void test1(){
        ExecutorService executorService = Executors.newFixedThreadPool(2, r->{
            Thread t =new Thread(r);
            t.setDaemon(false);
            return t;
        });
        /*CompletableFuture.supplyAsync(CompletableFutureAction::get,executorService)
                .thenApply(CompletableFutureAction3::multiply)
                .whenComplete((v, t)-> Optional.ofNullable(v).ifPresent(System.out::println));*/

        List<Integer> productionIDs = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> result = productionIDs.stream()
                .map(i -> CompletableFuture.supplyAsync(() -> queryProduction(i), executorService))
                .map(future -> future.thenApply(CompletableFutureAction3::multiply))
                .map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(result);

    }

    private static double multiply(double value){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value*10d;
    }

    private static double queryProduction(int i){
        double value = CompletableFutureAction.get();
        System.out.println(value);
        return value;
    }


}

