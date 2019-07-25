package pers.east.learning.java8.defaultmethod;

/**
 * @author East.F
 * @ClassName: DefaultInMethodDemo
 * @Description: default method test
 * @date 2019/7/24 9:54
 */
public class DefaultInMethodDemo3 {

    public static void value(Object a) {
        System.out.println("object");
    }

    public static void value(String b) {
        System.out.println("String");
    }

    /*public static void value(int[] c){
        System.out.println("int []");
    }*/


    public static void main(String[] args) {
        /**
         *  遇到这种情况，java 解释了 方法调用优先级的解释：
         *  1. 如果参数声明多了 说明类型，则使用就近方法（更熟，找寻路径最短），比如修饰用object 则调用object
         *  2. 如果参数描述模糊，则使用参数更具体的方法 int[] 比object 入参描述更具体
         */
        value(null);

        String a = null;
        Object b = a;
        value(b);
    }

}
