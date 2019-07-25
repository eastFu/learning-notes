package pers.east.learning.java8.future;

import java.util.concurrent.*;

/**
 * @author East.F
 * @ClassName: FutureAction2
 * @Description: java future （未来）模式
 * @date 2019/7/25 19:25
 */
public class FutureAction2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            Thread.sleep(5000);
            return "I am finished";
        });
//        System.out.println(future.get(6000, TimeUnit.SECONDS));

        while (!future.isDone()) {
            Thread.sleep(200);
            System.out.println("get...");
        }
        System.out.println(future.get());
        executorService.shutdown();
    }

}

