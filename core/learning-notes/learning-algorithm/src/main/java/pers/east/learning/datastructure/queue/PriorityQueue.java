package pers.east.learning.datastructure.queue;

import java.util.Arrays;

/**
 * 队列练习： 优先级队列的实现
 * @author Administrator
 */
public class PriorityQueue {
    private int[] queue;
    /**
     *  队列里面存放的元素个数
     */
    private int nItems;

    public PriorityQueue(int length) {
        queue = new int[length];
        nItems =0;
    }

    public void insert(int data){
        if (nItems==0){
            queue[nItems]=data;
            nItems++;
        } else {
            int i =0;
            for (i=nItems-1;i>=0;i--){
                if(data<queue[i]){
                    queue[i+1]=queue[i];
                } else {
                    break;
                }
            }
            queue[i+1] = data;
            nItems++;
        }
    }

    public int remove(){
        nItems--;
        int temp = queue[nItems];
        queue[nItems]=0;
        return temp;
    }

    public int peekFront(){
        return queue[nItems-1];
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
        PriorityQueue priorityQueue = new PriorityQueue(5);

        priorityQueue.insert(4);
        priorityQueue.insert(3);
        priorityQueue.insert(1);
        priorityQueue.insert(2);
        priorityQueue.insert(5);

        priorityQueue.print();

        System.out.println(priorityQueue.peekFront());

        System.out.println(priorityQueue.remove());
        System.out.println(priorityQueue.peekFront());

        priorityQueue.print();
    }
}
