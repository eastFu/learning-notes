package pers.east.learning.algorithm.linklist;

/**
 * 链表练习： 有序链表的实现
 * @author Administrator
 */
public class SortedLinkList {

    private LinkNode firstNode;

    public void insertFirst(int id){
        LinkNode newLink = new LinkNode(id);
        LinkNode previous = null;
        LinkNode current = firstNode;
        while(current!=null&&id>current.getId()){
            previous = current;
            current = current.getNext();
        }
        if(previous==null){
            firstNode = newLink;
        }else {
            previous.setNext(newLink);
        }
        newLink.setNext(current);
    }

    public LinkNode removeFirst(){
        LinkNode temp = firstNode;
        firstNode = temp.getNext();
        return temp;
    }

    public LinkNode peekFirst(){
        return firstNode;
    }

    public boolean isEmpty(){
        return firstNode==null;
    }

    public LinkNode find(int id){
        LinkNode node = firstNode;
        while (node.getId()!=id){
            if(node.getNext()==null){
                return null;
            } else {
                node = node.getNext();
            }
        }
        return node;
    }

    public LinkNode remove(int id){
        LinkNode del = firstNode;
        LinkNode preNode = firstNode;
        while (del.getId()!=id){
            if (del.getNext()==null){
                return null;
            } else {
                preNode = del;
                del = del.getNext();
            }
        }

        if (del.equals(firstNode)){
            firstNode=preNode;
        } else {
            preNode.setNext(del.getNext());
        }
        return del;
    }

    public void displayList(){
        System.out.println("============link list============");
        LinkNode template = firstNode;
        while (template!=null){
            template.print();
            template =template.getNext();
        }
        System.out.println("============link list============");
    }

    public static void main(String[] args) {
        SortedLinkList single = new SortedLinkList();
        single.insertFirst(2);
        single.insertFirst(4);
        single.insertFirst(3);
        single.insertFirst(1);
        single.displayList();
        single.removeFirst();
        single.displayList();

        single.remove(4);
        single.displayList();

        single.insertFirst(4);
        single.displayList();
    }
}
