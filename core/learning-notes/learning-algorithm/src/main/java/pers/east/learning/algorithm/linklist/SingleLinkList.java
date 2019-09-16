package pers.east.learning.algorithm.linklist;

/**
 * 链表练习： 单链表的基本操作
 * @author Administrator
 */
public class SingleLinkList {

    private LinkNode firstNode;

    public void insertFirst(int id){
        LinkNode newLink = new LinkNode(id);
        newLink.setNext(firstNode);
        firstNode=newLink;
    }

    public LinkNode removeFirst(){
        LinkNode temp = firstNode;
        firstNode = temp.getNext();
        return temp;
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
        SingleLinkList single = new SingleLinkList();
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
