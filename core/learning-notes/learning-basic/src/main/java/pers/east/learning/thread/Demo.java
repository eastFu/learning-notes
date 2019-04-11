package pers.east.learning.thread;

public class Demo {
    public static final Object LOCK1 = new Object();
    public static final Object LOCK2 = new Object();

    public static void main(String[] args) {

        DeadLockThread dlt = new DeadLockThread(true);
        DeadLockThread dlt2 = new DeadLockThread(false);

        dlt.start();
        dlt2.start();
    }
}
