package pers.east.learning.algorithm.sort;

/**
 * 插入排序：
 * @author eastFu
 */
public class InsertionSort {

    public static void insertion(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int maxIndex = i;
            for (int j = i; j > 0; j--) {
                if (array[j-1] > array[maxIndex]) {
                    maxIndex=j-1;
                }
            }
            swap(array,i,maxIndex);
        }
    }

    public static void swap(int[] ar, int aIndex, int bIndex) {
        int temp = ar[aIndex];
        ar[aIndex] = ar[bIndex];
        ar[bIndex] = temp;
    }
}
