package pers.east.learning.java8.future;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author East.F
 * @ClassName: FutureAction3
 * @Description: java future  异步回调 自定义的实现
 * @date 2019/7/25 19:25
 */
public class FutureAction3 {

    public static void main(String[] args) {
        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(5000);
                return "I am finished";
            } catch (InterruptedException e) {
                return "I am error";
            }
        });
        future.setCompletable(new Completable<String>() {
            @Override
            public void complete(String s) {
                System.out.println(s);
            }

            @Override
            public void exception(Throwable cause) {
                System.out.println("error");
                cause.printStackTrace();
            }
        });

    }

    private static <T> Future<T> invoke(Callable<T> callable) {
        AtomicReference<T> result = new AtomicReference<T>();
        AtomicBoolean finished = new AtomicBoolean(false);
        Future<T> future = new Future<T>() {
            private Completable<T> completable;
            @Override
            public T get() {
                return result.get();
            }
            @Override
            public boolean isDone() {
                return finished.get();
            }
            @Override
            public void setCompletable(Completable<T> completable) {
                this.completable = completable;
            }
            @Override
            public Completable<T> getCompletable() {
                return completable;
            }
        };

        Thread t = new Thread(() -> {
            try {
                T value = callable.action();
                result.set(value);
                finished.set(true);
                if(future.getCompletable()!=null){
                    future.getCompletable().complete(value);
                }
            }catch (Throwable cause){
                future.getCompletable().exception(cause);
            }
        });
        t.start();

        return future;
    }

    private interface Future<T> {
        T get();
        boolean isDone();
        void setCompletable (Completable<T> completable);

        Completable<T> getCompletable();
    }

    private interface Callable<T> {
        T action();
    }

    private interface Completable<T>{
        void complete(T t);
        void exception(Throwable cause);
    }
}

