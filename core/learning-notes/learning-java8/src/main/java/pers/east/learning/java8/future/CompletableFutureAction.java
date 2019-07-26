package pers.east.learning.java8.future;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author East.F
 * @ClassName: FutureAction3
 * @Description: java future  异步回调 自定义的实现
 * @date 2019/7/25 19:25
 */
public class CompletableFutureAction {

    private final static Random RANDOM = new Random(System.currentTimeMillis());
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(()->{
            double value = get();
            completableFuture.complete(value);
        }).start();
        System.out.println("===go====");
        completableFuture.whenComplete((v,t)->{
           Optional.ofNullable(v).ifPresent(System.out::println);
           Optional.ofNullable(t).ifPresent(x->x.printStackTrace());
        });
//        Optional.ofNullable(completableFuture.get()).ifPresent(System.out::println);
    }

    public static double get(){
        try {
            Thread.sleep(RANDOM.nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return RANDOM.nextDouble();
    }


}

