package pers.east.learning.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 *
 * 比较排序 ：冒泡排序的修改， 两层循环，找寻每次循环中，最小的元素放到循环的起始位置，以此类推。
 *
 * @author eastFu
 */
public class SelectionSort {

    public static void selection(int[] array) {
        System.out.println("sort before:" + JSON.toJSONString(array));
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            int minIndex =i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            swap(array,i,minIndex);
            index++;
            System.out.println("sort " + i + " : " + JSON.toJSONString(array));
        }
        System.out.println("index : " + index);
    }

    public static void swap(int[] ar, int aIndex, int bIndex) {
        int temp = ar[aIndex];
        ar[aIndex] = ar[bIndex];
        ar[bIndex] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 6, 1, 3, 2, 7, 9, 8};
        selection(arr);
    }
}
