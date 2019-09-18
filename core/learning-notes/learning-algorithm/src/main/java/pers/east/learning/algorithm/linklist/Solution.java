package pers.east.learning.algorithm.linklist;

import com.alibaba.fastjson.JSON;

/**
 * 链表算法的练习
 * @author Administrator
 */
public class Solution {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null){
                q = q.next;
            }
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        // [2,4,3] , [5,6,4]   =   [7,0,8]
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        l2.next=l3;
        l1.next=l2;

        ListNode x1= new ListNode(5);
        ListNode x2= new ListNode(6);
        ListNode x3= new ListNode(4);
        x2.next=x3;
        x1.next=x2;

        System.out.println(JSON.toJSONString(addTwoNumbers(l1,x1)));
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}
