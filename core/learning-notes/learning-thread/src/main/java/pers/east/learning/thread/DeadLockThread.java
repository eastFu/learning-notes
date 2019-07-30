package pers.east.learning.thread;

/**
 *  死锁
 *
 *  概念：同步中，多个线程使用多把锁之间存在等待的现象。
 *
 *  原因分析：
 *  a.线程1将锁1锁住，线程2将锁2锁住，而线程1要继续执行锁2中的代码，线程2要继续执行锁1中的代码，
 *    但是此时，两个锁均处于锁死状态。最终导致两线程相互等待，进入无限等待状态。
 *  b.有同步代码块的嵌套动作。
 *
 *  解决方法：
 *  不要写同步代码块嵌套
 */
public class DeadLockThread extends Thread{

    boolean flag;

    public DeadLockThread(boolean flag){
        this.flag=flag;
    }

    @Override
    public void run() {
        if(flag){
            System.out.println("if语句锁1");
            synchronized (Demo.LOCK1){
                //睡眠造成死锁现象
                /*try {
                    sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }*/
                synchronized (Demo.LOCK2){
                    System.out.println("if 语句锁2");
                }
            }
        }else{
            try {
                sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            synchronized (Demo.LOCK1){
                System.out.println("else 语句锁1");
                synchronized (Demo.LOCK2){
                    System.out.println("else 语句锁2");
                }
            }
        }
    }
}

class Demo {
    public static final Object LOCK1 = new Object();
    public static final Object LOCK2 = new Object();

    public static void main(String[] args) {
        DeadLockThread dlt = new DeadLockThread(true);
        DeadLockThread dlt2 = new DeadLockThread(false);
        dlt.start();
        dlt2.start();
    }
}
