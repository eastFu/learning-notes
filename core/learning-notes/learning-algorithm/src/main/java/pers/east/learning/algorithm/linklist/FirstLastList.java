package pers.east.learning.algorithm.linklist;

/**
 * 链表练习： 双端链表
 * @author Administrator
 */
public class FirstLastList {
    private LinkNode firstNode;
    private LinkNode lastNode;

    public void insertFirst(int id){
        LinkNode newLink = new LinkNode(id);
        if (firstNode==null) {
            lastNode = newLink;
        }
        newLink.setNext(firstNode);
        firstNode=newLink;
    }
    public void insertLast(int id){
        LinkNode newLink = new LinkNode(id);
        if (firstNode==null) {
            firstNode = newLink;
        } else {
            lastNode.setNext(newLink);
        }
        lastNode=newLink;
    }

    public LinkNode removeFirst(){
        LinkNode temp = firstNode;
        firstNode = temp.getNext();
        if (firstNode ==null){
            lastNode = null;
        }
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

    public void displayList(){
        System.out.println("============link list============");
        LinkNode first = firstNode;
        LinkNode last = firstNode;
        while (first!=null){
            first.print();
            first =first.getNext();
        }
        System.out.println("============link list============");
    }

    public static void main(String[] args) {
        FirstLastList firstLastList = new FirstLastList();
        firstLastList.insertFirst(2);
        firstLastList.insertFirst(4);
        firstLastList.insertFirst(3);
        firstLastList.insertFirst(1);

        firstLastList.removeFirst();
        firstLastList.displayList();

        firstLastList.insertFirst(4);
        firstLastList.displayList();
    }
}
