package pers.east.learning.algorithm.linklist;

import com.alibaba.fastjson.JSON;

/**
 * 链表练习： 双向链表
 * @author Administrator
 */
public class LinkNode2 {
    private int id;
    private LinkNode2 next;
    private LinkNode2 previous;

    public LinkNode2(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkNode2 getNext() {
        return next;
    }

    public void setNext(LinkNode2 next) {
        this.next = next;
    }

    public LinkNode2 getPrevious() {
        return previous;
    }

    public void setPrevious(LinkNode2 previous) {
        this.previous = previous;
    }

    public void print(){
        System.out.println("link node--->: id="+id+",pre:"+(previous!=null?previous.getId():"")+",next:"+(next!=null?next.getId():""));
    }
}
