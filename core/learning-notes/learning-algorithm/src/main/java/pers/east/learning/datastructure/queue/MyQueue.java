package pers.east.learning.datastructure.queue;

import java.util.Arrays;

/**
 * 队列练习：自定义循环队列
 * @author Administrator
 */
public class MyQueue {

    private int[] queue;
    private int front;
    private int end;
    /**
     *  队列里面存放的元素个数
     */
    private int nItems;

    public MyQueue(int length) {
        queue = new int[length];
        front =0;
        end = -1;
        nItems =0;
    }

    public void insert(int data){
        if (end == queue.length-1){
            end = -1;
        }
        end ++;
        queue[end] = data;
        nItems++;
        if (nItems>queue.length){
            nItems = queue.length;
        }
    }

    public int remove(){
        if (nItems==0){
            return 0;
        }
        int temp = queue[front];
        queue[front] = 0;

        if (front==queue.length-1){
            front =0;
        }
        front++;
        nItems--;
        return temp;
    }

    public int peekFront(){
        return queue[front];
    }

    public boolean isEmpty(){
        return nItems==0;
    }

    public boolean isFull(){
        return nItems==queue.length;
    }

    public void print(){
        System.out.println("------->");
        Arrays.stream(queue).forEach(System.out::println);
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(5);
        myQueue.insert(1);
        myQueue.insert(2);
        myQueue.insert(3);
        myQueue.insert(4);
        myQueue.insert(5);

        myQueue.print();

        System.out.println(myQueue.peekFront());

        System.out.println(myQueue.remove());
        System.out.println(myQueue.peekFront());

        myQueue.print();

        myQueue.insert(6);
        myQueue.insert(7);
        myQueue.insert(8);

        myQueue.print();
    }

}
