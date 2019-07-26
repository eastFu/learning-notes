package pers.east.learning.java8.future;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author East.F
 * @ClassName: FutureAction3
 * @Description: java future  异步回调 自定义的实现
 * @date 2019/7/25 19:25
 */
public class CompletableFutureAction2 {

    private final static Random RANDOM = new Random(System.currentTimeMillis());
    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
        test3();

    }

    private static void test1() throws InterruptedException {
        AtomicBoolean finished = new AtomicBoolean(false);
        CompletableFuture.supplyAsync(CompletableFutureAction::get).whenComplete((v, t)->{
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(x->x.printStackTrace());
            finished.set(true);
        });

        System.out.println("======I am no  block=======");
        while (!finished.get()){
            Thread.sleep(20);
        }
//        Thread.currentThread().join();
    }
    private static void test2(){
        ExecutorService executorService = Executors.newFixedThreadPool(2,r->{
            Thread t =new Thread(r);
            t.setDaemon(false);
            return t;
        });
        executorService.execute(()->System.out.println("test..."));

        executorService.shutdown();
//        executorService.shutdownNow();
    }

    public static void test3(){
        ExecutorService executorService = Executors.newFixedThreadPool(2,r->{
            Thread t =new Thread(r);
            t.setDaemon(true);
//            t.setDaemon(false);
            return t;
        });

        AtomicBoolean finished = new AtomicBoolean(false);
        CompletableFuture.supplyAsync(CompletableFutureAction::get,executorService).whenComplete((v,t)->{
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(x->x.printStackTrace());
            finished.set(true);
        });
        System.out.println("======I am no  block=======");
    }
}

