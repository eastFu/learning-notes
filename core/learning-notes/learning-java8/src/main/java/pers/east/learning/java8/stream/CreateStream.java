package pers.east.learning.java8.stream;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author East.F
 * @ClassName: CreateStream
 * @Description: TODO
 * @date 2019/7/20 18:39
 */
public class CreateStream {
    public static void main(String[] args) {
        createStreamFromIterator();
        createStreamFromGenerate();
        createStreamFromSupplier();
    }

    public static void createStreamFromIterator(){
        Stream.iterate(0, i->i+2).limit(10).forEach(System.out::println);
    }
    public static void createStreamFromGenerate(){
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }


    private static void createStreamFromSupplier(){
        Stream.generate(new userSupplier()).limit(10).forEach(System.out::println);
    }

    static class userSupplier implements Supplier<user>{

        private int index =0;
        private Random random = new Random(System.currentTimeMillis());
        @Override
        public user get() {
            index = random.nextInt(100);
            return new user(index,index + "laowang");
        }
    }
    static class user{
        private int id;
        private String name;

        public user(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}


