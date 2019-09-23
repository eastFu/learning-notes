package pers.east.learning.algorithm.linklist;

import java.util.Arrays;

/**
 * 链表练习： 使用有序链表来实现排序
 * @author Administrator
 */
public class SortedBySortedLinkList {

    public static int[] sort(int[] arr){
        int[] ret = new int[arr.length];
        SortedLinkList sortedLinkList = new SortedLinkList();
        for(int i:arr){
            sortedLinkList.insertFirst(i);
        }
        int index =0;
        while (!sortedLinkList.isEmpty()){
            ret[index] = sortedLinkList.removeFirst().getId();
            index++;
        }
        return ret;
    }

    public static void main(String[] args) {
        int [] arr = {6,7,3,4,1,8,9};
        Arrays.stream(SortedBySortedLinkList.sort(arr)).forEach(System.out::println);
    }
}
