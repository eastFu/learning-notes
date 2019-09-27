package pers.east.learning.algorithm.recursive;

/**
 * 递归练习： 二分法查找实现
 * @author Administrator
 */
public class BinarySearch {

    private static int[] arrays={3,4,5,6,7,8,9};

    public static int search(int find,int minIndex,int maxIndex){
        int index=-1;
        index=(minIndex+maxIndex)/2;
        if(minIndex>maxIndex){
            return -1;
        }else if(arrays[index]==find){
            return index;
        }else{
            if(find<arrays[index]){
                return search(find,minIndex,index-1);
            }else{
                return search(find,index+1,maxIndex);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(search(9,0,arrays.length-1));
    }
}
