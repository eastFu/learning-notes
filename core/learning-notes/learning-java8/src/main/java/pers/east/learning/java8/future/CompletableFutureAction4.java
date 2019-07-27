package pers.east.learning.java8.future;

import java.util.concurrent.CompletableFuture;

/**
 * @author East.F
 * @ClassName: FutureAction3
 * @Description:  completableFuture api 讲解上
 * @date 2019/7/25 19:25
 */
public class CompletableFutureAction4 {


    public static void main(String[] args) throws InterruptedException {

        /**
         *  supplyAsync 创建一个CompletableFuture（2个supplyAsync 可以支持返回值,2个runAsync 不支持返回值）
         *
         *  thenApply 当前阶段正常完成以后执行,而且当前阶段的执行的结果会作为下一阶段的输入参数，相当于future模式中的callback 回调，异常不运行
         *  thenApplyAsyn 同thenApply,但是默认是异步执行，这里所谓的异步指的是不在当前线程内执行,相当于future模式中的callback 回调，异常不运行
         *  handle  是执行任务完成时对结果的处理和 thenApply 方法处理方式基本一样。不同的是 handle 是在任务完成后再执行，还可以处理异常的任务
         *
         *  thenAccept 无返回值,接收上一阶段的输出作为本阶段的输入, （thenApply是进行生产，thenAccept 是进行消耗）
         *  thenRun 无返回值,根本不关心前一阶段的输出，根本不不关心前一阶段的计算结果，因为它不需要输入参数（thenApply是进行生产，thenAccept 是进行消耗）
         *
         *  whenComplete 当CompletableFuture的计算结果完成，或者抛出异常的时候执行
         *
         *  thenCompose 有返回值，两个completable 组合进行工作
         *  thenCombine 有返回值，类似thenCompose ，只不过会将两个completable结果传递操作，整合计算
         *  thenAcceptBoth  和thenCombine 类似，但是没有返回值
         */

        CompletableFuture.supplyAsync(()->1).thenApply(i->Integer.sum(i,10)).whenComplete((v,t)-> System.out.println(v));

        CompletableFuture.supplyAsync(()->1).thenApply(i->Integer.sum(i,10)).whenCompleteAsync((v,t)-> System.out.println(v));

        CompletableFuture.supplyAsync(()->1).handle((v,t)->Integer.sum(v,10)).whenComplete((v,t)-> System.out.println(v))
                .thenRun(System.out::println);

        CompletableFuture.supplyAsync(()->1).thenApply(i->Integer.sum(i,10)).thenAccept(System.out::println);

        CompletableFuture.supplyAsync(()->1).thenCompose(i->CompletableFuture.supplyAsync(()->10*i)).thenAccept(System.out::println);

        CompletableFuture.supplyAsync(()->1).thenCombine(CompletableFuture.supplyAsync(()->2.0d),(r1,r2)->r1+r2).thenAccept(System.out::println);

        CompletableFuture.supplyAsync(()->1).thenAcceptBoth(CompletableFuture.supplyAsync(()->2.0d),(r1,r2)->{
            System.out.println(r1);
            System.out.println(r2);
            System.out.println(r1+r2);
        });

        Thread.sleep(1000L);
    }


}

