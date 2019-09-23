package pers.east.learning.algorithm.linklist;

import com.alibaba.fastjson.JSON;
import pers.east.learning.algorithm.stack.MyStack;

/**
 * 链表练习： 使用链表来实现栈
 *
 * @author Administrator
 */
public class LinkListStack {

    private SingleLinkList theList = new SingleLinkList();

    public boolean push(int id) {
        theList.insertFirst(id);
        return true;
    }

    public int pop() {
        return theList.removeFirst().getId();
    }

    public int peek() {
        return theList.peekFirst().getId();
    }

    public boolean isEmpty() {
        return theList.isEmpty();
    }

    public void print(){
        theList.displayList();
    }

    public static void main(String[] args) {
        LinkListStack myStack = new LinkListStack();
        myStack.push(2);
        myStack.push(7);
        myStack.push(6);
        myStack.push(5);
        myStack.push(3);

        myStack.print();

        System.out.println("push result : " + myStack.push(31));
        System.out.println(myStack.pop());
        myStack.print();
        System.out.println(myStack.peek());
        System.out.println(myStack.isEmpty());
        myStack.print();

    }
}
