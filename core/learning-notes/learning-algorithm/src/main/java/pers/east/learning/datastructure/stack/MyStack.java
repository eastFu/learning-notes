package pers.east.learning.datastructure.stack;

import com.alibaba.fastjson.JSON;

/**
 * 自定义栈
 *
 * @author eastFu
 */
public class MyStack {

    private int[] data;
    private int topIndex = -1;

    public MyStack(int length) {
        data = new int[length];
    }

    public boolean push(int d){
        if(isFull()){
            return false;
        }
        topIndex++;
        data[topIndex]=d;
        return true;
    }

    public int pop(){
        int result = data[topIndex];
        topIndex--;
        return result;
    }

    public int peek(){
        return data[topIndex];
    }

    public boolean isEmpty(){
        return topIndex==-1;
    }
    public boolean isFull(){
        return topIndex==(data.length-1);
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack(5);
        myStack.push(2);
        myStack.push(7);
        myStack.push(6);
        myStack.push(5);
        myStack.push(3);
        // 元素满了，压不进去了
        System.out.println("is full : "+ myStack.isFull());
        System.out.println("push result : " + myStack.push(31));
        System.out.println(JSON.toJSONString(myStack.data));

        System.out.println(myStack.pop());
        System.out.println(JSON.toJSONString(myStack.data));
        System.out.println(myStack.pop());
        System.out.println(JSON.toJSONString(myStack.data));
        System.out.println(myStack.peek());
        System.out.println(myStack.isEmpty());
    }

}
