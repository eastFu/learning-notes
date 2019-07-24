package pers.east.learning.java8.methodreference;

@FunctionalInterface
public interface ThreeFunction<W,C,F,R> {

    R applay(W w,C c,F f);
}
