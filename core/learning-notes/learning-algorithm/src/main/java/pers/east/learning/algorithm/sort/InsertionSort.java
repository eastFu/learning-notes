package pers.east.learning.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * 插入排序： 双层循环，每次找出最大的一个放入每次循环的起始位置
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
