package pers.east.learning.collector;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author East.F
 * @ClassName: ToListCollector
 * @Description: 自定义toList 的collector
 * <p>
 *      接收参数T，寄存器容器类型为List<T>，返回一个List<T>
 * </p>
 * @date 2019/7/22 8:46
 */
public class ToListCollector<T> implements Collector<T,List<T>, List<T>> {

    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1,list2)->{
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
