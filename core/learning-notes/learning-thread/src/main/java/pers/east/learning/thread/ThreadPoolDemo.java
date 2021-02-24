package pers.east.learning.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 * @version 0.1
 * @description: TODO
 * @date 21/02/23 18:19
 */
public class ThreadPoolDemo {

    ExecutorService executorService = Executors.newCachedThreadPool();

    private void test(){
        for (int i =0 ;i<=10;i++){
            final int t = i;
            executorService.submit(()->{
                try {
                    Thread.sleep(1000);
                    System.out.println("thread num "+ t);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
            System.out.println("demo===============");
        }

    }

    public static void main(String[] args) {
        ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo();
        threadPoolDemo.test();
    }
}
