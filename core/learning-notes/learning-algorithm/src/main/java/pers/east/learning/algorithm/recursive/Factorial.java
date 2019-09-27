package pers.east.learning.algorithm.recursive;

/**
 * 递归算法练习： 阶乘
 * @author eastFu
 */
public class Factorial {

    // 实现N!=1*2*3*...*(N-1)*N

    public static int factorial(int n){
        if(n!=1){
            return n*factorial(n-1);
        }
        return 1;
    }
    public static void main(String[] args) {
        System.out.println(Factorial.factorial(8));
    }
}
