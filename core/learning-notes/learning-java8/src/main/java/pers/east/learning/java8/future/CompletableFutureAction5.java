package pers.east.learning.java8.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

/**
 * @author East.F
 * @ClassName: FutureAction3
 * @Description: completableFuture api 讲解下
 * @date 2019/7/25 19:25
 */
public class CompletableFutureAction5 {


    public static void main(String[] args) throws InterruptedException {

        /**
         *  runAfterBoth  两个CompletableFuture都执行结束后运行
         *
         *  applyToEither  其中一个执行完后，接受结果运行，并返回值
         *
         *  acceptEither  其中任意一个执行完后，接受结果并处理，但是不返回值
         *
         *  runAfterEither 其中任意一个执行完后，不接受结果，不返回值
         *
         *  anyOf  一组CompletableFuture 任意一个执行过后运行
         *
         *  allOf  一组CompletableFuture等到全部completableFuture 都执行完毕
         */

        CompletableFuture.supplyAsync(() -> 1).runAfterBoth(CompletableFuture.supplyAsync(() -> 2), () -> System.out.println("end...."));

        CompletableFuture.supplyAsync(() -> {
            System.out.println("11111111");
            return 11111;
        }).applyToEither(CompletableFuture.supplyAsync(()->{
            System.out.println("22222222222");
            return 22222;
        }),i -> 5*i).thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> {
            System.out.println("11111111");
            return 11111;
        }).acceptEither(CompletableFuture.supplyAsync(()->{
            System.out.println("22222222222");
            return 2222222;
        }),System.out::println);

        CompletableFuture.supplyAsync(() -> {
            System.out.println("11111111");
            return 11111;
        }).runAfterEither(CompletableFuture.supplyAsync(()->{
            System.out.println("22222222222");
            return 2222222;
        }),()-> System.out.println("I am run after either"));

        List<CompletableFuture<Double>> collect = Arrays.asList(1, 2, 3, 4).stream().map(i -> CompletableFuture.supplyAsync(CompletableFutureAction::get)).collect(toList());
        CompletableFuture.allOf(collect.toArray(new CompletableFuture[collect.size()])).thenRun(()->System.out.println("allof"));

        CompletableFuture.anyOf(collect.toArray(new CompletableFuture[collect.size()])).thenRun(()->System.out.println("anyOf"));

        Thread.sleep(10000L);
    }


}

