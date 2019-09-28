package pers.east.learning.algorithm.recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 递归练习：背包问题
 * @author eastFu
 */
public class Bags {

    public static void bag(int[] data,int targetNum,int nowIndex,List<Integer> result){
        if(nowIndex==data.length){
            return;
        }
        if(data[nowIndex]>targetNum){
            return;
        } else if (data[nowIndex]==targetNum){
            result.add(data[nowIndex]);
            System.out.println("result:"+result);
        } else{
            result.add(data[nowIndex]);
            bag(data,targetNum-data[nowIndex],++nowIndex,result);
        }
    }

    public static void main(String[] args) {
        int [] data= {7,6,8,4,1,5,3,9,2};
        List<Integer> result = null;
        for(int i=0;i<data.length;i++){
            result = new ArrayList();
            bag(data,13,i,result);
        }
    }
}
