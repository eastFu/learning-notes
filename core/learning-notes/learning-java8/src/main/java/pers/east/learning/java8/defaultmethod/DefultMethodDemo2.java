package pers.east.learning.java8.defaultmethod;

/**
 * @author East.F
 * @ClassName: DefultMethodDemo2
 * @Description: TODO
 * @date 2019/7/25 19:08
 */
public class DefultMethodDemo2 {

    public static void main(String[] args) {
        /**
         *  1. 以最亲近的实现为主，优先级： 自己重写的>父亲的default>爷爷的default
         *  2. 如果 子类实现的两个接口，都有defult方法 而且方法名字相同， 则子类必须重写该方法
         */
        C a = new C();
        a.value();
    }
}

interface A{
    default void value(){
        System.out.println("A...");
    }
}

interface B extends A{
    @Override
    default void value(){
        System.out.println("B...");
    }
}

class C implements B{
//    @Override
//    public void value() {
//        System.out.println("C...");
//    }
}
