package pers.east.learning.collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * @author East.F
 * @ClassName: ToListCollector
 * @Description: 自定义toList 的collector
 * <p>
 * 接收参数T，寄存器容器类型为List<T>，返回一个List<T>
 * </p>
 * @date 2019/7/22 8:46
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    private void log(final String log){
        System.out.println(Thread.currentThread().getName()+"-"+log);
    }

    @Override
    public Supplier<List<T>> supplier() {
        log("supplier");
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        log("accumulator");
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        log("combiner");
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        log("finisher");
        return t -> t;
    }

    @Override
    public Set<Characteristics> characteristics() {
        log("characteristics");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.CONCURRENT));
    }

    public static void main(String[] args) {
        Collector<String,List<String>,List<String>> myCollector = new ToListCollector<String>();
        Optional.ofNullable(Stream.of("java,golang,.net,scala,python,c,c++".split(","))
                .filter(s->s.length()>3)
                .collect(myCollector)).ifPresent(System.out::println);
    }
}
