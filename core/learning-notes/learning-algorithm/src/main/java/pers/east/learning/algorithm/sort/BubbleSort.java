package pers.east.learning.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 *
 * 冒泡排序 ： 实现访方式很多，基本都一样，
 * 1. 需要两层循环来控制
 * 2. 第一层循环负责控制循环范围
 * 3. 第二层循环负责进行相邻元素的比较
 * 4. 比较之后交换位置
 *
 * @author eastFu
 */
public class BubbleSort {

    public static void bubble(int[] array) {
        System.out.println("sort before : " + JSON.toJSONString(array));
        int index = 0;
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                //交换位置
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    index++;
                }
            }
            System.out.println("sort" + i + " : " + JSON.toJSONString(array));
        }
        System.out.println("swap count : " + index);
    }

    public static void bubble2(int[] array) {
        System.out.println("sort2 before:" + JSON.toJSONString(array));
        int index2 = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] < array[j]) {
                    swap(array, i, j);
                    index2++;
                }
            }
            System.out.println("sort2 " + i + " : " + JSON.toJSONString(array));
        }
        System.out.println("swap count : " + index2);
    }

    public static void swap(int[] ar, int aIndex, int bIndex) {
        int temp = ar[aIndex];
        ar[aIndex] = ar[bIndex];
        ar[bIndex] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 6, 1, 3, 2, 7, 9, 8};
        bubble(arr);
//        bubble2(arr);
    }
}
