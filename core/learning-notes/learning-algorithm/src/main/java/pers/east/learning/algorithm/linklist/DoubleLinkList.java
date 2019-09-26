package pers.east.learning.algorithm.linklist;

/**
 * 链表练习： 双向链表实现
 *
 * @author Administrator
 */
public class DoubleLinkList {

    private LinkNode2 first;
    private LinkNode2 last;


    public void insertFirst(int id) {
        LinkNode2 temp = new LinkNode2(id);
        if (isEmpty()){
            last = temp;
        } else {
            first.setPrevious(temp);
        }
        temp.setNext(first);
        first=temp;
    }

    public LinkNode2 removeFirst() {
        if (!isEmpty()) {
            
        }
        return first;
    }

    public LinkNode2 peekFirst() {
        return first;
    }

    public boolean isEmpty() {
        return first == null && last == null;
    }

    public LinkNode2 find(int id) {
        return null;
    }

    public LinkNode2 remove(int id) {
        return null;
    }

    public void displayList() {
        System.out.println("============link list============");
        LinkNode2 template = first;
        while (template != null) {
            template.print();
            template = template.getNext();
        }
        System.out.println("============link list============");
    }

    public static void main(String[] args) {
        DoubleLinkList single = new DoubleLinkList();

        single.insertFirst(2);
        single.insertFirst(4);
        single.insertFirst(3);
        single.insertFirst(1);

        single.removeFirst();
        single.displayList();

        single.remove(4);
        single.displayList();

        single.insertFirst(4);
        single.displayList();
    }


}
