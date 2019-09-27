package pers.east.learning.datastructure.linklist;

/**
 * 链表练习： 双向链表实现
 *
 * @author eastFu
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

    public void insertLast(int id) {
        LinkNode2 temp = new LinkNode2(id);
        if (isEmpty()){
            first = temp;
        } else {
            last.setNext(temp);
            temp.setPrevious(last);
        }
        last=temp;
    }

    public LinkNode2 removeFirst() {
        if(isEmpty()){
            return null;
        }
        LinkNode2 temp = first;
        if (first.getNext()==null) {
            last=null;
        } else{
            first.getNext().setPrevious(null);
        }
        first=first.getNext();
        return temp;
    }

    public LinkNode2 removeLast() {
        if (isEmpty()) {
            return null;
        }
        LinkNode2 temp = last;
        if(last.getPrevious()!=null){
            last.getPrevious().setNext(null);
        } else {
            first=null;
        }
        last=last.getPrevious();
        return temp;
    }

    public LinkNode2 peekFirst() {
        return first;
    }

    public boolean isEmpty() {
        return first == null && last == null;
    }

    public LinkNode2 find(int id) {
        LinkNode2 temp = first;
        while(temp!=null&&temp.getId()!=id){
            if(temp.getNext()==null){
                return null;
            }else{
                temp=temp.getNext();
            }
        }
        return temp;
    }

    public LinkNode2 remove(int id) {
        if(isEmpty()){
            return null;
        }
        LinkNode2 temp = first;
        while(temp.getId()!=id){
            if(temp.getNext()==null){
                return null;
            }else{
                temp=temp.getNext();
            }
        }
        if(temp.getPrevious()!=null){
            temp.getPrevious().setNext(temp.getNext());
        } else{
            first=temp.getNext();
        }
        if(temp.getNext()!=null){
            temp.getNext().setPrevious(temp.getPrevious());
        }else{
            last=temp.getPrevious();
        }
        return temp;
    }

    public void displayListForward() {
        System.out.println("============forward link list============");
        LinkNode2 template = first;
        while (template != null) {
            template.print();
            template = template.getNext();
        }
        System.out.println("============forward link list============");
    }

    public void displayListBackward() {
        System.out.println("============backward link list============");
        LinkNode2 template = last;
        while (template != null) {
            template.print();
            template = template.getPrevious();
        }
        System.out.println("============backward link list============");
    }

    public static void main(String[] args) {
        DoubleLinkList doubleLinkList = new DoubleLinkList();

        doubleLinkList.insertFirst(2);
        doubleLinkList.insertFirst(3);
        doubleLinkList.insertFirst(1);

        doubleLinkList.displayListForward();

        doubleLinkList.find(3);

        doubleLinkList.removeFirst();
        doubleLinkList.displayListForward();

        doubleLinkList.remove(2);
        doubleLinkList.displayListForward();

        doubleLinkList.insertLast(4);
        doubleLinkList.displayListForward();

        doubleLinkList.insertFirst(5);
        doubleLinkList.displayListForward();

        doubleLinkList.removeLast();
        doubleLinkList.displayListBackward();
    }


}
