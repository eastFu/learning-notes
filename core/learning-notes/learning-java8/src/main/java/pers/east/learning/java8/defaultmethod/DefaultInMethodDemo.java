package pers.east.learning.java8.defaultmethod;

/**
 * @author East.F
 * @ClassName: DefaultInMethodDemo
 * @Description: default method test
 * @date 2019/7/24 9:54
 */
public class DefaultInMethodDemo {
    public static void main(String[] args) {
        A a = ()->10;
        System.out.println(a.size());
        System.out.println(a.isEmpty());
    }

    @FunctionalInterface
    interface A {
        int size();
        default boolean isEmpty(){
            return size()==0;
        }
    }
}
