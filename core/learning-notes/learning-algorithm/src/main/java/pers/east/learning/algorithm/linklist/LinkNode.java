package pers.east.learning.algorithm.linklist;

import com.alibaba.fastjson.JSON;

/**
 * 链表练习： 单链表
 * @author Administrator
 */
public class LinkNode {
    private int id;
    private LinkNode next;

    public LinkNode(int id) {
        super();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }

    public void print(){
        System.out.println("link node--->: "+ JSON.toJSONString(this));
    }
}
