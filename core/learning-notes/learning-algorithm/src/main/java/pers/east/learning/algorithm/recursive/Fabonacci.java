package pers.east.learning.algorithm.recursive;

import java.util.Arrays;

/**
 * 递归练习：斐波那切数列
 *
 * @author eastFu
 */
public class Fabonacci {

    /**
     * 获取一个斐波那切数列
     * @param num
     * @return
     */
    public static int[] getFabonacci(int num) {
        int[]arrays = new int[num];
        for(int i=0;i<num;i++){
            arrays[i]=getNum(i);
        }
        return arrays;
    }

    /**
     * 求第num个元素的值
     * @param num
     * @return
     */
    public static int getNum(int num) {
        if (num == 1) {
            return 0;
        }
        if (num == 2) {
            return 1;
        }
        return getNum(num - 1) + getNum(num - 2);
    }

    /**
     * 求第num个元素之前元素的总和
     * @param num
     * @return
     */
    public static int sum(int num) {
        if(num==1){
            return 0;
        }
        return getNum(num)+sum(num-1);
    }

    public static void main(String[] args) {
//        Arrays.asList(getFabonacci(10)).forEach(System.out::println);
        System.out.println(getNum(6));
        System.out.println(sum(5));
    }

}
