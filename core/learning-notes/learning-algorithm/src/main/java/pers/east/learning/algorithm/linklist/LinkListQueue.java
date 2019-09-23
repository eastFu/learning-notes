package pers.east.learning.algorithm.linklist;

/**
 * 链表练习：使用链表来实现队列
 * @author Administrator
 */
public class LinkListQueue {

    private FirstLastList theList = new FirstLastList();

    public void insert(int data){
        theList.insertFirst(data);
    }

    public int remove(){
       return theList.removeFirst().getId();
    }

    public int peekFront(){
        return theList.peekFirst().getId();
    }

    public boolean isEmpty(){
        return theList.isEmpty();
    }

    public void print(){
        System.out.println("------->");
        theList.displayList();
    }

    public static void main(String[] args) {
        LinkListQueue myQueue = new LinkListQueue();
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
