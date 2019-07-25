package pers.east.learning.java8.future;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author East.F
 * @ClassName: FutureAction
 * @Description: java future （未来）模式， 自定义实现
 * @date 2019/7/25 19:25
 */
public class FutureAction {

    public static void main(String[] args) throws InterruptedException {
        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(5000);
                return "I am finished";
            } catch (InterruptedException e) {
                return "I am error";
            }
        });
        while(!future.isDone()){
            Thread.sleep(20);
            System.out.println("get...");
        }
        System.out.println(future.get());
    }

    private static <T> Future<T> invoke(Callable<T> callable) {
        AtomicReference<T> result = new AtomicReference<T>();
        AtomicBoolean finished = new AtomicBoolean(false);
        Thread t = new Thread(() -> {
            T value = callable.action();
            result.set(value);
            finished.set(true);
        });
        t.start();
        Future<T> future = new Future<T>() {
            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }
        };
        return future;
    }

    private interface Future<T> {
        T get();

        boolean isDone();
    }

    private interface Callable<T> {
        T action();
    }
}

