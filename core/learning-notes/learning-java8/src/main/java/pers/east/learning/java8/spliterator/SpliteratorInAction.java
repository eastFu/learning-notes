package pers.east.learning.java8.spliterator;

import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author East.F
 * @ClassName: SpliteratorInAction
 * @Description: pers.east.learning.java8.spliterator from pers.east.learning.java8.stream
 * @date 2019/7/24 8:43
 */
public class SpliteratorInAction {

    private static final String text ="sadfasdfasdfdsff\nsdfasdfasfasfsdfsdfsd\nsadfsdfasdf\nasdfsadfasdfd\nsdfsd";

    public static void main(String[] args) {
        /*IntStream intStream = IntStream.range(0, 10);
        Spliterator.OfInt pers.east.learning.java8.spliterator = intStream.pers.east.learning.java8.spliterator();
        Consumer<Integer> consumer = i -> System.out.println(i);
        pers.east.learning.java8.spliterator.forEachRemaining(consumer);*/

        MySpliteratorText mySpliteratorText = new MySpliteratorText(text);
        Optional.ofNullable(mySpliteratorText.stream().count()).ifPresent(System.out::println);

        mySpliteratorText.stream().forEach(System.out::println);

        mySpliteratorText.parallelStream().filter(s->!"".equals(s)).forEach(System.out::println);
    }


    static class MySpliteratorText {
        private final String[] data;

        public MySpliteratorText(String text) {
            Objects.requireNonNull(text, "The parameter can not be null");
            this.data = text.split("\n");
        }

        public Stream<String> stream(){
            return StreamSupport.stream(new MySpliterator(),false);
        }
        public Stream<String> parallelStream(){
            return StreamSupport.stream(new MySpliterator(),true);
        }

        private class MySpliterator implements Spliterator<String> {

            private int start;
            private int end;

            public MySpliterator() {
                this.start = 0;
                this.end = MySpliteratorText.this.data.length - 1;
            }

            public MySpliterator(int start, int end) {
                this.start = start;
                this.end = end;
            }

            @Override
            public boolean tryAdvance(Consumer<? super String> action) {
                if (start <= end) {
                    action.accept(MySpliteratorText.this.data[start]);
                    start++;
                    return true;
                }
                return false;
            }

            @Override
            public Spliterator<String> trySplit() {
                int mid = (end - start) / 2;
                if (mid <= 1) {
                    return null;
                }
                int left = start;
                int right = start + mid;
                start = start + mid + 1;
                return new MySpliterator(left, right);
            }

            @Override
            public long estimateSize() {
                return end - start;
            }

            @Override
            public long getExactSizeIfKnown() {
                return estimateSize();
            }

            @Override
            public int characteristics() {
                return IMMUTABLE | CONCURRENT | SUBSIZED;
            }
        }
    }
}
