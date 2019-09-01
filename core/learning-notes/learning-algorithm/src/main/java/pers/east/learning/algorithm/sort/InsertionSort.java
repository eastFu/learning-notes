package pers.east.learning.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * 插入排序： 双层循环，每次与前面的排好序的元素比较，如果遇到自己比其中一个大，停止，说明这次这次循环已经排好序了
 * @author eastFu
 */
public class InsertionSort {

    public static void insertion(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j-1]) {
                    swap(array,j,j-1);
                }else{
                    break;
                }
            }
        }
    }

    public static void swap(int[] ar, int aIndex, int bIndex) {
        int temp = ar[aIndex];
        ar[aIndex] = ar[bIndex];
        ar[bIndex] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 6, 1, 3, 2, 7, 9, 8};
        insertion(arr);
        System.out.println(JSON.toJSONString(arr));
    }
}
