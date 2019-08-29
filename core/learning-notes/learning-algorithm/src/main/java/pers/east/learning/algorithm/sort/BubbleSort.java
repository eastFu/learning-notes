package pers.east.learning.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * 冒泡排序
 */
public class BubbleSort {

    static int [] arr = {5,6,1,3,2,7,9,8};

    public static void bubble(int[] array){
        System.out.println("sort before:"+JSON.toJSONString(array));
        int index =0;
        for(int i = 0;i< array.length;i++){
            for(int j = i+1;j<array.length;j++){
                //交换位置
                if(array[i]>array[j]){
                    int tmp = array[i];
                    array[i]=array[j];
                    array[j]=tmp;
                }
                index++;
            }
            System.out.println("sort "+i+":"+JSON.toJSONString(array));
        }
        System.out.println("index:"+index);
    }

    public static void bubble2(int[] array){
        System.out.println("sort2 before:"+JSON.toJSONString(array));
        int index2 =0;
        for(int x = 0;x< array.length;x++){
            for(int y = 0;y<array.length-x-1;y++){
                //交换位置
                if(array[y]>array[y+1]){
                    int tmp = array[y];
                    array[y]=array[y+1];
                    array[y+1]=tmp;
                }
                index2++;
            }
            System.out.println("sort2 "+x+":"+JSON.toJSONString(array));
        }
        System.out.println("index2:"+index2);
    }

    public static void main(String[] args) {
//        bubble(arr);
        bubble2(arr);
    }
}
